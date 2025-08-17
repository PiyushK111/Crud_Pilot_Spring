package CrudPilot.Commons;

import CrudPilot.Entity.TransactionLogDetails;
import CrudPilot.ServiceImplementations.TransactionLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

@Service
public class TransactionLogger {

    Logger logger = LoggerFactory.getLogger(TransactionLogger.class);

    @Autowired
    TransactionLogService txnLogService;

    public void logTransaction(Map<String, Object> request){

        String callingClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();


        logger.info("logging Transaction for [{}] : [{}]", callingClassName, callingMethodName);

        TransactionLogDetails txnLogObj = new TransactionLogDetails();

        txnLogObj.setCreatedBy(request.get("createdBy").toString());
        txnLogObj.setCreatedDate(Timestamp.from(Instant.now()));
        txnLogObj.setOperation(callingClassName + " : " + callingMethodName);
        txnLogObj.setRequest(request.toString());

        txnLogService.save(txnLogObj);

        logger.info("Transaction logging Completed");


    }

}
