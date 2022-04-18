package dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseDTO implements Serializable{

    private int id;

    private int user_id;

    private int payment_id;

    private List<CartDTO> cart;
}
