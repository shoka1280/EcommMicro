package com.EcommMicro.notification.kafka;

import com.EcommMicro.notification.email.EmailService;
import com.EcommMicro.notification.kafka.Order.OrderConfirmation;
import com.EcommMicro.notification.kafka.Payment.PaymentConfirmation;
import com.EcommMicro.notification.notification.Notification;
import com.EcommMicro.notification.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.EcommMicro.notification.notification.NotificationType.ORDER_CONFIRMATION;
import static com.EcommMicro.notification.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;

    //private final EmailService emailService;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotfication(PaymentConfirmation confirmation) throws MessagingException {
                 log.info(String.format("Consuming the messsage from payment topic from topic:",confirmation));
                 repository.save(
                         Notification.builder()
                         .type(PAYMENT_CONFIRMATION).
                         notificationDate(LocalDateTime.now())
                         .paymentConfirmation(confirmation)
                         .build()
                 );
                 var customerName=confirmation.customerFirstname()+" "+confirmation.customerLastname();
                 emailService.sentPaymentSuccessEmail(
                         confirmation.customerEmail(),
                         customerName,
                         confirmation.amount(),
                         confirmation.orderReference()
                 );
    }
    //todo:Send email here

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotfication(OrderConfirmation confirmation) throws MessagingException {
        log.info(String.format("Consuming the messsage from order topic from topic:",confirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION).
                        notificationDate(LocalDateTime.now())
                        .orderConfirmation(confirmation)
                        .build()
        );
        var custommerName=confirmation.customer().firstname()+" "+confirmation.customer().lastname();
        emailService.sentOrderConfirmationEmail (
                confirmation.customer().email(),
                custommerName,
                confirmation.totalAmount(),
                confirmation.orderReference(),
                confirmation.products()

        );

    };
    //todo:Send email here


}
