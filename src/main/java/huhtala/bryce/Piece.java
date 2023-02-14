package huhtala.bryce;

public class Piece {
    public static final char PAWN = 'P';
    public static final char KNIGHT = 'N';
    public static final char BISHOP = 'B';
    public static final char ROOK = 'R';
    public static final char QUEEN = 'Q';
    public static final char KING = 'K';

    public static final char WHITE = 'W';
    public static final char BLACK = 'B';

    private char type;
    private char color;
    private int x;
    private int y;

    public Piece(int x, int y, char type, char color ) {
        this.type = type;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Piece(Piece piece) {
        this.type = piece.type;
        this.color = piece.color;
        this.x = piece.x;
        this.y = piece.y;
    }

    public char getType() {
        return type;
    }

    public char getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
