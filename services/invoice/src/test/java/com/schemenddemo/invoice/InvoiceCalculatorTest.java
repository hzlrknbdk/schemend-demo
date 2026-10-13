package com.schemenddemo.invoice;

import static org.assertj.core.api.Assertions.assertThat;

import com.schemenddemo.invoice.client.orders.model.Order;
import com.schemenddemo.invoice.client.orders.model.OrderStatus;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class InvoiceCalculatorTest {

  private final InvoiceCalculator invoiceCalculator = new InvoiceCalculator();

  private Order order(double totalPrice) {
    return new Order()
        .id("ord_test")
        .items(List.of())
        .totalPrice(BigDecimal.valueOf(totalPrice))
        .status(OrderStatus.PAID);
  }

  @Test
  void givesLoyaltyDiscountAtOrAboveTheThreshold() {
    assertThat(invoiceCalculator.isEligibleForLoyaltyDiscount(order(2000))).isTrue();
    assertThat(invoiceCalculator.isEligibleForLoyaltyDiscount(order(2499.9))).isTrue();
  }

  @Test
  void deniesLoyaltyDiscountBelowTheThreshold() {
    assertThat(invoiceCalculator.isEligibleForLoyaltyDiscount(order(1999.99))).isFalse();
  }
}
