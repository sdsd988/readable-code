package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.ConSoleInputHandler;
import cleancode.studycafe.tobe.io.ConSoleOutputHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        InputHandler inputHandler = new ConSoleInputHandler();
        OutputHandler outputHandler = new ConSoleOutputHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler,outputHandler);
        studyCafePassMachine.run();
    }

}
