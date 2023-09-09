package strategies.bot;

import models.Board;
import models.Move;

public interface BotPlayingStrategy {
//   Implement by setting Player and Board
    Move suggestMove(Board board);
}
