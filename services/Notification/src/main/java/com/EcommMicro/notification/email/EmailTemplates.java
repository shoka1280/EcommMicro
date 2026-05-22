package com.EcommMicro.notification.email;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html","Payment successfully processed"),
    Order_CONFIRMATION("order-confirmation.html","Order successfully placed");

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

    @Getter
    private final String template;
    @Getter
    private final String subject;
}
