package agency.amazon.test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Account {
    @Id
    private String email;
    private String name;
    private String surname;
    @DocumentReference
    private AccountDetails details;
}
