package roboVac;

public class MoveVerticalFirst implements MoveBehaviour {

    private int dirX;
    private int dirY;
    private char nextDirectionVertical;
    private char currentDirectionHorizontal;
    private boolean lastMoveWasVertical;

    private static final int[][] DIRECTIONS = {
            // X, Y
            { 0, -1 }, // North
            { 1, 0 }, // East
            { 0, 1 }, // South
            { -1, 0 } // West
    };

    @Override
    public void init() {
        nextDirectionVertical = 'N';
        currentDirectionHorizontal = 'E';
        lastMoveWasVertical = false;

        dirX = DIRECTIONS[2][0];
        dirY = DIRECTIONS[2][1];
    }

    @Override
    public void move(RoboVac roboVac) {
        Room room = roboVac.getRoom();

        int newPosX, newPosY;
        do {
            if (lastMoveWasVertical) {
            }
            newPosX = room.getRobotPosX() + dirX;
            newPosY = room.getRobotPosY() + dirY;

            if (room.getStatus(newPosX, newPosY) == Status.WALL) {
            }
        } while (room.getStatus(newPosX, newPosY) == Status.WALL);

        room.setRobot(newPosX, newPosY);
    }

}
