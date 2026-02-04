package br.eng.eaa.notifacationservice.message;

import br.eng.eaa.notifacationservice.model.EmailMessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final JavaMailSender mailSender;

    public EmailConsumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "fila-emails")
    public void consumeMessage(EmailMessageDTO message) {
        System.out.println("Recebendo mensagem para: " + message.to());

        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom("noreply@healtsmartqueue.com");
            email.setTo(message.to());
            email.setSubject(message.subject());
            email.setText(message.body());

            mailSender.send(email);
            System.out.println("E-mail enviado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}
