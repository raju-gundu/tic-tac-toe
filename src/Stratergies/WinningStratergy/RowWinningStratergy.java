package Stratergies.WinningStratergy;

import Models.Board;
import Models.Move;

public class RowWinningStratergy implements WinningStratergy{
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
