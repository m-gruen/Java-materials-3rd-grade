import roboVac.*;

public class RoboVacApp {
    public static void main(String[] args) {
        var myRoboVac = new RoboVac("RoboVac EG");
        var myRoom = new Room(new String[] {
                "############",
                "#          #",
                "#          #",
                "#    ####  #",
                "#    #  #  #",
                "#    #     #",
                "#    #     #",
                "############"
        });
        myRoboVac.setRoom(myRoom);
        myRoboVac.setPosition(new Position(1, 1));
        myRoboVac.clean();
        myRoboVac.clean();

    }

}
