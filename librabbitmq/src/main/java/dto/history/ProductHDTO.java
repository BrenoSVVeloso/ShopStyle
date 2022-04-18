package dto.history;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductHDTO implements Serializable{

    private int id;


    private String name;


    private String description;

 
    private String color;


    private Size size;

    private double price;

    private int quantity;
}
