package modelDTO;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
}
