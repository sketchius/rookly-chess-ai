package huhtala.bryce;

public class Move {
    public Point origin;
    public Point destination;

    public Move(Point origin, Point destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Move(Piece piece, int deltaX, int deltaY) {
        this.origin = new Point(piece.getX(), piece.getY());
        this.destination = new Point(piece.getX() + deltaX, piece.getY() + deltaY);
    }

    public Move(Piece piece, Point point) {
        this.origin = new Point(piece.getX(), piece.getY());
        this.destination = new Point(piece.getX() + point.x, piece.getY() + point.y);
    }
}
