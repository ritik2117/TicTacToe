package models;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    private int id;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(String name, char symbol, int id, PlayerType playerType, Scanner scanner) {
        this.name = name;
        this.symbol = symbol;
        this.id = id;
        this.playerType = playerType;
        this.scanner = scanner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    private static boolean cellAvailable(Board board) {
        for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().size(); j++) {
                if (board.getBoard().get(i).get(j).getCellStatus().equals(CellStatus.EMPTY))
                    return true;
            }
        }
        return false;
    }

    public Move makeMove(Board board) {
        if (!cellAvailable(board)) {
            return null;
        }
        System.out.println("Enter the row and column where you want to play the move.");

        int i = 0;
        while (i < 3) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            // board.getBoard().get(row).get(col) --> get the cell from the board
            // Update the cell -> board ends up getting updated.
            Cell cell = new Cell(board.getBoard().get(row-1).get(col-1));
            System.out.printf("The player %s is making a move at cell: %d, %d\n",
                    this.getName(), cell.getRow()+1, cell.getCol()+1);
            if (cell.getCellStatus().equals(CellStatus.OCCUPIED)) {
                System.out.println("This cell is already occupied!!!");
                System.out.println("Enter the row and column again where you want to play the move.");
//                throw new IllegalArgumentException("The cell is occupied");
            } else {
                cell.setPlayer(this);
                cell.setCellStatus(CellStatus.OCCUPIED);
                return new Move(cell);
            }
            i++;
        }
//        TODO: In case of repeated wrong moves, another player should be declared as winner
        System.out.printf("The player %s has lost all the chances!!!\n", this.getName());
        return null;
    }
}
