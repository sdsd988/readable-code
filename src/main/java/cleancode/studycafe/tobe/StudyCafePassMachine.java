package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler consoleInputHandler = new InputHandler();
    private final OutputHandler consoleOutputHandler = new OutputHandler();

    public void run() {
        consoleOutputHandler.showWelcomeMessage();
        consoleOutputHandler.showAnnouncement();
        try {
            consoleOutputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = consoleInputHandler.getPassTypeSelectingUserAction();
            StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
            List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
            if (studyCafePassType == StudyCafePassType.HOURLY) {
                selectHourlyType(studyCafePasses, studyCafeFileHandler);
            }
            if (studyCafePassType == StudyCafePassType.WEEKLY) {
                selectWeeklyType(studyCafePasses, studyCafeFileHandler);
            }
            if (studyCafePassType == StudyCafePassType.FIXED) {
                selectFixedType(studyCafePasses, studyCafeFileHandler);
            }
        } catch (AppException e) {
            consoleOutputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            consoleOutputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void selectFixedType(List<StudyCafePass> studyCafePasses, StudyCafeFileHandler studyCafeFileHandler) {
        List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.FIXED)
                .toList();
        consoleOutputHandler.showPassListForSelection(fixedPasses);
        StudyCafePass selectedPass = consoleInputHandler.getSelectPass(fixedPasses);

        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
        StudyCafeLockerPass lockerPass = lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                                && option.getDuration() == selectedPass.getDuration()
                )
                .findFirst()
                .orElse(null);
        checkLockerAuthority(lockerPass, selectedPass);
    }

    private void checkLockerAuthority(StudyCafeLockerPass lockerPass, StudyCafePass selectedPass) {
        boolean lockerSelection = false;
        if (lockerPass != null) {
            consoleOutputHandler.askLockerPass(lockerPass);
            lockerSelection = consoleInputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            consoleOutputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            consoleOutputHandler.showPassOrderSummary(selectedPass, null);
        }
    }

    private void selectWeeklyType(List<StudyCafePass> studyCafePasses, StudyCafeFileHandler studyCafeFileHandler) {
        List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.WEEKLY)
                .toList();
        consoleOutputHandler.showPassListForSelection(weeklyPasses);
        StudyCafePass selectedPass = consoleInputHandler.getSelectPass(weeklyPasses);
        consoleOutputHandler.showPassOrderSummary(selectedPass, null);
    }

    private void selectHourlyType(List<StudyCafePass> studyCafePasses, StudyCafeFileHandler studyCafeFileHandler) {
        List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
                .toList();
        consoleOutputHandler.showPassListForSelection(hourlyPasses);
        StudyCafePass selectedPass = consoleInputHandler.getSelectPass(hourlyPasses);
        consoleOutputHandler.showPassOrderSummary(selectedPass, null);
    }

}
