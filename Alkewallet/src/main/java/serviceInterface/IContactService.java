package serviceInterface;

import java.util.List;

import modelDTO.ContactDTO;
import modelEntity.ContactEntity;

public interface IContactService {

    List<ContactEntity> getAllContactList();
    ContactEntity save(ContactDTO contactDTO,Long userId);
    ContactEntity getById(Long id);
    List<ContactEntity> getByUserEntityId(Long userId);
    List<ContactEntity> getByNameAndEmail(String name,String email);
    void deleteContact(Long contactId);
}
