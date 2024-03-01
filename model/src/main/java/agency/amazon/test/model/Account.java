package agency.amazon.test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String email;
    private String name;
    private String surname;
    private AccountDetails details;
}
