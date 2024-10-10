package roboVac;

import java.util.Random;

public class MoveRandomly implements MoveBehaviour {

    private Direction currentDirection;

    @Override
    public void init() {
        // choose new random direction
        Random rand = new Random();
        Direction[] directions = Direction.values();
        currentDirection = directions[rand.nextInt(directions.length)];
    }

    @Override
    public void move(RoboVac roboVac) {
        Room room = roboVac.getRoom();

        int newPosX, newPosY;

        do {
            newPosX = room.getRobotPosX() + currentDirection.x;
            newPosY = room.getRobotPosY() + currentDirection.y;

            if (room.getStatus(newPosX, newPosY) == Status.WALL || new Random().nextInt(4) == 0) {
                init();
            }
        } while (room.getStatus(newPosX, newPosY) == Status.WALL);

        room.setRobot(newPosX, newPosY);
    }
}