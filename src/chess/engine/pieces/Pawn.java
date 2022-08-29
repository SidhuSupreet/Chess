package chess.engine.pieces;

import chess.engine.Alliance;
import chess.engine.board.BoardUtils;
import chess.engine.board.Move;
import chess.engine.board.Board;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static chess.engine.board.Move.*;

public class Pawn extends Piece{

    private final static int [] CANDIDATE_MOVE_COORDINATES = {7, 8, 9, 16};
    Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currenCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currenCandidateOffset);

            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            if (currenCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                // TODO more work with pawn promotion needed
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            } else if (currenCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceAlliance.isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceAlliance.isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied())  {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }
            } else if (currenCandidateOffset == 7 &&
                            !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())) )) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        //TODO more work with pawn promotion needed
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
            } else if (currenCandidateOffset == 9 &&
                            !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack() ||
                            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite())) )) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        //TODO more work with pawn promotion needed
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
