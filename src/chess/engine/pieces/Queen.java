package chess.engine.pieces;

import chess.engine.Alliance;
import chess.engine.board.Move;
import chess.engine.board.Board;

import java.util.Collection;

public class Queen extends Piece{

    Queen(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }
}
