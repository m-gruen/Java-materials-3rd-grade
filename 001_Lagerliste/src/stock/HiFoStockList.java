package stock;

public class HiFoStockList extends FiFoStockList {

    @Override
    public void store(ValuedStockMovement valuedStockMovement) {
        // We need to have a sorted list to implement HiFo.
        // When a new stock movement is stored, we need to insert it in the right place.
        // The list is sorted by pricePerUnit and the most expensive stock is at the head.

        var newNode = new Node(valuedStockMovement.clone());

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            if (valuedStockMovement.pricePerUnit > head.valuedStockMovement.pricePerUnit) {
                newNode.next = head;
                head = newNode;
            } else {
                var current = head;
                while (current.next != null && current.next.valuedStockMovement.pricePerUnit >= valuedStockMovement.pricePerUnit) {
                    current = current.next;
                }

                newNode.next = current.next;
                current.next = newNode;

                if (newNode.next == null) {
                    tail = newNode;
                }
            }

        }

        ingoings.put(valuedStockMovement.clone());
    }

}
