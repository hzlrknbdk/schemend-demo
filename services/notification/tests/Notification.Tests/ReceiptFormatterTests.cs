using Notification;
using Notification.OrdersClient;

namespace Notification.Tests;

public class ReceiptFormatterTests
{
    private static Order OrderWith(double totalPrice) => new()
    {
        Id = "ord_test",
        TotalPrice = totalPrice,
        Status = OrderStatus.Paid,
    };

    [Fact]
    public void FormatsTheTotalWithTheHardcodedTlSuffix()
    {
        var receipt = ReceiptFormatter.Format(OrderWith(2499.9));

        Assert.Equal("Receipt for order ord_test: 2499.9 TL", receipt);
    }
}
