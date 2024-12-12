package runnableSushi;

import java.util.List;

public class GuestBehavior implements ConsumeBehavior {

    @Override
    public void consume(List<Food> foodList, Belt belt, int pos, String name) {

        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (belt) {
                    while (belt.isFreeAtPosition(pos)) {
                        belt.wait();
                    }
                    var food = belt.remove(pos);
                    foodList.add(food);

                    System.out.println(String.format("*** %s consumed %s at position %d", name, food.getId(), pos));
                }
                Thread.sleep((long) (1000 + Math.random() * 4000));
            }
        } catch (InterruptedException ignore) {
        }
    }
}
