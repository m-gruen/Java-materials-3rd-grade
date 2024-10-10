package roboVac;

public class Room {
    private Status[][] layout;
    private int robotPosX;
    private int robotPosY;

    public Room(String[] layout) {
        this.layout = new Status[layout.length][layout[0].length()];
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length(); x++) {
                this.layout[y][x] = Status.valueOf(layout[y].charAt(x));
            }
        }
    }

    public void setRobot(int posX, int posY) {
        robotPosX = posX;
        robotPosY = posY;
    }

    public int getRobotPosX() {
        return robotPosX;
    }

    public int getRobotPosY() {
        return robotPosY;
    }

    public void setCleanAtRobotPosition() {
        layout[robotPosY][robotPosX] = Status.CLEAN;
    }

    public void setAllDirty() {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                if (layout[y][x] != Status.WALL) {
                layout[y][x] = Status.DIRTY;
                }
            }
        }
    }

    public Status getStatus(int posX, int posY) {
        return layout[posY][posX];
    }

    public boolean isClean() {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                if (layout[y][x] == Status.DIRTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                if (x == robotPosX && y == robotPosY) {
                    sb.append('R');
                } else {
                    sb.append(layout[y][x].label);
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
