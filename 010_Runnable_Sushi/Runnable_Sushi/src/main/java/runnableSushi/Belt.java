package runnableSushi;

public class Belt extends Thread {

    private Food[] foodArr;

    /**
     *
     * @param capacity The capacity of the belt, i.e. the number of positions on the
     *                 belt
     */
    public Belt(int capacity) {
        foodArr = new Food[capacity];
    }

    /**
     * Checks if a position is valid on the belt
     * 
     * @param pos A position on the belt
     * @return true if the position is valid, false otherwise
     */
    public boolean isValidPosition(int pos) {
        return pos >= 0 && pos < foodArr.length;
    }

    /**
     * Checks if a position is free on the belt, so that food can be placed there
     * 
     * @param pos A position on the belt
     * @return true if the position is free, false otherwise
     */
    public boolean isFreeAtPosition(int pos) {
        return foodArr[pos] == null;
    }

    /**
     * Checks if the belt is empty, i.e. there is no food on it
     * 
     * @return true if the belt is empty, false otherwise
     */
    public boolean isEmpty() {
        for (Food food : foodArr) {
            if (food != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds food to the belt at a given position if the position is free
     * 
     * @param food The food to be added
     * @param pos  The position on the belt where the food should be added
     * @return true if the food was added successfully, false otherwise
     */
    public boolean add(Food food, int pos) {
        if (isFreeAtPosition(pos)) {
            foodArr[pos] = food;
            return true;
        }
        return false;
    }

    /**
     * Removes food from the belt at a given position
     * 
     * @param pos The position on the belt where the food should be removed
     * @return The food that was removed or null if there was no food at the given
     *         position
     */
    public Food remove(int pos) {
        var food = foodArr[pos];
        foodArr[pos] = null;
        return food;
    }

    /**
     * Moves all food on the belt one position to the right
     * The food on the last position becomes the first position
     */
    public void move() {
        var lastFood = foodArr[foodArr.length - 1];
        for (int i = foodArr.length - 1; i > 0; i--) {
            foodArr[i] = foodArr[i - 1];
        }
        foodArr[0] = lastFood;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            try {
                synchronized (this) {
                    this.move();
                    this.notifyAll();
                    System.out.println(this.toString());
                }
                Thread.sleep(500);
            } catch (InterruptedException ignore) {
                break; 
            }
        }

        System.out.println("Belt stopped");
    }

    /**
     * Returns a string representation of the belt
     * 
     * @return A string representation of the belt
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();

        for (int i = 0; i < foodArr.length; i++) {
            sb.append(String.format("-(%d:", i));
            if (foodArr[i] != null) {
                sb.append(String.format("%s)", foodArr[i].getId()));
            } else {
                sb.append("...)");
            }
        }

        return sb.toString();
    }

}
