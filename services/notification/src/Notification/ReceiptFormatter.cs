using System.Globalization;
using Notification.OrdersClient;

namespace Notification;

public static class ReceiptFormatter
{
    // CurrentCulture's decimal separator varies by machine (comma vs. period); money text must
    // not depend on it.
    public static string Format(Order order)
    {
        var total = order.TotalPrice.ToString(CultureInfo.InvariantCulture);
        return $"Receipt for order {order.Id}: {total} TL";
    }
}
