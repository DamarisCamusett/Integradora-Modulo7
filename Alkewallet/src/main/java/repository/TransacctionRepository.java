package repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modelEntity.TransactionEntity;

import java.util.List;

@Repository
public interface TransacctionRepository extends JpaRepository<TransactionEntity,Long> {

    List<TransactionEntity> findByUserSenderId(Long userId);
    List<TransactionEntity> findByUserReceiverId(Long userId);
    List<TransactionEntity> findByContactReceiverId(Long contactReceiverId);
}
