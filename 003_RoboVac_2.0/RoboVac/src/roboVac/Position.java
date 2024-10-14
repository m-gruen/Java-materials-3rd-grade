package roboVac;

public class Position {

    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position[] getNeighbors() {
        return new Position[] {
                new Position(x + Direction.NORTH.x, y + Direction.NORTH.y),
                new Position(x + Direction.EAST.x, y + Direction.EAST.y),
                new Position(x + Direction.SOUTH.x, y + Direction.SOUTH.y),
                new Position(x + Direction.WEST.x, y + Direction.WEST.y),
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        return x == other.x && y == other.y;
    }

}
