import { notFound } from "next/navigation";
import { OrderSummary } from "@/components/OrderSummary";
import { ordersClient } from "@/lib/orders-client";

export default async function OrderPage({
  params,
}: {
  params: Promise<{ id: string }>;
}) {
  const { id } = await params;
  const { data, error } = await ordersClient.GET("/orders/{order_id}", {
    params: { path: { order_id: id } },
  });

  if (error || !data) notFound();

  return (
    <main className="p-8">
      <OrderSummary order={data} />
    </main>
  );
}
