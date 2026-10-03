export function formatPrice(amount: number, currency = "TRY"): string {
  return new Intl.NumberFormat("tr-TR", { style: "currency", currency }).format(
    amount,
  );
}
