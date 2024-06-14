import Controllers.GameController;
import Models.*;
import Stratergies.WinningStratergy.ColumnWinningStratergy;
import Stratergies.WinningStratergy.DiagnolWinningStratergy;
import Stratergies.WinningStratergy.RowWinningStratergy;
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
            winningStratergies.add(new ColumnWinningStratergy());
            winningStratergies.add(new RowWinningStratergy());
            winningStratergies.add(new DiagnolWinningStratergy());

            Game game=gameController.startGame(dimensions,players,winningStratergies);
            System.out.println("Game has been created");
            while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){
                gameController.displayBoard(game);
                gameController.makeMove(game);
            }

            if (gameController.checkState(game).equals(GameState.DRAW)){
                System.out.println("Game has been drawn");
            }
            if (gameController.checkState(game).equals(GameState.WIN)){
                System.out.println("Game has been won by "+ gameController.getWinner(game).getPlayer_name());
            }
        } catch (Exception e){
            System.out.println("Something went wrong while creating the game" + e);
        }


    }
}
