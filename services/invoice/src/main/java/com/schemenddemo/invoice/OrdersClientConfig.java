package com.schemenddemo.invoice;

import com.schemenddemo.invoice.client.orders.api.DefaultApi;
import com.schemenddemo.invoice.client.orders.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersClientConfig {

  @Bean
  public DefaultApi ordersApi(@Value("${orders.api.url}") String ordersApiUrl) {
    ApiClient apiClient = new ApiClient();
    apiClient.setBasePath(ordersApiUrl);
    return new DefaultApi(apiClient);
  }
}
