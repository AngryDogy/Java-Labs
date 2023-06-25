package com.example.buildings;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("main");
        connectionFactory.setPassword("main");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
    @Bean
    public Queue streetsGetQueue() {
        return new Queue("streets-get", true);
    }
    @Bean
    public Queue streetsPostQueue() {
        return new Queue("streets-post", true);
    }
    @Bean
    public Queue streetsPutQueue() {
        return new Queue("streets-put", true);
    }
    @Bean
    public Queue streetsDeleteQueue() {
        return new Queue("streets-delete", true);
    }
    @Bean
    public Queue streetsResultQueue() {
        return new Queue("streets-result", true);
    }

    @Bean
    public Queue buildingsGetQueue() {
        return new Queue("buildings-get", true);
    }
    @Bean
    public Queue buildingsResultQueue() {
        return new Queue("buildings-result", true);
    }

    @Bean
    public Queue buildingsPostQueue() {
        return new Queue("buildings-post", true);
    }
    @Bean
    public Queue buildingsPutQueue() {
        return new Queue("buildings-put", true);
    }
    @Bean
    public Queue buildingsDeleteQueue() {
        return new Queue("buildings-delete", true);
    }

    @Bean
    public TopicExchange streetsExchange() {
        return new TopicExchange("streets-exchange");
    }
    @Bean
    public TopicExchange buildingsExchange() {
        return new TopicExchange("buildings-exchange");
    }

    @Bean
    public Binding buildingsGetBinding() {
        return BindingBuilder
                .bind(buildingsGetQueue())
                .to(buildingsExchange())
                .with("buildings.get");
    }
    @Bean
    public Binding buildingsPostBinding() {
        return BindingBuilder
                .bind(buildingsPostQueue())
                .to(buildingsExchange())
                .with("buildings.post");
    }
    @Bean
    public Binding buildingsPutBinding() {
        return BindingBuilder
                .bind(buildingsPutQueue())
                .to(buildingsExchange())
                .with("buildings.put");
    }
    @Bean
    public Binding buildingsDeleteBinding() {
        return BindingBuilder
                .bind(buildingsDeleteQueue())
                .to(buildingsExchange())
                .with("buildings.delete");
    }
    @Bean
    public Binding buildingsResultBinding() {
        return BindingBuilder
                .bind(buildingsResultQueue())
                .to(buildingsExchange())
                .with("buildings.result");
    }

    @Bean
    public Binding streetsGetBinding() {
        return BindingBuilder
                .bind(streetsGetQueue())
                .to(streetsExchange())
                .with("streets.get");
    }

    @Bean
    public Binding streetsResultBinding() {
        return BindingBuilder
                .bind(streetsResultQueue())
                .to(streetsExchange())
                .with("streets.result");
    }
    @Bean
    public Binding streetsPutBinding() {
        return BindingBuilder
                .bind(streetsGetQueue())
                .to(streetsExchange())
                .with("streets.put");
    }
    @Bean
    public Binding streetsPostBinding() {
        return BindingBuilder
                .bind(streetsPostQueue())
                .to(streetsExchange())
                .with("streets.post");
    }
    @Bean
    public Binding streetsDeleteBinding() {
        return BindingBuilder
                .bind(streetsDeleteQueue())
                .to(streetsExchange())
                .with("streets.delete");
    }



}
