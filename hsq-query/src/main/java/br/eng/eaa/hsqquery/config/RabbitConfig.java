package br.eng.eaa.hsqquery.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_AGENDAMENTO = "exchange-agendamentos";
    public static final String QUEUE_HSQ_QUERY = "fila-hsq-query";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_AGENDAMENTO);
    }

    @Bean
    public Queue hsqQueryQueue() {
        return new Queue(QUEUE_HSQ_QUERY, true);
    }

    @Bean
    public Binding bindingHsqQuery(FanoutExchange fanoutExchange, Queue hsqQueryQueue) {
        return BindingBuilder.bind(hsqQueryQueue).to(fanoutExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
