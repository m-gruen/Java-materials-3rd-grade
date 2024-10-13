package roboVac;

public class MoveVerticalFirst implements MoveBehaviour {

    private Direction currentDirection;
    private Direction verticalDirection;
    private Direction horizontalDirection;
    private boolean lastMoveWasVertical;
    private RoboVac roboVac;

    public MoveVerticalFirst(RoboVac roboVac) {
        this.roboVac = roboVac;
    }

    @Override
    public void init() {
        verticalDirection = Direction.SOUTH;
        horizontalDirection = Direction.WEST;
        currentDirection = verticalDirection;
        lastMoveWasVertical = true;
    }

    @Override
    public Position getNextMove() {
        Room room = roboVac.getRoom();

        Position newPos;

        do {
            newPos = new Position(
                    roboVac.getRoom().getRobotPosition().x + currentDirection.x,
                    roboVac.getRoom().getRobotPosition().y + currentDirection.y);

            if (!room.isAccessible(newPos)) {
                switchDirection();
            } else if (!lastMoveWasVertical) {
                switchToVertical();
            }
        } while (!room.isAccessible(newPos));

        return newPos;
    }

    private void switchDirection() {
        if (lastMoveWasVertical) {
            currentDirection = horizontalDirection;
            lastMoveWasVertical = false;
        } else {
            horizontalDirection = (horizontalDirection == Direction.WEST) ? Direction.EAST : Direction.WEST;
            currentDirection = verticalDirection;
            lastMoveWasVertical = true;
        }
    }

    private void switchToVertical() {
        horizontalDirection = currentDirection;
        verticalDirection = (verticalDirection == Direction.SOUTH) ? Direction.NORTH : Direction.SOUTH;
        currentDirection = verticalDirection;
        lastMoveWasVertical = true;
    }

}
