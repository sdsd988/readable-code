package cleancode.minesweeper.tobe.cell;

public class LandMineCell extends Cell {
    private boolean isLandMine;
    private static final String LAND_MINE_SIGN = "☼";



    @Override
    public boolean hasLandMineCount() {
        return false;
    }

    @Override
    public boolean isLandMine() {
        return true;
    }

    @Override
    public String getSign() {
        if (isOpened) {
            return LAND_MINE_SIGN;
        }
        if (isFlagged) {
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }


}
