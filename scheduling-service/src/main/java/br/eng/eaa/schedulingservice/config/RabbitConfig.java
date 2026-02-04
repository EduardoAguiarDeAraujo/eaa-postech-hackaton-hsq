package br.eng.eaa.schedulingservice.config;

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
    public static final String QUEUE_SCHEDULING = "fila-scheduling-service";

    public static final String QUEUE_EMAIL = "fila-emails";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_AGENDAMENTO);
    }

    @Bean
    public Queue schedulingQueue() {
        return new Queue(QUEUE_SCHEDULING, true);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(QUEUE_EMAIL, true); // O produtor pode declarar a fila se ela for Direct
    }

    @Bean
    public Binding bindingScheduling(FanoutExchange fanoutExchange, Queue schedulingQueue) {
        return BindingBuilder.bind(schedulingQueue).to(fanoutExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
