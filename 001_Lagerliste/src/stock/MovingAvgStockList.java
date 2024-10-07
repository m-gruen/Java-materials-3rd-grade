package stock;

public class MovingAvgStockList extends StockListImpl {

    // aktueller Lagerbestand
    private ValuedStockMovement stock;

    public MovingAvgStockList() {
        stock = null;
    }

    @Override
    public void store(ValuedStockMovement valuedStockMovement) {
        if (stock == null) {
            // WICHTIG: Im Lager wird ein neues Objekt abgelegt,
            // damit es keine Wechselwirkungen mit übergeordneten
            // Progammsystemen, gibt da wir vorhaben, Eigenschaften
            // des Objekts (z.B. Menge) zu verändern.
            stock = valuedStockMovement.clone();
        } else {
            // GLD - Berechnung
            double totalQuantity = stock.quantity + valuedStockMovement.quantity;
            double totalValue = stock.getValue()+ valuedStockMovement.getValue();
            double avgPricePerUnit = totalValue / totalQuantity;

            stock.quantity = totalQuantity;
            stock.pricePerUnit = avgPricePerUnit;
            stock.date = valuedStockMovement.date.clone();
        }

        // write to ingoing list
        ingoings.put(valuedStockMovement.clone());
    }

    @Override
    public void remove(StockMovement stockMovement) {
        // quit, if stock is empty
        if (stock == null) {
            return;
        }

        ValuedStockMovement outgoing = new ValuedStockMovement(
                stockMovement.date.clone(),
                0,
                stock.pricePerUnit); // Bewertung mit GLD

        if (stock.quantity <= stockMovement.quantity) {
            // Fall 1: Gesamte Lagermenge wird genommen
            outgoing.quantity = stock.quantity;
            stock = null;
        } else {
            // Fall 2: Im Lager verbleibt eine Restmenge
            outgoing.quantity = stockMovement.quantity;
            stock.quantity -= stockMovement.quantity;
        }

        outgoings.put(outgoing);
    }

    @Override
    public String getStockStatus() {
        if (stock == null) {
            return "\n";
        } else {
            return stock.toString() + "\n";
        }
    }
}
