package roboVac;

public class MoveToTarget implements MoveBehaviour{

    private RoboVac roboVac;
    private Position target;
    private int[][] distanceMatrix;

    public MoveToTarget(RoboVac roboVac, Position target) {
        this.roboVac = roboVac;
        this.target = target;   
    }

    @Override
    public void init() {
        this.distanceMatrix = roboVac.getRoom().getDistanceMatrix(target);
    }

    public void setTarget(Position target) {
        this.target = target;
        init();
    }

    @Override
    public Position getNextMove() {
        Position currentPos = roboVac.getRoom().getRobotPosition();
        Position nextPos = currentPos;
        int currentDistance = distanceMatrix[currentPos.y][currentPos.x];
        int nextDistance = currentDistance;
    
        for (Position newPos : currentPos.getNeighbors()) {
            if (roboVac.getRoom().isAccessible(newPos)) {
                int newDistance = distanceMatrix[newPos.y][newPos.x];
                if (newDistance < nextDistance) {
                    nextPos = newPos;
                    nextDistance = newDistance;
                }
            }
        }
    
        return nextPos;
    }

}
