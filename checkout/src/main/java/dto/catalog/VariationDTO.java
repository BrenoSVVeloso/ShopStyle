package dto.catalog;

import javax.validation.constraints.NotNull;

import dto.history.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariationDTO {
    
    private int id;

    @NotNull
    private String color;

    @NotNull
    private Size size;

    @NotNull
    private double price;

    @NotNull
    private int quantity;

    @NotNull
    private int product_id;

}
