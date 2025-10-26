package com.velomagaz.app.service;

import java.util.Base64;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.velomagaz.app.ViewModel.CartItemViewModel;
import com.velomagaz.app.ViewModel.CartViewModel;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final MailSender mailSender;


    public EmailService(MailSender myMailSender) {
        this.mailSender = myMailSender;
    }

    public void sendCartHtml(String to, String subject, CartViewModel cartViewModel, String name, String lastname, String phone) {
        JavaMailSenderImpl sender = mailSender.send();
        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("andrej.andretsov@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);

            StringBuilder html = new StringBuilder();
            html.append("<html><body>");
            html.append("<h2>Дані замовлення користувача: </h2>");
            
            html.append("<p><strong>Ім’я:</strong> ").append(name).append("</p>");
            html.append("<p><strong>Прізвище:</strong> ").append(lastname).append("</p>");
            html.append("<p><strong>Телефон:</strong> ").append(phone).append("</p>");
            
            html.append("<p><strong>Загальна кількість товарів:</strong> ")
                .append(cartViewModel.getItemCount())
                .append("</p>");
            html.append("<hr>");

            for (CartItemViewModel item : cartViewModel.getCartItems()) {
                html.append("<div style='margin-bottom: 20px;'>");

                html.append("<p><strong>Код товару:</strong> ")
                    .append(item.getId())
                    .append("</p>");

                html.append("<p><strong>Найменування:</strong> ")
                    .append(item.getProductName())
                    .append("</p>");

                html.append("<p><strong>Вартість:</strong> ")
                    .append(item.getPrice())
                    .append(" грн</p>");

                html.append("<p><strong>Кількість:</strong> ")
                    .append(item.getCount())
                    .append("</p>");

                html.append("</div>");
                html.append("<hr>");
            }

            html.append("</body></html>");

            helper.setText(html.toString(), true);
            sender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Помилка при надсиланні листа: " + e.getMessage(), e);
        }
    }
}
