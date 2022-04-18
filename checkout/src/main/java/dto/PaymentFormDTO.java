package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFormDTO {

    private String type;

    private int discount;

    private boolean status;

    public boolean getStatus() {
        return this.status;
    }
}
