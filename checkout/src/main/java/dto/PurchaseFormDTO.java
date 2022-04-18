package dto;

import java.io.Serializable;
import java.util.List;

import com.checkout.checkout.entity.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PurchaseFormDTO implements Serializable{

    private int user_id;

    private int payment_id;

    private List<Cart> cart;
}
