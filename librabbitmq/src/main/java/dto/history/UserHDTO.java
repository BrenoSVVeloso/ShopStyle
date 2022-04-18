package dto.history;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHDTO implements Serializable{

    private int id;

    private String firstName;

    private String lastName;

    private Sex sex;
    
    private String cpf;


    private LocalDate birthdate;
    
    
    private String email;
}
