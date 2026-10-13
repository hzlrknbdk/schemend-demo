package com.schemenddemo.invoice;

import com.schemenddemo.invoice.client.orders.model.Order;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class InvoiceCalculator {

  public static final BigDecimal LOYALTY_DISCOUNT_THRESHOLD = BigDecimal.valueOf(2000);

  public boolean isEligibleForLoyaltyDiscount(Order order) {
    return order.getTotalPrice().compareTo(LOYALTY_DISCOUNT_THRESHOLD) >= 0;
  }
}
