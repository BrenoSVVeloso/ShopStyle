package dto.history;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHDTO implements Serializable{
    
    private int id;
    

    private String type;


    private int discount;

 
    private boolean status;


    public boolean getStatus(){
        return this.status;
    }
}
