package CrudPilot.Service;

import CrudPilot.Entity.TransactionLogDetails;

public interface TransactionService {

    TransactionLogDetails save(TransactionLogDetails txnLogObj);
}
