package huhtala.bryce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    public static final boolean WHITE = true;
    public static final boolean BLACK = false;


    public static final short UP = 0;
    public static final short RIGHT = 1;
    public static final short DOWN = 2;
    public static final short LEFT = 3;
    public static final short UP_RIGHT = 4;
    public static final short DOWN_RIGHT = 5;
    public static final short DOWN_LEFT = 6;
    public static final short UP_LEFT = 7;

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
        pieces[0][0] = new Piece(0, 0, Piece.ROOK, Piece.WHITE);
        pieces[1][0] = new Piece(1, 0, Piece.KNIGHT, Piece.WHITE);
        pieces[2][0] = new Piece(2, 0, Piece.BISHOP, Piece.WHITE);
        pieces[3][0] = new Piece(3, 0, Piece.QUEEN, Piece.WHITE);
        pieces[4][0] = new Piece(4, 0, Piece.KING, Piece.WHITE);
        pieces[5][0] = new Piece(5, 0, Piece.BISHOP, Piece.WHITE);
        pieces[6][0] = new Piece(6, 0, Piece.KNIGHT, Piece.WHITE);
        pieces[7][0] = new Piece(7, 0, Piece.ROOK, Piece.WHITE);
        pieces[0][1] = new Piece(0, 1, Piece.PAWN, Piece.WHITE);
        pieces[1][1] = new Piece(1, 1, Piece.PAWN, Piece.WHITE);
        pieces[2][1] = new Piece(2, 1, Piece.PAWN, Piece.WHITE);
        pieces[3][1] = new Piece(3, 1, Piece.PAWN, Piece.WHITE);
        pieces[4][1] = new Piece(4, 1, Piece.PAWN, Piece.WHITE);
        pieces[5][1] = new Piece(5, 1, Piece.PAWN, Piece.WHITE);
        pieces[6][1] = new Piece(6, 1, Piece.PAWN, Piece.WHITE);
        pieces[7][1] = new Piece(7, 1, Piece.PAWN, Piece.WHITE);
        pieces[0][7] = new Piece(0, 7, Piece.ROOK, Piece.BLACK);
        pieces[1][7] = new Piece(1, 7, Piece.KNIGHT, Piece.BLACK);
        pieces[2][7] = new Piece(2, 7, Piece.BISHOP, Piece.BLACK);
        pieces[3][7] = new Piece(3, 7, Piece.KING, Piece.BLACK);
        pieces[4][7] = new Piece(4, 7, Piece.QUEEN, Piece.BLACK);
        pieces[5][7] = new Piece(5, 7, Piece.BISHOP, Piece.BLACK);
        pieces[6][7] = new Piece(6, 7, Piece.KNIGHT, Piece.BLACK);
        pieces[7][7] = new Piece(7, 7, Piece.ROOK, Piece.BLACK);
        pieces[0][6] = new Piece(0, 6, Piece.PAWN, Piece.BLACK);
        pieces[1][6] = new Piece(1, 6, Piece.PAWN, Piece.BLACK);
        pieces[2][6] = new Piece(2, 6, Piece.PAWN, Piece.BLACK);
        pieces[3][6] = new Piece(3, 6, Piece.PAWN, Piece.BLACK);
        pieces[4][6] = new Piece(4, 6, Piece.PAWN, Piece.BLACK);
        pieces[5][6] = new Piece(5, 6, Piece.PAWN, Piece.BLACK);
        pieces[6][6] = new Piece(6, 6, Piece.PAWN, Piece.BLACK);
        pieces[7][6] = new Piece(7, 6, Piece.PAWN, Piece.BLACK);
        whitePieces = new Piece[16];
        blackPieces = new Piece[16];
        int whitePiecesAdded = 0;
        int blackPiecesAdded = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 2; j++) {
                whitePieces[whitePiecesAdded++] = pieces[i][j];
                blackPieces[blackPiecesAdded++] = pieces[i][j+6];
            }
        }
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
        capturedPieces = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (!(i == move.origin.x && j == move.origin.y)) {
                    Piece piece;
                    if (i == move.destination.x && j == move.destination.y) {
                        piece = board.getPieceAt(move.origin.x, move.origin.y);
                        Piece existingPiece = board.getPieceAt(i, j);
                        if (existingPiece != null) {
                            capturedPieces.add(new Piece(existingPiece));
                        }
                    } else {
                        piece = board.getPieceAt(i, j);
                    }
                    if (piece != null) {

                        pieces[i][j] = new Piece(i, j, piece.getType(), piece.getColor());
                        if (piece.getColor() == Piece.WHITE) {
                            whitePieces[whitePiecesAdded++] = piece;
                            whitePiecesCount++;
                        }
                        else {
                            blackPieces[blackPiecesAdded++] = piece;
                            blackPiecesCount++;
                        }
                    }

                }
            }
        }

        for (Piece capturedPiece : board.capturedPieces) {
            capturedPieces.add(new Piece(capturedPiece));
        }

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

    public Move getRandomMove() {
        Move move;

        Random random = new Random();
        if (activeColor == WHITE) {
            List<Move> moves = new ArrayList<>();
            for (int i = 0; i < whitePieces.length; i++) {
                List<Move> newMoves = getLegalMovesForPiece(whitePieces[i]);
                moves.addAll(newMoves);
            }
            int randomIndex = random.nextInt(moves.size()-1);
            move = moves.get(randomIndex);
        } else {
            List<Move> moves = new ArrayList<>();
            for (int i = 0; i < blackPieces.length; i++) {
                moves.addAll(getLegalMovesForPiece(blackPieces[i]));
            }
            int randomIndex = random.nextInt(moves.size()-1);
            move = moves.get(randomIndex);
        }

        return move;
    }

    private List<Move> getLegalMovesForPiece(Piece piece) {
        List<Move> moves = new ArrayList<>();

        switch (piece.getType()) {
            case Piece.PAWN:
                short forward = piece.getColor() == Piece.WHITE ? UP : DOWN;
                if (isLineClear(piece,forward,1))
                    moves.add(new Move(piece,forward,1));
                break;
//            case Piece.ROOK:
//                for (int i = 1; i <= 8; i++) {
//                    for (short direction = 0; direction < 4; direction++) {
//                        if (isLineClear(piece,direction,i))
//                            moves.add(new Move(piece,getDirectionOffset(direction,i)));
//                    }
//                }
//                break;
//            case Piece.BISHOP:
//                for (int i = 1; i <= 8; i++) {
//                    for (short direction = 4; direction < 7; direction++) {
//                        if (isLineClear(piece,direction,i))
//                            moves.add(new Move(piece,getDirectionOffset(direction,i)));
//                    }
//                }
//                break;
//            case Piece.QUEEN:
//                for (int i = 1; i <= 8; i++) {
//                    for (short direction = 0; direction < 7; direction++) {
//                        if (isLineClear(piece,direction,i))
//                            moves.add(new Move(piece,getDirectionOffset(direction,i)));
//                    }
//                }
//                break;
//            case Piece.KING:
//                for (short direction = 0; direction < 7; direction++) {
//                    if (isLineClear(piece,direction,1))
//                        moves.add(new Move(piece,getDirectionOffset(direction,1)));
//                }
//                break;
//            case Piece.KNIGHT:
//                if (pointIsClear(piece.getX() + 2, piece.getY() + 1))
//                    moves.add(new Move(piece,2,1));
//                if (pointIsClear(piece.getX() + 2, piece.getY() - 1))
//                    moves.add(new Move(piece,2,-1));
//
//                if (pointIsClear(piece.getX() + 1, piece.getY() + 2))
//                    moves.add(new Move(piece,1,2));
//                if (pointIsClear(piece.getX() - 1, piece.getY() + 2))
//                    moves.add(new Move(piece,-1,2));
//
//                if (pointIsClear(piece.getX() - 2, piece.getY() + 1))
//                    moves.add(new Move(piece,-2,1));
//                if (pointIsClear(piece.getX() - 2, piece.getY() - 1))
//                    moves.add(new Move(piece,-2,-1));
//
//                if (pointIsClear(piece.getX() + 1, piece.getY() - 2))
//                    moves.add(new Move(piece,1,-2));
//                if (pointIsClear(piece.getX() - 1, piece.getY() - 2))
//                    moves.add(new Move(piece,1,-2));
//                break;
        }
        return moves;
    }

    private boolean pointIsClear(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 7) return false;
        return getPieceAt(x, y) == null;
    }

    private boolean isLineClear(Piece piece, short direction, int distance) {
        if (distance == 0) return false;
        if (piece == null) return false;
        int x = piece.getX();
        int y = piece.getY();
        switch (direction) {
            case UP:
                for (int i = 1; i < distance; i++) {
                    if (y+i > 7) return false;
                    if (getPieceAt(x, y+i) != null) return false;
                }
                break;
            case RIGHT:
                for (int i = 1; i < distance; i++) {
                    if (x+i > 7) return false;
                    if (getPieceAt(x+i, y) != null) return false;
                }
                break;
            case DOWN:
                for (int i = 1; i < distance; i++) {
                    if (y-i < 0) return false;
                    if (getPieceAt(x, y-i) != null) return false;
                }
                break;
            case LEFT:
                for (int i = 1; i < distance; i++) {
                    if (x-i < 0) return false;
                    if (getPieceAt(x-i, y) != null) return false;
                }
                break;
            case UP_RIGHT:
                for (int i = 1; i < distance; i++) {
                    if (y+i > 7 || x+i > 7) return false;
                    if (getPieceAt(x+i, y+i) != null) return false;
                }
                break;
            case DOWN_RIGHT:
                for (int i = 1; i < distance; i++) {
                    if (y-i < 0 || x+i > 7) return false;
                    if (getPieceAt(x+i, y-i) != null) return false;
                }
                break;
            case DOWN_LEFT:
                for (int i = 1; i < distance; i++) {
                    if (y-i > 0 || x-i < 0) return false;
                    if (getPieceAt(x-i, y-i) != null) return false;
                }
                break;
            case UP_LEFT:
                for (int i = 1; i < distance; i++) {
                    if (y+i > 7 || x-i < 0) return false;
                    if (getPieceAt(x-i, y+i) != null) return false;
                }
                break;
        }
        return true;
    }

    private Point getDirectionOffset(short direction, int distance) {
        switch (direction) {
            case UP:
                return (new Point(0,distance));
            case RIGHT:
                return (new Point(distance,0));
            case DOWN:
                return (new Point(0,-distance));
            case LEFT:
                return (new Point(-distance,0));
            case UP_RIGHT:
                return (new Point(distance, distance));
            case DOWN_RIGHT:
                return (new Point(distance, -distance));
            case DOWN_LEFT:
                return (new Point(-distance, -distance));
            case UP_LEFT:
                return (new Point(-distance, distance));
        }
        return null;
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
