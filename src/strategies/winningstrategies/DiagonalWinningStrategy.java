package strategies.winningstrategies;

import models.*;

public class DiagonalWinningStrategy implements WinningStrategy {

    private boolean checkDiagonal1(Board board, int diagRow, int diagCol, int boardSize, Player player) {
        for (int i = 0; i < boardSize; i++) {
            Cell cell = board.getBoard().get(i).get(i);
            if ( (cell.getCellStatus().equals(CellStatus.EMPTY))
                    || !(cell.getPlayer().equals(player)) ) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal2(Board board, int diagRow, int diagCol, int boardSize, Player player) {
        int i = 0;
        int j = boardSize-1;
        while (i < boardSize && j >= 0) {
            Cell cell = board.getBoard().get(i).get(j);
            if ( (cell.getCellStatus().equals(CellStatus.EMPTY))
                    || !(cell.getPlayer().equals(player)) ) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        Player player = move.getCell().getPlayer();

        int diagRow = move.getCell().getRow();
        int diagCol = move.getCell().getCol();
        int boardSize = board.getBoard().size();
        boolean diagOneComp = false;
        boolean diagTwoComp = false;

        if (diagRow == diagCol) {
            diagOneComp = checkDiagonal1(board, diagRow, diagCol, boardSize, player);
        }
        if (diagRow + diagCol == boardSize-1) {
            diagTwoComp = checkDiagonal2(board, diagRow, diagCol, boardSize, player);
        }
        return (diagOneComp || diagTwoComp);
    }
}
