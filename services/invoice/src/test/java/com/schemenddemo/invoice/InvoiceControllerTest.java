package com.schemenddemo.invoice;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.schemenddemo.invoice.client.orders.api.DefaultApi;
import com.schemenddemo.invoice.client.orders.model.Order;
import com.schemenddemo.invoice.client.orders.model.OrderStatus;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(InvoiceController.class)
@Import(InvoiceCalculator.class)
class InvoiceControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private DefaultApi ordersApi;

  @Test
  void returnsTheOrderTotalAndLoyaltyDiscountEligibility() throws Exception {
    Order order =
        new Order()
            .id("ord_1")
            .items(List.of())
            .totalPrice(BigDecimal.valueOf(2499.9))
            .status(OrderStatus.PAID);
    when(ordersApi.getOrder("ord_1")).thenReturn(order);

    mockMvc
        .perform(get("/invoices/ord_1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.orderId", is("ord_1")))
        .andExpect(jsonPath("$.total", is(2499.9)))
        .andExpect(jsonPath("$.loyaltyDiscountEligible", is(true)));
  }
}
