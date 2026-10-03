import createClient from "openapi-fetch";
import type { components, paths } from "./orders-api";

export type Order = components["schemas"]["Order"];

export const ordersClient = createClient<paths>({
  baseUrl: process.env.ORDERS_API_URL ?? "http://127.0.0.1:8001",
});
