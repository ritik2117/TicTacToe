package models;

import strategies.bot.BotPlayingStrategy;
import strategies.bot.BotPlayingStrategyFactory;

import java.util.Scanner;

public class Bot extends Player {

    BotDifficultyLevel botDifficultyLevel;
    BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, char symbol, int id, BotDifficultyLevel botDifficultyLevel, Scanner scanner) {
        super(name, symbol, id, PlayerType.BOT, scanner);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        Move move = botPlayingStrategy.suggestMove(board);
//        NPE check
        if (move == null) {
            return null;
        }
        System.out.printf("The bot is making a move %d %d.\n", move.getCell().getRow()+1, move.getCell().getCol()+1);
        move.getCell().setPlayer(this);
        move.getCell().setCellStatus(CellStatus.OCCUPIED);
        return move;
    }
}
