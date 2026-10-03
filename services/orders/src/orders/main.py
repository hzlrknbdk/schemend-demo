from enum import Enum

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

app = FastAPI(title="Orders Service", version="1.0.0")


class OrderStatus(str, Enum):
    pending = "pending"
    paid = "paid"
    shipped = "shipped"


class OrderItem(BaseModel):
    sku: str
    name: str
    quantity: int
    unitPrice: float


class Order(BaseModel):
    id: str
    items: list[OrderItem]
    totalPrice: float
    status: OrderStatus


ORDERS = {
    "ord_1": Order(
        id="ord_1",
        items=[OrderItem(sku="SKU-1", name="Coffee machine", quantity=1, unitPrice=2499.9)],
        totalPrice=2499.9,
        status=OrderStatus.paid,
    ),
}


@app.get("/orders", response_model=list[Order], operation_id="listOrders")
def list_orders() -> list[Order]:
    return list(ORDERS.values())


@app.get("/orders/{order_id}", response_model=Order, operation_id="getOrder")
def get_order(order_id: str) -> Order:
    order = ORDERS.get(order_id)
    if order is None:
        raise HTTPException(status_code=404, detail="Order not found")
    return order