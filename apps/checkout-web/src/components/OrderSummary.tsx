import { formatPrice } from "@/lib/format";
import type { Order } from "@/lib/orders-client";

const FREE_SHIPPING_THRESHOLD = 2000;

export function OrderSummary({ order }: { order: Order }) {
  const hasFreeShipping = order.totalPrice >= FREE_SHIPPING_THRESHOLD;

  return (
    <section className="max-w-md rounded-xl border p-6">
      <h1 className="text-xl font-semibold">Order {order.id}</h1>
      <ul className="mt-4 space-y-2">
        {order.items.map((item) => (
          <li key={item.sku} className="flex justify-between">
            <span>
              {item.name} x {item.quantity}
            </span>
            <span>{formatPrice(item.unitPrice * item.quantity)}</span>
          </li>
        ))}
      </ul>
      <div className="mt-4 flex justify-between border-t pt-4 font-semibold">
        <span>Total</span>
        <span>{formatPrice(order.totalPrice)}</span>
      </div>
      {hasFreeShipping && (
        <p className="mt-2 text-sm text-green-700">Free shipping</p>
      )}
      <p className="mt-1 text-sm text-gray-500">Status: {order.status}</p>
    </section>
  );
}
