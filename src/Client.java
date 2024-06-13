import Controllers.GameController;
import Models.*;
import Stratergies.WinningStratergy.WinningStratergy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        try {
            int dimensions=3;
            List<Player> players = new ArrayList<>();
            players.add(new Player(1L,"Raju",new Symbol('X'), PlayerType.HUMAN));
            players.add(new Bot(2L,"BOT",new Symbol('O'),PlayerType.BOT,BotDiffLevel.EASY));

            List<WinningStratergy> winningStratergies=new ArrayList<>();
            Game game=gameController.startGame(dimensions,players,winningStratergies);
            System.out.println("Game has been created");
            while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){
                gameController.displayBoard(game);
                gameController.makeMove(game);
            }
        } catch (Exception e){
            System.out.println("Something went wrong while creating the game" + e);
        }


    }
}
