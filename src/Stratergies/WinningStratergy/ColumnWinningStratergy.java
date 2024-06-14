package Stratergies.WinningStratergy;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStratergy implements WinningStratergy{
    Map<Integer,Map<Symbol,Integer>> count = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if (!count.containsKey(col)){
            count.put(col,new HashMap<>());
        }
        Map<Symbol,Integer> colmap = count.get(col);
        if (!colmap.containsKey(symbol)){
            colmap.put(symbol,0);
        }
        colmap.put(symbol,colmap.get(symbol)+1);

        if (colmap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }
}
