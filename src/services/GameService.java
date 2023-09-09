package services;

import models.*;
import strategies.winningstrategies.WinningStrategy;

import java.util.List;

public class GameService {
    private Game game;

    public GameService(Game game) {
        this.game = game;
    }

    public void executeNextMove() {
        Player currentPlayer = game.getCurrentPlayer();
        System.out.printf("Player turn: %s\n", currentPlayer.getName());
        Move move = currentPlayer.makeMove(game.getBoard());

        if (move == null) {
            game.setGameState(GameState.DRAW);
            return;
        }
        game.addMove(move);
        game.updateBoard(move);

        for (WinningStrategy winningStrategy : game.getWinningStrategies()) {
            if (winningStrategy.checkWinner(game.getBoard(), move)) {
                System.out.printf("The player %s has won.\n", currentPlayer.getName());
                game.setGameState(GameState.WIN);
                game.setWinner(currentPlayer);
                break;
            }
        }
        game.nextPlayerTurn();
    }

    public void undoLastMove(Game game) {
        System.out.println("Undoing last move");
        List<Move> moves = game.getMoves();

        if (moves.isEmpty()) {
            System.out.println("No moves are there for undo");
            return;
        }

        Move move = game.removeLastMove();
        game.updateBoardAfterUndo(move);
        game.previousPlayerTurn();
    }
}
