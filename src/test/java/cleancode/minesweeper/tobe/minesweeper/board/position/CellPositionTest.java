package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellPositionTest {


    @Test
    @DisplayName("생성 실패 : rowIndex 음수")
    void createCellPositionErrorTest() {
        //given
        int rowIndex = -1;
        int columnIndex = 1;
        //when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> CellPosition.of(rowIndex, columnIndex));
        assertEquals("올바르지 않은 좌표입니다.",exception.getMessage());
    }

    @Test
    @DisplayName("생성 실패 : colIndex 음수")
    void createCellPositionErrorTest1() {
        //given
        int rowIndex = 1;
        int columnIndex = -1;
        //when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> CellPosition.of(rowIndex, columnIndex));
        assertEquals("올바르지 않은 좌표입니다.",exception.getMessage());
    }


    @Test
    @DisplayName("생성 성공 : row,colIndex 양수")
    void createCellPositionSuccessTest() {
        //given
        int rowIndex = 1;
        int columnIndex = 1;
        //when
        CellPosition result = CellPosition.of(rowIndex, columnIndex);

        //then
        assertEquals(rowIndex, result.getRowIndex());
        assertEquals(columnIndex, result.getColIndex());
    }

    @Test
    @DisplayName("상대 좌표 계산시 범위를 벗어나면 오류를 발생한다.")
    void calculatePositionByFailTest() {

        // given
        CellPosition cellPosition = CellPosition.of(0, 0);
        RelativePosition relativePosition = RelativePosition.of(-1, -1);

        //when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cellPosition.calculatePositionBy(relativePosition));

        assertEquals("움직일 수 있는 좌표가 아닙니다.", exception.getMessage());

    }

    @Test
    @DisplayName("상대 좌표를 통한 계산 성공")
    void calculatePositionByOkTest() {

        // given
        CellPosition cellPosition = CellPosition.of(1, 1);
        RelativePosition relativePosition = RelativePosition.of(-1, -1);

        //when
        CellPosition result = cellPosition.calculatePositionBy(relativePosition);

        //then
        assertEquals(CellPosition.of(0,0),result);


    }

    @Test
    @DisplayName("rowIndex 범위를 계산할 수 있다.")
    void isRowIndexMoreThanOrEqualTest() {
        //given
        CellPosition cellPosition = CellPosition.of(3, 3);

        //when
        boolean result = cellPosition.isRowIndexMoreThanOrEqual(2);

        //then
        assertTrue(result);

    }

    @Test
    @DisplayName("colIndex 범위를 계산할 수 있다.")
    void isColIndexMoreThanOrEqualTest() {
        //given
        CellPosition cellPosition = CellPosition.of(3, 3);

        //when
        boolean result = cellPosition.isColIndexMoreThanOrEqual(2);

        //then
        assertTrue(result);

    }

    @Test
    @DisplayName("동등성 테스트")
    void equalsTest() {
        //given
        CellPosition positionA = CellPosition.of(1, 1);
        CellPosition positionB = CellPosition.of(1, 1);

        //when
        boolean result = positionA.equals(positionB);

        //then
        assertTrue(result);
    }
}