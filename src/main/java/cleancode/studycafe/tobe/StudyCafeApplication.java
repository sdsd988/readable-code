package cleancode.studycafe.tobe;

import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine();
        studyCafePassMachine.run();
    }

}
