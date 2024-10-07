package roboVac;

public class RoboVac {

    private String name;
    private Room room;
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

    public void setPosition(int posX, int posY) {
        room.setRobot(posX, posY);
    }

    public void printRoomStatus() {
        System.out.println(room);
    }

    public void clean() {
        room.setAllDirty();
        moveBehaviour.init();
        do {
            printRoomStatus();
            room.setCleanAtRobotPosition();
            moveBehaviour.move(this);
        } while (!room.isClean());

        System.out.println("Room is clean!");
    }

}
