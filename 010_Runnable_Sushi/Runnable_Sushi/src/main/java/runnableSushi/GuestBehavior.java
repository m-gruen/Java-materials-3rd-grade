package runnableSushi;

import java.util.List;

public class GuestBehavior implements ConsumeBehavior {

    @Override
    public void consume(List<Food> foodList, Belt belt, int pos, String name) {
        while (!Thread.interrupted()) {
            try {
                synchronized (belt) {
                    while (belt.isFreeAtPosition(pos)) {
                        belt.wait();
                    }
                    var food = belt.remove(pos);
                    foodList.add(food);
    
                    System.out.println(String.format("*** %s consumed %s at position %d", name, food.getId(), pos));
                }
                Thread.sleep((long) (1000 + Math.random() * 5000)); // Sleep for a random time between 1 and 5 seconds         
            } catch (InterruptedException ignore) {
                break; // Exit the loop if interrupted
            }
        }
    }
}
