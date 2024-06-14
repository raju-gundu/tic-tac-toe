package Models;

import Stratergies.BotPlayingStratergy.BotPlayingStratergy;
import Stratergies.BotPlayingStratergy.BotPlayingStratergyFactory;

public class Bot extends Player{
    private BotDiffLevel botDiffLevel;
    private BotPlayingStratergy botPlayingStratergy;

    public Bot(Long player_id, String player_name, Symbol symbol, PlayerType playerType, BotDiffLevel botDiffLevel) {
        super(player_id, player_name, symbol, playerType);
        this.botDiffLevel = botDiffLevel;
        this.botPlayingStratergy= BotPlayingStratergyFactory.getBotplayinStratergy(botDiffLevel);
    }
    public Move makeMove(Board board){
        Move move=this.botPlayingStratergy.makeMove(board);
        move.setPlayer(this);
        return move;
    }

    public BotDiffLevel getBotDiffLevel() {
        return botDiffLevel;
    }

    public void setBotDiffLevel(BotDiffLevel botDiffLevel) {
        this.botDiffLevel = botDiffLevel;
    }
}
