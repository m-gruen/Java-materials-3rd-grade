package roboVac;

import java.util.Random;

public class MoveRandomly implements MoveBehaviour {

    private int dirX;
    private int dirY;

    private static final int[][] DIRECTIONS = {
            // X, Y
            { 0, -1 }, // North
            { 1, 0 }, // East
            { 0, 1 }, // South
            { -1, 0 } // West
    };

    @Override
    public void init() {
        // choose dir randomly
        Random rand = new Random();
        int dir = rand.nextInt(0, DIRECTIONS.length);
        dirX = DIRECTIONS[dir][0];
        dirY = DIRECTIONS[dir][1];
    }

    @Override
    public void move(RoboVac roboVac) {
        Room room = roboVac.getRoom();

        int newPosX, newPosY;

        do {
            newPosX = room.getRobotPosX() + dirX;
            newPosY = room.getRobotPosY() + dirY;

            if (room.getStatus(newPosX, newPosY) == Status.WALL || new Random().nextInt(0, 4) == 0) {
                init();
            }
        } while (room.getStatus(newPosX, newPosY) == Status.WALL);

        room.setRobot(newPosX, newPosY);
    }
}
