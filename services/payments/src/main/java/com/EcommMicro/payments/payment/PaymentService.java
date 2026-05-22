package com.EcommMicro.payments.payment;

import com.EcommMicro.payments.notification.NotificationProducer;
import com.EcommMicro.payments.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepo repo;
    private final PaymentMapper mapper;
    private final NotificationProducer notification;
    public Integer createPayment(PaymentRequest request) {
        Payment payment=mapper.toPayment(request);
        notification.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),

                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        Payment savedPayment=repo.save(payment);
        return savedPayment.getId();
    }
}
