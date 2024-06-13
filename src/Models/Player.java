package Models;

import java.util.Scanner;

public class Player {
    private Long player_id;
    private String player_name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Long player_id,String player_name,Symbol symbol,PlayerType playerType) {
        this.player_id = player_id;
        this.player_name = player_name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner=new Scanner(System.in);
    }

    public Move makeMove(Board board){
        System.out.println("Please enter row number");
        int row = scanner.nextInt();

        System.out.println("Please enter the column number ");
        int col = scanner.nextInt();

        return new Move(new Cell(row,col),this);
    }
    public Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
