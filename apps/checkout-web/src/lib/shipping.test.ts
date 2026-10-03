import { describe, expect, it } from "vitest";
import type { Order } from "./orders-client";
import { hasFreeShipping } from "./shipping";

const order = (totalPrice: number): Order => ({
  id: "ord_test",
  items: [],
  totalPrice,
  status: "paid",
});

describe("hasFreeShipping", () => {
  it("gives free shipping at or above the threshold", () => {
    expect(hasFreeShipping(order(2000))).toBe(true);
    expect(hasFreeShipping(order(2499.9))).toBe(true);
  });

  it("charges shipping below the threshold", () => {
    expect(hasFreeShipping(order(1999.99))).toBe(false);
  });
});
