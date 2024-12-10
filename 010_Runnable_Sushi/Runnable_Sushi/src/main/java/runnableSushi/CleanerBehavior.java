package runnableSushi;

import java.util.List;

public class CleanerBehavior implements ConsumeBehavior {

    @Override
    public void consume(List<Food> foodList, Belt belt, int pos, String name) {
        while (!Thread.interrupted()) {
            try {
                synchronized (belt) {
                    if (belt.isEmpty()) {
                        break;
                    }
                    while (belt.isFreeAtPosition(pos)) {
                        belt.wait();
                    }
                    var food = belt.remove(pos);
                    foodList.add(food);
    
                    System.out.println(String.format("*** %s consumed %s at position %d", name, food.getId(), pos));
                }
                // Does not sleep    
            } catch (Exception ignore) {
                break;
            }
        }
    }

}
