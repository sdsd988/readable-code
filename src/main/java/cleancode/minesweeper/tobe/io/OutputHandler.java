package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.AppException;
import cleancode.minesweeper.tobe.GemaBoard;

public interface OutputHandler {

     void showGameStartComment();

     void showBoard(GemaBoard board);

     void showGameWinningComment();

     void showGameLosingComment();

     void showCommentForSelectingCell();

     void showCommentForUserAction();
     void showExceptionMessage(AppException e);

     void showSimpleMessage(String message);
}
