package runnableSushi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {

    private String name;
    private FoodType foodType;
    private Belt belt;
    private int pos;
    private List<Food> producedFood;
    private Random random = new Random();

    private int lastFoodId = 1;

    /**
     * Constructor
     * 
     * @param name     name of the producer
     * @param foodType type of food produced
     * @param belt     belt to place the food on
     * @param pos      position on the belt to place the food
     */
    public Producer(String name, FoodType foodType, Belt belt, int pos) {
        this.name = name;
        this.foodType = foodType;
        this.belt = belt;
        this.pos = pos;
        this.producedFood = new ArrayList<>();
    }

    @Override
    public void run() {
        System.out.println(String.format("Producer %s starts producing at position %d ...", this.name, this.pos));

        try {
            while (!interrupted()) {
                var food = new Food(String.format("%s-%d", this.name, lastFoodId++), this.foodType);
                Thread.sleep(random.nextInt(1000, 2000));

                synchronized (belt) {
                    while (!belt.isFreeAtPosition(pos)) {
                        belt.wait();
                    }
                    belt.add(food, pos);
                }
                System.out.println(String.format("*** %s placed %s at position %d", this.name, food.getId(), pos));
                producedFood.add(food);
            }
        } catch (InterruptedException ignore) {
        }

        System.out.println(String.format("Producer %s stopped", this.name));
        System.out.println(this.getProducedFood());
    }

    /**
     * Returns a string representation of all produced food
     * 
     * @return a string representation of all produced food
     */
    public String getProducedFood() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Producer %s produced:", this.name));
        for (var food : producedFood) {
            sb.append(String.format(" %s |", food.getId()));
        }

        return sb.toString();
    }

}
