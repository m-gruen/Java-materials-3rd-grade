package runnableSushi;

import java.util.List;

public interface ConsumeBehavior {
    	
    public void consume(List<Food> foodList, Belt belt, int pos, String name);
}
