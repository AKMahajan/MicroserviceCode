package com.customer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
 public class RabbitConfig {
	//private final ConnectionFactory connectionFactory;
	public static final String QUEUE_ORDERS = "CustomerCreated";
    public static final String EXCHANGE_ORDERS = "customer-created";
 
    @Bean
    Queue ordersQueue() {
        return QueueBuilder.durable(QUEUE_ORDERS).build();
    }
 
    
 
    @Bean
    Exchange ordersExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_ORDERS).build();
    }
 
    @Bean
    Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(QUEUE_ORDERS);
    }
	@Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }
 
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
	
	/*@Autowired
	public RabbitConfig(ConnectionFactory connectionFactory) {
		this.connectionFactory=connectionFactory;
	}

	@Bean
	public RabbitTemplate template() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory);
		rabbitTemplate.setRoutingKey("customer");
		Jackson2JsonMessageConverter converter=new Jackson2JsonMessageConverter();
		converter.setClassMapper(classMapper());
		rabbitTemplate.setMessageConverter(converter);
		return rabbitTemplate;
	
	}

	@Bean
	public DefaultClassMapper classMapper() {
		DefaultClassMapper classMapper = new DefaultClassMapper();
		Map<String,Class<?>> idClassMapping=new HashMap<>();
		idClassMapping.put("com.customer.domain.Customer", Customer.class);
		classMapper.setIdClassMapping(idClassMapping);
		return classMapper;
	}*/
}
