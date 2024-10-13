package roboVac;

public class RoboVac {

    private String name;
    private Room room;
    private int moveCount;
    private MoveBehaviour moveBehaviour;

    public RoboVac(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MoveBehaviour getMoveBehaviour() {
        return moveBehaviour;
    }

    public void setMoveBehaviour(MoveBehaviour moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setPosition(Position pos) {
        room.setRobotPosition(pos);
    }

    public void clean() {

        resetMoveCount();
        room.setAllDirty();
        room.setCleanAtRobotPosition();
        moveBehaviour = new MoveVerticalFirst(this);
        moveBehaviour.init();

        while (!room.isClean()) {
            Position nextPos = moveBehaviour.getNextMove();
            if (room.isClean(nextPos)) {
                moveToTarget(room.getNearestDirtyPosition(nextPos));
            } else {
                setPosition(nextPos);
                room.setCleanAtRobotPosition();
                printRoomStatus();
                System.out.println("Cleaning...\n");
                incrementMoveCount();
            }
        }

        System.out.println("Room is clean!");
        System.out.println("Total moves: " + moveCount);
    }

    public void moveToTarget(Position target) {
        moveBehaviour = new MoveToTarget(this, target);
        moveBehaviour.init();

        while (!room.getRobotPosition().equals(target)) {
            setPosition(moveBehaviour.getNextMove());
            room.setCleanAtRobotPosition();
            printRoomStatus();
            System.out.println("Moving to target...\n");
            incrementMoveCount();
        }

        room.setCleanAtRobotPosition();
        moveBehaviour = new MoveVerticalFirst(this);
        moveBehaviour.init();
    }

    public void printRoomStatus() {
        System.out.println(room.getLayout());
    }

    private void resetMoveCount() {
        moveCount = 0;
    }
    
    private void incrementMoveCount() {
        moveCount++;
    }

}
