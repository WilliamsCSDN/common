package org.williams.project.component.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 手动开启事务工具类
 */
@Component
@Slf4j
public class TransactionUtil {

    @Autowired
    private DataSourceTransactionManager transactionManager;

    /**
     * 开启事务（继承父级事务）
     * @return
     */
    public TransactionStatus begin(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        TransactionStatus transactionStatus = transactionManager.getTransaction(def);
        return transactionStatus;
    }

    /**
     * 开启事务（开启新事务）
     * @return
     */
    public TransactionStatus beginRequireNew(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        TransactionStatus transactionStatus = transactionManager.getTransaction(def);
        return transactionStatus;
    }

    /**
     * 提交事务
     * @param transactionStatus
     */
    public void commit(TransactionStatus transactionStatus){
        log.info("TransactionUtil commit");
        transactionManager.commit(transactionStatus);
    }

    /**
     * 回滚事务
     * @param transactionStatus
     */
    public void rollback(TransactionStatus transactionStatus){
        log.error("TransactionUtil rollback");
        transactionManager.rollback(transactionStatus);
    }

}
