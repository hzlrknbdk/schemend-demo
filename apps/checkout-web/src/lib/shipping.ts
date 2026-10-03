import type { Order } from "./orders-client";

export const FREE_SHIPPING_THRESHOLD = 2000;

export function hasFreeShipping(order: Order): boolean {
  return order.totalPrice >= FREE_SHIPPING_THRESHOLD;
}
