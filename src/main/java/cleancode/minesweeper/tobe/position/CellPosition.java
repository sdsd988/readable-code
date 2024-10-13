package cleancode.minesweeper.tobe.position;

import java.util.Objects;

public class CellPosition {

    private final int rowIndex;
    private final int colIndex;

    public CellPosition(int rowIndex, int colIndex) {
        if (rowIndex < 0 || colIndex < 0) {
            throw new IllegalArgumentException("올바르지 않은 좌표입니다.");
        }
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public static CellPosition of(int rowIndex, int colIndex) {
        return new CellPosition(rowIndex, colIndex);
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CellPosition that = (CellPosition) object;
        return rowIndex == that.rowIndex && colIndex == that.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }

    public boolean isRowIndexMoreThanEqual(int rowIndex) {

        return this.rowIndex >= rowIndex;
    }
    public boolean isColIndexMoreThanEqual(int colIndex) {

        return this.colIndex >= colIndex;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }
    public int getColIndex() {
        return this.colIndex;
    }

    public CellPosition calculatePositionBy(RelativePosition relativePosition) {
        return CellPosition.of(
                this.rowIndex + relativePosition.getDeltaRow(),
                this.colIndex + relativePosition.getDeltaCol());
    }

    public boolean canCalculatePositionBy(RelativePosition relativePosition) {
        if(canCalculatePositionBy(relativePosition)) {
            return this.rowIndex + relativePosition.getDeltaRow() >= 0
                && this.colIndex + relativePosition.getDeltaCol() >= 0;
        }
        throw new IllegalArgumentException("잘못된 좌표입니다.");
    }

    public boolean isRowIndexLessThan(int rowIndex) {
        return this.rowIndex < rowIndex;
    }
    public boolean isColIndexLessThan(int colIndex) {
        return this.colIndex < colIndex;
    }
}
