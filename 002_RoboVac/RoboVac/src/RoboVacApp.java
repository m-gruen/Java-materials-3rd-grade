import roboVac.RoboVac;
import roboVac.Room;

public class RoboVacApp {
    public static void main(String[] args) {
        var myRoboVac = new RoboVac("RoboVac EG");
        var myRoom = new Room(new String[] {
                "######",
                "#    #",
                "#    #",
                "#    #",
                "######"
        });
        myRoboVac.setRoom(myRoom);
        myRoboVac.setMoveBehaviour(new roboVac.MoveVerticalFirst());
        myRoboVac.setPosition(1, 1);
        myRoboVac.clean();
        myRoboVac.setPosition(1, 1);
        myRoboVac.clean();
    }
}
