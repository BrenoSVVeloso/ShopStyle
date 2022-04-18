package dto.history;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHDTO implements Serializable{

    private int id;

    private PaymentHDTO paymentMethod;


    private ProductHDTO[] product;

    private double total;

    private LocalDate date;
}
