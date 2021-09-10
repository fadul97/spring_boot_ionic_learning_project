package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);

    }

    @Override
    public void sendEmail(SimpleMailMessage msg) {

    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {

    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Order confirmed! Code: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }

    @Override
    public void sendNewPasswordEmail(Client client, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(client, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(client.getEmail());
        sm.setFrom(sender);
        sm.setSubject("New password Request: ");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("New password: " + newPass);
        return sm;
    }

    protected String htmlFromTemplatePedido(Pedido obj){
        Context context = new Context();
        context.setVariable("pedido", obj);
        return templateEngine.process("email/pedidoConfirmation", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido obj){
        try {
            MimeMessage mm = prepareMimeMessageFromPedido(obj);
            sendHtmlEmail(mm);
        } catch (MessagingException e){
            sendOrderConfirmationEmail(obj);
        }
    }

    private MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(obj.getClient().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Order confirmed! Code: " + obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(obj), true);
        return mimeMessage;
    }
}
