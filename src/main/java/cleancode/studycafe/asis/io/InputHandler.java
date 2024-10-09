package cleancode.studycafe.asis.io;

import cleancode.studycafe.asis.model.StudyCafePass;
import cleancode.studycafe.asis.model.StudyCafePassType;

import java.util.List;

public interface InputHandler {
    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafePass getSelectPass(List<StudyCafePass> passes);

    boolean getLockerSelection();
}
