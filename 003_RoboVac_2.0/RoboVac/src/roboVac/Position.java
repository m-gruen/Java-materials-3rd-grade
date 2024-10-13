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
                new Position(x, y - 1),
                new Position(x + 1, y),
                new Position(x, y + 1),
                new Position(x - 1, y)
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
