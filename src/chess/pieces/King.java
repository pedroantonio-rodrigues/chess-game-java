package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	private boolean testRookCastling(Position position){
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == p.getColor() && p.getMoveCount() == 0;
	}
	
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// for position above to the piece
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// for position below to the piece
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// for position on left to the piece
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// for position on right to the piece
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// for position northwest to the piece
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// for position northeast to the piece
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// for position south-west to the piece
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// for position south-east to the piece
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// special move castling 
		if(getMoveCount() == 0 && !chessMatch.getCheck()){
			// special move castling king side rook
			Position positionRookOne = new Position(position.getRow(), position.getColumn() + 3);
				if (testRookCastling(positionRookOne)){
					Position p1 = new Position(position.getRow(), position.getColumn()+ 1 );
					Position p2 = new Position(position.getRow(), position.getColumn() + 2);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null){
						mat[position.getRow()][position.getColumn() + 2] = true;
					}
				}
				// special move castling king side Queen 
			Position positionRookTwo = new Position(position.getRow(), position.getColumn() - 4);
				if (testRookCastling(positionRookTwo)){
					Position p1 = new Position(position.getRow(), position.getColumn() - 1);
					Position p2 = new Position(position.getRow(), position.getColumn() - 2);
					Position p3 = new Position(position.getRow(), position.getColumn() - 3);

					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null ){
						mat[position.getRow()][position.getColumn() - 2] = true;
					}
				}
			} 

		return mat;
	}

}
