package repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modelEntity.ContactEntity;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity,Long> {

    List<ContactEntity> findByUserEntityId(Long userId);
    List<ContactEntity> findByNameAndEmail(String name,String email);
}
