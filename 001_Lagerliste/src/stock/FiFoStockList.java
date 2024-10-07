package stock;

public class FiFoStockList extends StockListImpl {

    protected Node head;
    protected Node tail;

    public FiFoStockList() {
        head = null;
        tail = null;
    }

    @Override
    public void store(ValuedStockMovement valuedStockMovement) {
        var newNode = new Node(valuedStockMovement.clone());

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;

        ingoings.put(valuedStockMovement.clone());
    }

    @Override
    public void remove(StockMovement stockMovement) {

        if (head == null) {
            return;
        } // empty list

        var outgoing = new ValuedStockMovement(
                stockMovement.date.clone(),
                0,
                head.valuedStockMovement.pricePerUnit);

        if (head.valuedStockMovement.quantity <= stockMovement.quantity) {
            outgoing.quantity = head.valuedStockMovement.quantity;
            outgoings.put(outgoing);

            var newStockMovement = new StockMovement(
                    stockMovement.date.clone(),
                    stockMovement.quantity - head.valuedStockMovement.quantity);

            head = head.next;
            remove(newStockMovement);
        } else {
            outgoing.quantity = stockMovement.quantity;
            outgoings.put(outgoing);
            head.valuedStockMovement.quantity -= stockMovement.quantity;
        }

    }

    @Override
    public String getStockStatus() {
        var sb = new StringBuilder();

        for (var current = head; current != null; current = current.next) {
            sb.append(current.valuedStockMovement.toString());
            if (current.next != null) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    protected class Node {
        ValuedStockMovement valuedStockMovement;
        Node next;

        Node(ValuedStockMovement valuedStockMovement) {
            this.valuedStockMovement = valuedStockMovement;
            this.next = null;
        }
    }
}
