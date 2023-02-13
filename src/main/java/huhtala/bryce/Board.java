package huhtala.bryce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static final char EMPTY = ' ';

    char[][] pieces;
    boolean activeColor;
    boolean check;

    List<Character> capturedPieces;

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
        for (int i = 0; i <= 7; i++) {
            for (int j = 2; j <= 5; j++) {
                pieces[i][j] = EMPTY;
            }
        }
        moves = 0;
        activeColor = WHITE;
        check = false;

        capturedPieces = new ArrayList<>();
    }

    public Board(Board board, Move move) {
        pieces = new char[8][8];
        pieces[0][0] = board.pieces[0][0];
        pieces[1][0] = board.pieces[1][0];
        pieces[2][0] = board.pieces[2][0];
        pieces[3][0] = board.pieces[3][0];
        pieces[4][0] = board.pieces[4][0];
        pieces[5][0] = board.pieces[5][0];
        pieces[6][0] = board.pieces[6][0];
        pieces[7][0] = board.pieces[7][0];
        pieces[0][1] = board.pieces[0][1];
        pieces[1][1] = board.pieces[1][1];
        pieces[2][1] = board.pieces[2][1];
        pieces[3][1] = board.pieces[3][1];
        pieces[4][1] = board.pieces[4][1];
        pieces[5][1] = board.pieces[5][1];
        pieces[6][1] = board.pieces[6][1];
        pieces[7][1] = board.pieces[7][1];
        pieces[0][7] = board.pieces[0][7];
        pieces[1][7] = board.pieces[1][7];
        pieces[2][7] = board.pieces[2][7];
        pieces[3][7] = board.pieces[3][7];
        pieces[4][7] = board.pieces[4][7];
        pieces[5][7] = board.pieces[5][7];
        pieces[6][7] = board.pieces[6][7];
        pieces[7][7] = board.pieces[7][7];
        pieces[0][6] = board.pieces[0][6];
        pieces[1][6] = board.pieces[1][6];
        pieces[2][6] = board.pieces[2][6];
        pieces[3][6] = board.pieces[3][6];
        pieces[4][6] = board.pieces[4][6];
        pieces[5][6] = board.pieces[5][6];
        pieces[6][6] = board.pieces[6][6];
        pieces[7][6] = board.pieces[7][6];

        capturedPieces = new ArrayList<>();
        capturedPieces.addAll(board.capturedPieces);

        Character destination = pieces[move.destination.x][move.destination.y];
        if (!destination.equals(' '))
            capturedPieces.add(pieces[move.destination.x][move.destination.y]);
        pieces[move.destination.x][move.destination.y] = pieces[move.origin.x][move.origin.y];
        pieces[move.origin.x][move.origin.y] = EMPTY;
        for (int i = 0; i <= 7; i++) {
            for (int j = 2; j <= 5; j++) {
                pieces[i][j] = EMPTY;
            }
        }
        moves = board.moves + 1;
        activeColor = !board.activeColor;
        check = false;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int j = 7; j >= 0; j--) {
            for (int i = 0; i < 8; i++) {
                sb.append(pieces[i][j]);
                if (i == 7)
                    sb.append('\n');
            }
        }

        return sb.toString();
    }
}
