package cleancode.studycafe.asis.io;

import cleancode.studycafe.asis.model.StudyCafeLockerPass;
import cleancode.studycafe.asis.model.StudyCafePass;

import java.util.List;

public interface OutputHandler {
    void showWelcomeMessage();

    void showAnnouncement();

    void askPassTypeSelection();

    void showPassListForSelection(List<StudyCafePass> passes);

    void askLockerPass(StudyCafeLockerPass lockerPass);

    void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass);

    void showSimpleMessage(String message);
}
