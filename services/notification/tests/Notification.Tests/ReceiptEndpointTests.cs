using System.Net.Http.Json;
using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.DependencyInjection.Extensions;
using Notification.OrdersClient;
using NSubstitute;

namespace Notification.Tests;

public class ReceiptEndpointTests : IClassFixture<WebApplicationFactory<Program>>
{
    private readonly WebApplicationFactory<Program> _factory;

    public ReceiptEndpointTests(WebApplicationFactory<Program> factory)
    {
        _factory = factory;
    }

    [Fact]
    public async Task ReturnsTheReceiptForAnExistingOrder()
    {
        var ordersClient = Substitute.For<IOrdersClient>();
        ordersClient.GetOrderAsync("ord_1").Returns(new Order
        {
            Id = "ord_1",
            TotalPrice = 2499.9,
            Status = OrderStatus.Paid,
        });

        var client = _factory.WithWebHostBuilder(builder =>
        {
            builder.ConfigureServices(services =>
            {
                services.RemoveAll<IOrdersClient>();
                services.AddScoped<IOrdersClient>(_ => ordersClient);
            });
        }).CreateClient();

        var receipt = await client.GetFromJsonAsync<ReceiptView>("/receipts/ord_1");

        Assert.NotNull(receipt);
        Assert.Equal("ord_1", receipt!.OrderId);
        Assert.Equal(2499.9, receipt.Total);
        Assert.Equal("Receipt for order ord_1: 2499.9 TL", receipt.ReceiptText);
    }
}
