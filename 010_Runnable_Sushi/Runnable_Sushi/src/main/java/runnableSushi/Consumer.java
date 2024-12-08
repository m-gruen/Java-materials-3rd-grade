package runnableSushi;

import java.util.ArrayList;
import java.util.List;

public class Consumer extends Thread {

    private String name;
    private ConsumeBehavior consumeBehavior;
    private Belt belt;
    private int pos;
    private List<Food> foodList;

    /**
     * Constructor
     * 
     * @param consumerType the type of consumer
     * @param name         the name of the consumer
     * @param belt         the belt from which the consumer consumes food
     * @param pos          the position on the belt from which the consumer consumes
     *                     food
     */
    public Consumer(ConsumerType consumerType, String name, Belt belt, int pos) {
        this.name = name;
        this.belt = belt;
        this.pos = pos;
        switch (consumerType) {
            case GUEST:
                consumeBehavior = new GuestBehavior();
                break;
            case CLEANER:
                consumeBehavior = new CleanerBehavior();
                break;
        }
        this.foodList = new ArrayList<>();
    }

    @Override
    public void run() {
        System.out.println(String.format("Consumer %s starts consuming at position %d ..", this.name, this.pos));

        consumeBehavior.consume(foodList, belt, pos, name);
    
        System.out.println(String.format("Consumer %s stopped", this.name));
        System.out.println(this.getConsumedFood());
    }

    /**
     * Returns a string representation of all consumed food
     * @return a string representation of all consumed food
     */
    public String getConsumedFood() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s took:", this.name));
        for (var food : foodList) {
            sb.append(String.format(" %s |", food.getId()));
        }

        return sb.toString();
    }
}