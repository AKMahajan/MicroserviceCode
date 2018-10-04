package com.salesOrder.config;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class RabbitListenerConfig implements RabbitListenerConfigurer{

	public static final String QUEUE_ORDERS = "CustomerCreated";
	@Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
 
    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }
 
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
	
 //private final ConnectionFactory connectionFactory;
	
	/*@Autowired
	public RabbitListenerConfig(ConnectionFactory connectionFactory) {
		this.connectionFactory=connectionFactory;
	}
	
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
		
		converter.setClassMapper(classMapper());
		factory.setMessageConverter(converter);
		return factory;
		
	}
	
	@Bean
	public DefaultClassMapper classMapper() {
		DefaultClassMapper classMapper = new DefaultClassMapper();
		Map<String,Class<?>> idClassMapping=new HashMap<>();
		idClassMapping.put("com.salesOrder.domain.Customer", Customer.class);
		classMapper.setIdClassMapping(idClassMapping);
		return classMapper;
	}
	
	@Bean 
	public Queue queue() {
		return new Queue("customer",false);
	}*/

}
