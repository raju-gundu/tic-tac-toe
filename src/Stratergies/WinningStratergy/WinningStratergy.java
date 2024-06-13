package Stratergies.WinningStratergy;

import Models.Board;
import Models.Move;

public interface WinningStratergy {
    public boolean checkWinner(Board board, Move move);
}
