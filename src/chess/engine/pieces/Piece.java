package chess.engine.pieces;


import chess.engine.Alliance;
import chess.engine.board.Move;
import chess.engine.board.Board;

import java.util.Collection;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        // TODO mor work here
        this.isFirstMove = false;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
