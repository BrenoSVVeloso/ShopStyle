// package com.history.history.client;


// import com.history.history.dto.checkout.PaymentDTO;
// import com.history.history.dto.checkout.PurchaseFormDTO;

// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient("checkout")
// public interface CheckoutClient {

//     @GetMapping("/v1/payments/:{id}")
//     PaymentDTO getPayment(@PathVariable int id);
    
//     @GetMapping("/v1/purchases/:{id}")
//     PurchaseFormDTO getPurchaseByUserId(@PathVariable int id);
// }
