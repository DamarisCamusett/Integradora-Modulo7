package serviceInterface;

import java.util.List;

import modelEntity.TransactionEntity;

public interface ITransactionService {

    List<TransactionEntity> getAllTransaction();
    List<TransactionEntity> getByUserEntityId(Long userId);
    void saveTransaction(TransactionEntity transaction);
    TransactionEntity getById(Long id);
}
