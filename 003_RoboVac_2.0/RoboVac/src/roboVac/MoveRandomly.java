package roboVac;

import java.util.Random;

public class MoveRandomly implements MoveBehaviour {

    private Direction currentDirection;
    private RoboVac roboVac;

    public MoveRandomly(RoboVac roboVac) {
        this.roboVac = roboVac;
    }

    @Override
    public void init() {
        // choose new random direction
        Random rand = new Random();
        Direction[] directions = Direction.values();
        currentDirection = directions[rand.nextInt(directions.length)];
    }

    @Override
    public Position getNextMove() {
        Room room = roboVac.getRoom();

        Position newPos;

        do {
            newPos = new Position(
                    roboVac.getRoom().getRobotPosition().x + currentDirection.x,
                    roboVac.getRoom().getRobotPosition().y + currentDirection.y);

            if (!room.isAccessible(newPos) || new Random().nextInt(4) == 0) {
                init();
            }
        } while (!room.isAccessible(newPos));

        return newPos;
    }
}