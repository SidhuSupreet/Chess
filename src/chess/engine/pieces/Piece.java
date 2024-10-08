package chess.engine.pieces;


import chess.engine.Alliance;
import chess.engine.board.Move;
import chess.engine.board.Board;

import java.util.Collection;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;

    private final int cachedHashCode;

    Piece(final int piecePosition, final Alliance pieceAlliance, final PieceType pieceType) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        // TODO mor work here
        this.isFirstMove = false;
        this.pieceType = pieceType;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
        int res = pieceType.hashCode();
        res = 31 * res + pieceAlliance.hashCode();
        res = 31 * res + piecePosition;
        res = 31 * res + (isFirstMove ? 1: 0);
        return res;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
    public int getPiecePosition() { return this.piecePosition; }

    public PieceType getPieceType() { return this.pieceType; }

    public abstract Piece movePiece(Move move);

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    @Override
    public boolean equals(final Object other){
        if(this == other)
            return true;

        if(!(other instanceof Piece))
            return false;

        final Piece otherPiece = (Piece) other;
        return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() &&
                pieceAlliance == otherPiece.getPieceAlliance() && isFirstMove == otherPiece.isFirstMove();
    }
    @Override
    public int hashCode(){
        return this.cachedHashCode;
    }



    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N"){
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B"){
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R"){
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q"){
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K"){
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();
    }
}
