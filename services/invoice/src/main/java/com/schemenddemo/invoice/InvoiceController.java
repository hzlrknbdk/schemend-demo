package com.schemenddemo.invoice;

import com.schemenddemo.invoice.client.orders.api.DefaultApi;
import com.schemenddemo.invoice.client.orders.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

  private final DefaultApi ordersApi;
  private final InvoiceCalculator invoiceCalculator;

  public InvoiceController(DefaultApi ordersApi, InvoiceCalculator invoiceCalculator) {
    this.ordersApi = ordersApi;
    this.invoiceCalculator = invoiceCalculator;
  }

  @GetMapping("/invoices/{orderId}")
  public InvoiceView getInvoice(@PathVariable String orderId) {
    Order order = ordersApi.getOrder(orderId);
    return new InvoiceView(
        order.getId(), order.getTotalPrice(), invoiceCalculator.isEligibleForLoyaltyDiscount(order));
  }
}
