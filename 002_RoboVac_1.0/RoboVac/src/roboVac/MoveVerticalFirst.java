package roboVac;

public class MoveVerticalFirst implements MoveBehaviour {

    private Direction currentDirection;
    private Direction verticalDirection;
    private Direction horizontalDirection;
    private boolean lastMoveWasVertical;

    @Override
    public void init() {
        verticalDirection = Direction.SOUTH;
        horizontalDirection = Direction.WEST;
        currentDirection = verticalDirection;
        lastMoveWasVertical = true;
    }

    @Override
    public void move(RoboVac roboVac) {
        Room room = roboVac.getRoom();
        int newPosX, newPosY;

        do {
            newPosX = room.getRobotPosX() + currentDirection.x;
            newPosY = room.getRobotPosY() + currentDirection.y;

            if (room.getStatus(newPosX, newPosY) == Status.WALL) {
                switchDirection();
            } else if (!lastMoveWasVertical) {
                switchToVertical();
            }
        } while (room.getStatus(newPosX, newPosY) == Status.WALL);

        room.setRobot(newPosX, newPosY);
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
