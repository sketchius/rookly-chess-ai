package huhtala.bryce;

public class Board {
    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    public static final char WHITE_PAWN = 'p';
    public static final char WHITE_KNIGHT = 'n';
    public static final char WHITE_BISHOP = 'b';
    public static final char WHITE_ROOK = 'r';
    public static final char WHITE_QUEEN = 'q';
    public static final char WHITE_KING = 'k';
    public static final char BLACK_PAWN = 'P';
    public static final char BLACK_KNIGHT = 'N';
    public static final char BLACK_BISHOP = 'B';
    public static final char BLACK_ROOK = 'R';
    public static final char BLACK_QUEEN = 'Q';
    public static final char BLACK_KING = 'K';

    char[][] pieces;
    boolean activeColor;
    boolean check;

    int moves;

    public Board() {
        pieces = new char[8][8];
        pieces[0][0] = WHITE_ROOK;
        pieces[1][0] = WHITE_KNIGHT;
        pieces[2][0] = WHITE_BISHOP;
        pieces[3][0] = WHITE_QUEEN;
        pieces[4][0] = WHITE_KING;
        pieces[5][0] = WHITE_BISHOP;
        pieces[6][0] = WHITE_KNIGHT;
        pieces[7][0] = WHITE_ROOK;
        pieces[0][1] = WHITE_PAWN;
        pieces[1][1] = WHITE_PAWN;
        pieces[2][1] = WHITE_PAWN;
        pieces[3][1] = WHITE_PAWN;
        pieces[4][1] = WHITE_PAWN;
        pieces[5][1] = WHITE_PAWN;
        pieces[6][1] = WHITE_PAWN;
        pieces[7][1] = WHITE_PAWN;
        pieces[0][7] = BLACK_ROOK;
        pieces[1][7] = BLACK_KNIGHT;
        pieces[2][7] = BLACK_BISHOP;
        pieces[3][7] = BLACK_KING;
        pieces[4][7] = BLACK_QUEEN;
        pieces[5][7] = BLACK_BISHOP;
        pieces[6][7] = BLACK_KNIGHT;
        pieces[7][7] = BLACK_ROOK;;
        pieces[0][6] = BLACK_PAWN;
        pieces[1][6] = BLACK_PAWN;
        pieces[2][6] = BLACK_PAWN;
        pieces[3][6] = BLACK_PAWN;
        pieces[4][6] = BLACK_PAWN;
        pieces[5][6] = BLACK_PAWN;
        pieces[6][6] = BLACK_PAWN;
        pieces[7][6] = BLACK_PAWN;
        moves = 0;
        activeColor = WHITE;
        check = false;
    }

}
