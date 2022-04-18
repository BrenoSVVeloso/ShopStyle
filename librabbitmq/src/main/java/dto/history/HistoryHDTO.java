package dto.history;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryHDTO implements Serializable{

    private UserHDTO user;

    private PurchaseHDTO purchases;

}
