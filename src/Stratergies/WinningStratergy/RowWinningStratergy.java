package Stratergies.WinningStratergy;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStratergy implements WinningStratergy {
    Map<Integer, Map<Symbol, Integer>> count = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getCell().getPlayer().getSymbol();


        if (!count.containsKey(row)) {
            count.put(row, new HashMap<>());
        }
        Map<Symbol, Integer> rowmap = count.get(row);

        if (!rowmap.containsKey(symbol)) {
            rowmap.put(symbol, 0);
        }
        rowmap.put(symbol, rowmap.get(symbol) + 1);

        if (rowmap.get(symbol).equals(board.getSize())) {
            return true;
        }
        return false;
    }

    @Override
    public void undo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> rowmap = count.get(row);

        rowmap.put(symbol, rowmap.get(symbol) - 1);

    }
}
