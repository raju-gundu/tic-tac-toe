package Models;

import Exceptions.BotCountException;
import Exceptions.PlayerCountException;
import Exceptions.SymbolCountException;
import Stratergies.WinningStratergy.WinningStratergy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextPlayerTurnIndex;
    private List<WinningStratergy> winningStratergies;

    private Game(int dimension,List<Player> players,List<WinningStratergy> winningStratergies){
        board = new Board(dimension);
        this.players = players;
        this.winningStratergies=winningStratergies;

        this.moves=new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerTurnIndex=0;
    }
    public void displayBoard(){
        this.board.displayBoard();
    }

    public void makeMove(){
        Player currentPlayer = players.get(nextPlayerTurnIndex);
        System.out.println("It is "+currentPlayer.getPlayer_name()+"'s move");
        Move move = currentPlayer.makeMove(board);

        System.out.println(currentPlayer.getPlayer_name()+" has made a move at "+move.getCell().getRow()+","+move.getCell().getCol());
        if (!validateMove(move)){
            System.err.println("Invalid Move");
            return;
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell actualCell = board.getCells().get(row).get(col);
        actualCell.setCellState(CellState.FILLED);
        actualCell.setPlayer(currentPlayer);
        Move actualMove = new Move(actualCell,currentPlayer);
        moves.add(actualMove);

        nextPlayerTurnIndex+=1;
        nextPlayerTurnIndex%=players.size();

        if (checkWinner(actualMove)){
            setGameState(GameState.WIN);
            setWinner(currentPlayer);
            return;
        }

        if (moves.size()== board.getSize()*board.getSize()){
            setGameState(GameState.DRAW);
            System.out.println("Game has been drawn");
        }
    }
    public boolean checkWinner(Move move){
        for (WinningStratergy winningStratergy : winningStratergies){
            if (winningStratergy.checkWinner(board,move )){
                return true;
            }
        }
        return false;
    }
    public boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row<0 || row>=board.getSize()){
            return false;
        }
        if(col<0 || col>=board.getSize()){
            return false;
        }
        if (board.getCells().get(row).get(col).getCellState().equals(CellState.FILLED)){
            return false;
        }
        return true;
    }

    public void undo(Game game){
        if (moves.size()==0)
            return;
        Move lastMove = moves.getLast();
        moves.remove(lastMove);

        Cell cell = lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);

        for (WinningStratergy winningStratergy : winningStratergies){
            winningStratergy.undo(board,lastMove);
        }

        nextPlayerTurnIndex-=1;
        nextPlayerTurnIndex=(nextPlayerTurnIndex+players.size())%players.size();
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStratergy> winningStratergies;


        public Game build() throws PlayerCountException, BotCountException, SymbolCountException {
            validate();
            return new Game(dimension,players,winningStratergies);
        }

        private void validate() throws BotCountException, SymbolCountException, PlayerCountException {
            validatePlayerCount();
            validateBotCount();
            validateSymbolCount();
        }

        private void validatePlayerCount() throws PlayerCountException {
                if(players.size() != dimension-1){
                    throw new PlayerCountException();
                }
        }
        private void validateBotCount() throws BotCountException {
            int botCount=0;
            for(Player player : players){
                if (player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if (botCount>1){
                throw new BotCountException();
            }
        }
        private void validateSymbolCount() throws SymbolCountException {
            Map<Character,Integer> symbolCount = new HashMap<>();
            for (Player player : players){
                if (!symbolCount.containsKey(player.getSymbol().getAchar())){
                    symbolCount.put(player.getSymbol().getAchar(),0);
                }
                symbolCount.put(player.getSymbol().getAchar(),symbolCount.get(player.getSymbol().getAchar())+1);
                if (symbolCount.get(player.getSymbol().getAchar())>1){
                    throw new SymbolCountException();
                }
            }
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStratergies(List<WinningStratergy> winningStratergies) {
            this.winningStratergies = winningStratergies;
            return this;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerTurnIndex() {
        return nextPlayerTurnIndex;
    }

    public void setNextPlayerTurnIndex(int nextPlayerTurnIndex) {
        this.nextPlayerTurnIndex = nextPlayerTurnIndex;
    }

    public List<WinningStratergy> getWinningStratergies() {
        return winningStratergies;
    }

    public void setWinningStratergies(List<WinningStratergy> winningStratergies) {
        this.winningStratergies = winningStratergies;
    }
}
