import controllers.GameController;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char choice = 'y';
        do {
            GameController gameController = new GameController();

            System.out.println("Lets begin Tic Tac Toe ....");

            System.out.println("What dimension of board do you need?");
            int dimension = sc.nextInt();

            System.out.println("How many total Players?");
            int numberOfPlayers = sc.nextInt();

            if (numberOfPlayers < 2) {
                System.out.println("Minimum number of players should be 2");
                continue;
            }

            System.out.println("Is there a bot? y/n");
            String isBot = sc.next();
            int nonBotPlayers = numberOfPlayers;
            if (isBot.equals("y")) {
                nonBotPlayers -= 1;
            }

            List<Player> playerList = new ArrayList<>();

            for (int i = 0; i < nonBotPlayers; i++) {
                System.out.println("Enter the name of the player " + (int)(i+1));
                String playerName = sc.next();
                System.out.println("Enter the symbol for the player ");
                String symbol = sc.next();
                playerList.add(new Player(playerName, symbol.charAt(0), i, PlayerType.HUMAN, sc));
            }

            if (isBot.equals("y")) {
                System.out.println("Enter the name of the bot ");
                String botName = sc.next();
                System.out.println("Enter the symbol for the bot ");
                String symbol = sc.next();
                playerList.add(new Bot(botName, symbol.charAt(0), nonBotPlayers, BotDifficultyLevel.EASY, sc));
            }

            Game game = gameController.createGame(dimension, playerList);

            while (gameController.getGameStatus(game).equals(GameState.IN_PROGRESS)) {
                System.out.println("This is the current board: ");
                gameController.displayBoard(game);
//             Added undo check here.
                System.out.println("Do you want to undo your move? y/n");
                char doUndo = sc.next().charAt(0);
                if (doUndo == 'y') {
                    gameController.undoLastMove(game);
                    gameController.displayBoard(game);
                }

                gameController.executeNextMove(game);
            }

            System.out.println("Result of the game: ");

            if (gameController.getGameStatus(game).equals(GameState.DRAW)) {
                System.out.println("DRAW");
            } else {
                System.out.println("The winner is: " + gameController.getWinner(game));
            }
            System.out.println("Do you want to play again? (y/n)");
            choice = sc.next().charAt(0);
        } while (choice == 'y');
    }
}