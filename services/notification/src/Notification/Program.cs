using Notification;
using Notification.OrdersClient;

var builder = WebApplication.CreateBuilder(args);

var ordersApiUrl = Environment.GetEnvironmentVariable("ORDERS_API_URL") ?? "http://127.0.0.1:8001";

builder.Services.AddHttpClient();
builder.Services.AddScoped<IOrdersClient>(sp =>
{
    var httpClient = sp.GetRequiredService<IHttpClientFactory>().CreateClient();
    return new OrdersClient(ordersApiUrl, httpClient);
});

var app = builder.Build();

app.MapGet("/receipts/{orderId}", async (string orderId, IOrdersClient ordersClient) =>
{
    var order = await ordersClient.GetOrderAsync(orderId);
    return new ReceiptView(order.Id, order.TotalPrice, ReceiptFormatter.Format(order));
});

app.Run();

public partial class Program { }
