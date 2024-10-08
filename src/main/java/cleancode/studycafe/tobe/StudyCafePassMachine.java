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

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void run() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
        try {
            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
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
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void selectFixedType(List<StudyCafePass> studyCafePasses, StudyCafeFileHandler studyCafeFileHandler) {
        List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.FIXED)
                .toList();
        outputHandler.showPassListForSelection(fixedPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

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
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }

    private void selectWeeklyType(List<StudyCafePass> studyCafePasses, StudyCafeFileHandler studyCafeFileHandler) {
        List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.WEEKLY)
                .toList();
        outputHandler.showPassListForSelection(weeklyPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    private void selectHourlyType(List<StudyCafePass> studyCafePasses, StudyCafeFileHandler studyCafeFileHandler) {
        List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
                .toList();
        outputHandler.showPassListForSelection(hourlyPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

}
