package roboVac;

import java.util.ArrayList;

public class Room {
    private Layout layout;
    private Position robotPos;

    public Room(String[] layout) {
        this.layout = new Layout(layout);
    }

    public boolean isWall(Position pos) {
        return layout.getStatus(pos) == Status.WALL;
    }

    public boolean isDirty(Position pos) {
        return layout.getStatus(pos) == Status.DIRTY;
    }

    public boolean isClean(Position pos) {
        return layout.getStatus(pos) == Status.CLEAN;
    }

    public boolean isAccessible(Position pos) {
        return layout.isValid(pos);
    }

    public boolean isClean() {
        for (Position pos : layout.getAllPosition()) {
            if (layout.getStatus(pos) == Status.DIRTY) {
                return false;
            }
        }
        return true;
    }

    public void setAllDirty() {
        for (Position pos : layout.getAllPosition()) {
            layout.setStatus(pos, Status.DIRTY);
        }
    }

    public Position getRobotPosition() {
        return robotPos;
    }

    public void setRobotPosition(Position pos) {
        if (isAccessible(pos)) {
            robotPos = pos;
        }
    }

    public void setCleanAtRobotPosition() {
        layout.setStatus(robotPos, Status.CLEAN);
    }

    public int[][] getDistanceMatrix(Position pos) {
        int[][] distanceMatrix = new int[layout.getHeight()][layout.getWidth()];

        for (int y = 0; y < distanceMatrix.length; y++) {
            for (int x = 0; x < distanceMatrix[y].length; x++) {
                distanceMatrix[y][x] = -1;
            }
        }

        distanceMatrix[pos.y][pos.x] = 0;
        var processingQueue = new ArrayList<Position>();
        processingQueue.add(pos);
    
        while (!processingQueue.isEmpty()) {
            Position current = processingQueue.remove(0);
            for (Position neighbor : current.getNeighbors()) {
                if (isAccessible(neighbor) && distanceMatrix[neighbor.y][neighbor.x] == -1) {
                    distanceMatrix[neighbor.y][neighbor.x] = distanceMatrix[current.y][current.x] + 1;
                    processingQueue.add(neighbor);
                }
            }
        }
    
        return distanceMatrix;
    }

    public Position getNearestDirtyPosition(Position pos) {
        Position nearestDirtyPosition = null;
        var distances = getDistanceMatrix(pos);
    
        for (int y = 0; y < distances.length; y++) {
            for (int x = 0; x < distances[0].length; x++) {
                var current = new Position(x, y);
    
                if (isDirty(current) && (nearestDirtyPosition == null
                        || distances[current.y][current.x] < distances[nearestDirtyPosition.y][nearestDirtyPosition.x])) {
                    nearestDirtyPosition = current;
                }
            }
        }
    
        return nearestDirtyPosition;
    }

    public String getLayout() {
        return layout.toString();
    }

    public void printDistanceMatrix(Position pos) {
        int[][] distanceMatrix = getDistanceMatrix(pos);
        for (int y = 0; y < distanceMatrix.length; y++) {
            for (int x = 0; x < distanceMatrix[y].length; x++) {
                System.out.print(distanceMatrix[y][x] + " ");
            }
            System.out.println();
        }
    }

    private class Layout {

        private Status[][] innerLayout;
        private Position[] positions;

        public Layout(String[] layout) {
            this.innerLayout = new Status[layout.length][layout[0].length()];
            var positionList = new ArrayList<Position>();

            for (int y = 0; y < layout.length; y++) {
                for (int x = 0; x < layout[0].length(); x++) {
                    this.innerLayout[y][x] = Status.valueOf(layout[y].charAt(x));
                    if (!this.innerLayout[y][x].equals(Status.WALL)) {
                        positionList.add(new Position(x, y));
                    }
                }
            }

            this.positions = positionList.toArray(new Position[0]);
        }

        public int getHeight() {
            return innerLayout.length;
        }

        public int getWidth() {
            return innerLayout[0].length;
        }

        public Status getStatus(Position pos) {
            return innerLayout[pos.y][pos.x];
        }

        public void setStatus(Position pos, Status status) {
            innerLayout[pos.y][pos.x] = status;
        }

        public boolean isValid(Position pos) {
            for (Position position : positions) {
                if (position.equals(pos)) {
                    return true;
                }
            }
            return false;
        }

        public Position[] getAllPosition() {
            return positions;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int y = 0; y < innerLayout.length; y++) {
                for (int x = 0; x < innerLayout[y].length; x++) {
                    if (x == robotPos.x && y == robotPos.y) {
                        sb.append('R');
                    } else {
                        sb.append(innerLayout[y][x].label);
                    }
                }
                sb.append('\n');
            }

            return sb.toString().substring(0, sb.toString().length() - 1);
        }

    }
}
