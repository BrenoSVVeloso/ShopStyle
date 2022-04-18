package dto.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int id;

    private String name;

    private String description;

    private boolean active;

    private int[] category_ids;

    private VariationDTO variation;

    public boolean getActive(){
        return this.active;
    }
}
