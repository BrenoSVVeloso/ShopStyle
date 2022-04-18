package com.checkout.checkout.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.checkout.checkout.client.CatalogClient;
import com.checkout.checkout.client.CustomerClient;
import com.checkout.checkout.entity.Cart;
import com.checkout.checkout.entity.Payment;
import com.checkout.checkout.entity.Purchase;
import com.checkout.checkout.exception.ExceptionResponse;
import com.checkout.checkout.rabbitmq.service.RabbitMqService;
import com.checkout.checkout.repositoy.PaymentRepository;
import com.checkout.checkout.repositoy.PurchaseRepositoy;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import constants.RabbitMqConstants;
import dto.EstoqueDTO;
import dto.PaymentDTO;
import dto.PaymentFormDTO;
import dto.PurchaseDTO;
import dto.PurchaseFormDTO;
import dto.catalog.ProductDTO;
import dto.catalog.VariationDTO;
import dto.customer.UserDTO;
import dto.history.HistoryHDTO;
import dto.history.PaymentHDTO;
import dto.history.ProductHDTO;
import dto.history.PurchaseHDTO;
import dto.history.UserHDTO;



@Service
public class CheckoutServiceImpl implements CheckoutService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PurchaseRepositoy purchaseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RabbitMqService rabbitMqService;  

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private CatalogClient catalogClient;

    @Override
    public void savePayment(@Valid PaymentFormDTO body) {
        try{
            Payment payment = this.modelMapper.map(body, Payment.class);
            this.paymentRepository.save(payment);
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public List<PaymentDTO> listPayments() {
        try{
            List<Payment> payments = paymentRepository.findAll();
            List<PaymentDTO> paymentsDTO = new ArrayList<>();
            payments.forEach((pa) -> {
                paymentsDTO.add(modelMapper.map(pa, PaymentDTO.class));
            });
            return paymentsDTO;
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public PaymentDTO getPayment(int id) {
        try{
            Optional<Payment> oPayment = paymentRepository.findById(id);
            if(oPayment.isPresent()){
                return modelMapper.map(oPayment.get(), PaymentDTO.class);
            }else {
                throw new ExceptionResponse(404, "Payment Not Found");

            }
        }catch(Exception e) {
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public PaymentDTO uptadePayment(@Valid PaymentFormDTO body, int id) {
        try{
            Optional<Payment> oPayment = paymentRepository.findById(id);
            if(oPayment.isPresent()){

                oPayment.get().setDiscount(body.getDiscount());
                oPayment.get().setStatus(body.getStatus());
                oPayment.get().setType(body.getType());
                paymentRepository.save(oPayment.get());

                return modelMapper.map(oPayment.get(), PaymentDTO.class);
            }else{
                throw new ExceptionResponse(404, "Payment Not Found");

            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public void deletePayment(int id) {
        try{
            Optional<Payment> oPayment = paymentRepository.findById(id);
            if(oPayment.isPresent()){
                paymentRepository.deleteById(id);
            }else{
                throw new ExceptionResponse(404,"Payment Not Found");

            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public PurchaseDTO purchase(@Valid PurchaseFormDTO body) {
        try{
            double total = 0;
            int quantity = 0;
            UserDTO user = this.customerClient.getUser(body.getUser_id());
            UserHDTO userHistory = this.modelMapper.map(user, UserHDTO.class);
            if(user.getActive()){
                Payment payment = this.paymentRepository.getById(body.getPayment_id());
                PaymentHDTO paymentHistory = this.modelMapper.map(payment, PaymentHDTO.class);
                if(payment.getStatus()){

                    List<Cart> cart = new ArrayList<>();
                    for (int i = 0; i<body.getCart().size();i++) {

                        if(cart.isEmpty()){

                            cart.add(i, new Cart(1,body.getCart().get(i).getVariant_id(), body.getCart().get(i).getQuantity()));
                            body.getCart().get(i).setId(cart.get(i).getId());
                        }else{

                            cart.add(i, new Cart(cart.get(i-1).getId()+1,body.getCart().get(i).getVariant_id(), body.getCart().get(i).getQuantity()));
                            body.getCart().get(i).setId(cart.get(i).getId());

                        }

                    }

                    ProductDTO product[] = new ProductDTO[body.getCart().size()];

                    ProductHDTO productHistory[] = new ProductHDTO[body.getCart().size()];

                    for (int i = 0; i<cart.size();i++) {
                        quantity += cart.get(i).getQuantity();
                        VariationDTO variant = this.catalogClient.getVariation(cart.get(i).getVariant_id());
                        total += variant.getPrice();

                        product[i] = this.catalogClient.getProduct(variant.getProduct_id());

                        productHistory[i] = new ProductHDTO(product[i].getId(), product[i].getName(), product[i].getDescription(), variant.getColor(), variant.getSize(), variant.getPrice(), variant.getQuantity());

                        if(!product[i].getActive()){
                            throw new ExceptionResponse(400, "Product Not Active");

                        }
                    }                    

                    PurchaseDTO purchaseDTO = modelMapper.map(body, PurchaseDTO.class);
                    Long length = this.purchaseRepository.count();
                    purchaseDTO.setId((int) (length+1));
                    Purchase purchase = modelMapper.map(purchaseDTO, Purchase.class);
                    
                    PurchaseHDTO purchaseHistory = new PurchaseHDTO(purchaseDTO.getId(), paymentHistory, productHistory, total, LocalDate.now());

                    HistoryHDTO historyDTO = new HistoryHDTO(userHistory, purchaseHistory);

                    EstoqueDTO estoqueDTO = new EstoqueDTO(historyDTO.getPurchases().getId(), quantity);

                    this.purchaseRepository.save(purchase);
                    this.rabbitMqService.enviaMensagem(RabbitMqConstants.FILA_ESTOQUE, estoqueDTO);
                    this.rabbitMqService.enviaMensagem(RabbitMqConstants.FILA_PRECO, historyDTO);
                    return purchaseDTO;
                }
            }
            throw new ExceptionResponse(400, "User Not Active");

        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public PurchaseDTO getPurchaseByUserId(int id) {
        try{
            Optional<Purchase> oPurchase = this.purchaseRepository.findById(id);
            if(oPurchase.isPresent()){
                return modelMapper.map(oPurchase.get(), PurchaseDTO.class);
            }else{
                throw new ExceptionResponse(404, "Purchase Not Found");
            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    
}
