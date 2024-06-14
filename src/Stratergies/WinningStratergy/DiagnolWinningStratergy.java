package Stratergies.WinningStratergy;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagnolWinningStratergy implements WinningStratergy{
    Map<Symbol,Integer> leftDiagMap = new HashMap<>();
    Map<Symbol,Integer> rightDiagMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if (row==col){
            if (!leftDiagMap.containsKey(symbol)){
                leftDiagMap.put(symbol,0);
            }
            leftDiagMap.put(symbol,leftDiagMap.get(symbol)+1);
            if (leftDiagMap.get(symbol).equals(board.getSize())){
                return true;
            }
        }
        if (row+col==(board.getSize()-1)){
            if (!rightDiagMap.containsKey(symbol)){
                rightDiagMap.put(symbol,0);
            }
            rightDiagMap.put(symbol,rightDiagMap.get(symbol)+1);
            if (rightDiagMap.get(symbol).equals(board.getSize())){
                return true;
            }
        }
        return false;
    }
}
