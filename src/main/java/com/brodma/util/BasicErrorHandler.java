package com.brodma.util;

import org.apache.logging.log4j.Logger;
import org.hibernate.ogm.compensation.BaseErrorHandler;
import org.hibernate.ogm.compensation.ErrorHandlingStrategy;
import org.hibernate.ogm.compensation.operation.CreateTuple;
import org.hibernate.ogm.compensation.operation.GridDialectOperation;
import org.hibernate.ogm.compensation.operation.InsertTuple;
import org.hibernate.ogm.compensation.operation.RemoveTuple;
import org.hibernate.ogm.dialect.spi.TupleAlreadyExistsException;
import org.hibernate.ogm.model.key.spi.EntityKey;
import org.hibernate.ogm.model.key.spi.EntityKeyMetadata;
import org.hibernate.ogm.model.spi.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicErrorHandler extends BaseErrorHandler {

    @Autowired
    private Logger logger;

    @Override
    public void onRollback(RollbackContext context) {
        for (GridDialectOperation appliedOperation : context.getAppliedGridDialectOperations()) {
            switch (appliedOperation.getType()) {
                case INSERT_TUPLE:
                    Tuple insertTuple = appliedOperation.as(InsertTuple.class).getTuple();
                    logger.info("Error inserting tuple {} ", insertTuple);
                    break;
                case REMOVE_TUPLE:
                    EntityKey entityKey = appliedOperation.as(RemoveTuple.class ).getEntityKey();
                    logger.info("Error removing entity key {} ", entityKey);
                    break;
                case CREATE_TUPLE:
                    EntityKeyMetadata entityKeyMetadata = appliedOperation.as(CreateTuple.class).getEntityKeyMetadata();
                    logger.info("Error creating tuple {} ", entityKeyMetadata);
                    break;
                default:
                    logger.info("Default case. Not handled.");
                    break;
            }
        }
    }

    @Override
    public ErrorHandlingStrategy onFailedGridDialectOperation(FailedGridDialectOperationContext context) {
        if ( context.getException() instanceof TupleAlreadyExistsException) {
            GridDialectOperation failedOperation = context.getFailedOperation();
            logger.info("Failed operation {} ", failedOperation);
            return ErrorHandlingStrategy.CONTINUE;
        }
        else {
            return ErrorHandlingStrategy.ABORT;
        }
    }
}
