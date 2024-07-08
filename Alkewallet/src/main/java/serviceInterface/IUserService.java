package serviceInterface;

import java.util.List;

import modelDTO.UserDTO;
import modelEntity.UserEntity;

public interface IUserService {

    UserEntity createUser(UserDTO userDTO);
    UserEntity getById(Long id);
    UserEntity getUserByUsername(String username);
    UserEntity loginUser(String username,String password);
    List<UserEntity> getAllUserList();
    UserEntity getReceiverAccount(String username,String email);
    UserEntity getByEmail(String email);
}
