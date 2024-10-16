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
        var robotPos = roboVac.getRoom().getRobotPosition();
        if (robotPos.equals(target)) {
            return null;
        }

        Position pos = null;

        for (Position neighbor : robotPos.getNeighbors()) {
            if (distanceMatrix[neighbor.y][neighbor.x] != -1
                    && (pos == null || distanceMatrix[neighbor.y][neighbor.x] < distanceMatrix[pos.y][pos.x])) {
                pos = neighbor;
            }
        }

        return pos;
    }

}
