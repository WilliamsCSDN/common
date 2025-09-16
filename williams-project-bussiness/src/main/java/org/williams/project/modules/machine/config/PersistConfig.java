//package org.williams.project.modules.machine.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.statemachine.StateMachinePersist;
//import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
//import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
//import org.springframework.statemachine.redis.RedisStateMachinePersister;
//import org.springframework.stereotype.Component;
//import org.williams.project.enums.OrderEvent;
//import org.williams.project.enums.OrderStatus;
//
//@Component
//public class PersistConfig {
//
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//
//    /**
//     * 注入RedisStateMachinePersister对象
//     *
//     * @return
//     */
//    @Bean(name = "orderRedisPersister")
//    public RedisStateMachinePersister<OrderStatus, OrderEvent> redisPersister() {
//        return new RedisStateMachinePersister<>(redisPersist());
//    }
//
//    /**
//     * 通过redisConnectionFactory创建StateMachinePersist
//     *
//     * @return
//     */
//    public StateMachinePersist<OrderStatus, OrderEvent,String> redisPersist() {
//        RedisStateMachineContextRepository<OrderStatus, OrderEvent> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory);
//        return new RepositoryStateMachinePersist<>(repository);
//    }
//
//}