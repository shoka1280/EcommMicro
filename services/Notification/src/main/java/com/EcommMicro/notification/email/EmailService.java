package com.EcommMicro.notification.email;

import com.EcommMicro.notification.kafka.Order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sentPaymentSuccessEmail(String destinationEmail,
                                        String customerName,
                                        BigDecimal amount,String orderRefrence) throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("contact@shreshthcoding.com");
        final String templateName=EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

        Map<String,Object> variables=new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("orderRefrence",orderRefrence);
        variables.put("amount",amount);

        Context context=new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

        try{
            String htmlTemplate=templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);

            log.info(String.format("INFO-Email successfull sent to %s with template %s ",destinationEmail,templateName));
        }
        catch (MessagingException e){
                log.warn("Cannnot send emai "+destinationEmail);
        }
    }
    @Async
    public void sentOrderConfirmationEmail(String destinationEmail,
                                           String customerName,
                                           BigDecimal amount, String orderRefrence, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("contact@shreshthcoding.com");
        final String templateName=EmailTemplates.Order_CONFIRMATION.getTemplate();

        Map<String,Object> variables=new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("orderRefrence",orderRefrence);
        variables.put("totalamount",amount);
        variables.put("products",orderRefrence);


        Context context=new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplates.Order_CONFIRMATION.getSubject());

        try{
            String htmlTemplate=templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);

            log.info(String.format("INFO-Email successfullt sent to %s with template %s ",destinationEmail,templateName));
        }
        catch (MessagingException e){
            log.warn("Cannnot send emai "+destinationEmail);
        }
    }

}
