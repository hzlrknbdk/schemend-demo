package com.schemenddemo.invoice;

import java.math.BigDecimal;

public record InvoiceView(String orderId, BigDecimal total, boolean loyaltyDiscountEligible) {}
