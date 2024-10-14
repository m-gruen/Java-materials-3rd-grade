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
        var visited = new ArrayList<Position>();
        var toVisit = new ArrayList<Position>();
        toVisit.add(pos);

        while (!toVisit.isEmpty()) {
            Position current = toVisit.remove(0);
            visited.add(current);

            for (Position next : current.getNeighbors()) {
                if (!(visited.contains(next) || toVisit.contains(next))) {
                    if (!layout.isValid(next)) {
                        distanceMatrix[next.y][next.x] = -1;
                    } else {
                        toVisit.add(next);
                        distanceMatrix[next.y][next.x] = distanceMatrix[current.y][current.x] + 1;
                    }
                }
            }
        }

        return distanceMatrix;
    }

    public Position getNearestDirtyPosition(Position pos) {
        int[][] distanceMatrix = getDistanceMatrix(pos);
        var dirtyPositions = new ArrayList<Position>();
        int minDistance = Integer.MAX_VALUE;
        Position nearestDirty = null;

        for (Position position : layout.getAllPosition()) {
            if (isDirty(position)) {
                dirtyPositions.add(position);
            }
        }

        for (Position dirty : dirtyPositions) {
            if (distanceMatrix[dirty.y][dirty.x] < minDistance) {
                minDistance = distanceMatrix[dirty.y][dirty.x];
                nearestDirty = dirty;
            }
        }

        return nearestDirty;
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
