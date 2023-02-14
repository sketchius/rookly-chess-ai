package huhtala.bryce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    Piece[][] pieces;
    Piece[] whitePieces;
    Piece[] blackPieces;
    boolean activeColor;
    boolean check;

    List<Piece> capturedPieces;

    int moves;
    int whitePiecesCount;
    int blackPiecesCount;

    public Board() {
        pieces = new Piece[8][8];
        pieces[0][0] = new Piece(Piece.ROOK,Piece.WHITE);
        pieces[1][0] = new Piece(Piece.KNIGHT,Piece.WHITE);
        pieces[2][0] = new Piece(Piece.BISHOP,Piece.WHITE);
        pieces[3][0] = new Piece(Piece.QUEEN,Piece.WHITE);
        pieces[4][0] = new Piece(Piece.KING,Piece.WHITE);
        pieces[5][0] = new Piece(Piece.BISHOP,Piece.WHITE);
        pieces[6][0] = new Piece(Piece.KNIGHT,Piece.WHITE);
        pieces[7][0] = new Piece(Piece.ROOK,Piece.WHITE);
        pieces[0][1] = new Piece(Piece.PAWN,Piece.WHITE);
        pieces[1][1] = new Piece(Piece.PAWN,Piece.WHITE);
        pieces[2][1] = new Piece(Piece.PAWN,Piece.WHITE);
        pieces[3][1] = new Piece(Piece.PAWN,Piece.WHITE);
        pieces[4][1] = new Piece(Piece.PAWN,Piece.WHITE);
        pieces[5][1] = new Piece(Piece.PAWN,Piece.WHITE);
        pieces[6][1] = new Piece(Piece.PAWN,Piece.WHITE);
        pieces[7][1] = new Piece(Piece.PAWN,Piece.WHITE);
        pieces[0][7] = new Piece(Piece.ROOK,Piece.BLACK);
        pieces[1][7] = new Piece(Piece.KNIGHT,Piece.BLACK);
        pieces[2][7] = new Piece(Piece.BISHOP,Piece.BLACK);
        pieces[3][7] = new Piece(Piece.KING,Piece.BLACK);
        pieces[4][7] = new Piece(Piece.QUEEN,Piece.BLACK);
        pieces[5][7] = new Piece(Piece.BISHOP,Piece.BLACK);
        pieces[6][7] = new Piece(Piece.KNIGHT,Piece.BLACK);
        pieces[7][7] = new Piece(Piece.ROOK,Piece.BLACK);;
        pieces[0][6] = new Piece(Piece.PAWN,Piece.BLACK);
        pieces[1][6] = new Piece(Piece.PAWN,Piece.BLACK);
        pieces[2][6] = new Piece(Piece.PAWN,Piece.BLACK);
        pieces[3][6] = new Piece(Piece.PAWN,Piece.BLACK);
        pieces[4][6] = new Piece(Piece.PAWN,Piece.BLACK);
        pieces[5][6] = new Piece(Piece.PAWN,Piece.BLACK);
        pieces[6][6] = new Piece(Piece.PAWN,Piece.BLACK);
        pieces[7][6] = new Piece(Piece.PAWN,Piece.BLACK);
        whitePieces = new Piece[16];
        blackPieces = new Piece[16];
        whitePiecesCount = 16;
        blackPiecesCount = 16;
        moves = 0;
        activeColor = WHITE;
        check = false;

        capturedPieces = new ArrayList<>();
    }

    public Board(Board board, Move move) {
        pieces = new Piece[8][8];
        whitePieces = new Piece[board.getWhitePiecesCount()];
        blackPieces = new Piece[board.getBlackPiecesCount()];
        int whitePiecesAdded = 0;
        int blackPiecesAdded = 0;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (board.getPieceAt(i,j) != null) {
                    Piece piece = board.getPieceAt(i,j);
                    pieces[i][j] = new Piece(piece.getType(),piece.getColor());
                    if (piece.getColor() == Piece.WHITE)
                        whitePieces[whitePiecesAdded++] = piece;
                    else
                        blackPieces[blackPiecesAdded++] = piece;
                }
            }
        }

        capturedPieces = new ArrayList<>();
        for (Piece capturedPiece : board.capturedPieces) {
            capturedPieces.add(new Piece(capturedPiece.getType(),capturedPiece.getColor()));
        }

        Piece destination = pieces[move.destination.x][move.destination.y];
        if (destination != null) {
            capturedPieces.add(pieces[move.destination.x][move.destination.y]);
            if (destination.getColor() == Piece.WHITE)
                whitePiecesCount--;
            else
                blackPiecesCount--;
        }
        pieces[move.destination.x][move.destination.y] = pieces[move.origin.x][move.origin.y];
        pieces[move.origin.x][move.origin.y] = null;

        moves = board.moves + 1;
        activeColor = !board.activeColor;
        check = false;
    }

    public int getWhitePiecesCount() {
        return whitePiecesCount;
    }

    public int getBlackPiecesCount() {
        return blackPiecesCount;
    }

    public Piece getPieceAt(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 7) return null;
        return pieces[x][y];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int j = 7; j >= 0; j--) {
            for (int i = 0; i < 8; i++) {
                if (pieces[i][j] != null)
                    sb.append(pieces[i][j].getType());
                else
                    sb.append(" ");
                if (i == 7)
                    sb.append('\n');
            }
        }

        return sb.toString();
    }
}
