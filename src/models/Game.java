package models;

import strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Run the application. We ask the player their name.
 * We ask them to choose a symbol
 * "C"
 * "R"
 * "*"
 */
public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int currentPlayerIndex;
    private GameState gameState;
    private Player winner;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
    }

    //    Added builder design pattern
    public static Builder getBuilder() {
        return new Builder();
    }
    public static class Builder {
        List<Player> players;
        List<WinningStrategy> winningStrategies;
        int dimension;

        private Builder() {
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategies.add(winningStrategy);
            return this;
        }

        public Game build() {
//            TODO: validations here
//            TODO: Add exceptions here
            return new Game(dimension, players, winningStrategies);
        }
    }
    public Board getBoard() {
        return board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public Move removeLastMove() {
        return moves.remove(moves.size() - 1);
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void nextPlayerTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public void previousPlayerTurn() {
        currentPlayerIndex -= 1;
        if (currentPlayerIndex < 0) {
            currentPlayerIndex = players.size()-1;
        }
    }

    public void updateBoard(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        this.board.getBoard().get(row).set(col, move.getCell());
    }

    public void updateBoardAfterUndo(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        this.board.getBoard().get(row).set(col, new Cell(row, col));
    }
}
