package Stratergies.BotPlayingStratergy;

import Models.BotDiffLevel;

public class BotPlayingStratergyFactory {
    public static BotPlayingStratergy getBotplayinStratergy(BotDiffLevel botDiffLevel){
        if (botDiffLevel.equals(BotDiffLevel.EASY)){
            return new EasyPlayingStratergy();
        }
        if (botDiffLevel.equals(BotDiffLevel.MEDIUM)){
            return new MediumBotPlayingStratergy();
        }
        if (botDiffLevel.equals(BotDiffLevel.HARD)){
            return new HardBotPlayingStrategy();
        }
        return null;
    }
}
