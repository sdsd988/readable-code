package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CellSignProviderTest {



    @Test
    @DisplayName("셀 스냅샷이 비어있으면, 빈 셀 표시('■') 반환")
    public void testEmptyCellSign() {
        //given
        CellSnapshot emptyCell = CellSnapshot.ofEmpty();

        //when
        String result = CellSignProvider.findCellSignFrom(emptyCell);

        //then
        assertEquals("■", result);
    }


    @Test
    @DisplayName("셀 스냅샷이 비어있으면, 빈 셀 표시('⚑') 반환")
    public void testFlagCellSign() {

        //given
        CellSnapshot flagCell = CellSnapshot.ofFlag();

        //when
        String result = CellSignProvider.findCellSignFrom(flagCell);

        //then
        assertEquals("⚑", result);

    }

    @Test
    @DisplayName("셀 스냅샷이 비어있으면, 빈 셀 표시('☼') 반환")
    public void testLandMineCellSign() {

        //given
        CellSnapshot landMineCell = CellSnapshot.ofLandMine();

        //when
        String result = CellSignProvider.findCellSignFrom(landMineCell);

        //then
        assertEquals("☼", result);
    }

    @Test
    @DisplayName("셀 스냅샷이 비어있으면, 빈 셀 표시('☼') 반환")
    public void testNumberCellSign() {
        //given
        CellSnapshot numberCell = CellSnapshot.ofNumber(3);

        //when
        String result = CellSignProvider.findCellSignFrom(numberCell);

        //then
        assertEquals("3", result);
    }

    @Test
    @DisplayName("셀 스냅샷이 비어있으면, 빈 셀 표시('□') 반환")
    public void testUncheckedCellSign() {

        //given
        CellSnapshot uncheckedCell = CellSnapshot.ofUnchecked();

        //when
        String result = CellSignProvider.findCellSignFrom(uncheckedCell);

        //then
        assertEquals("□", result);
    }


    @Test
    @DisplayName("셀 스냅샷이 일치하면 True 반환")
    void testSupport() {

        //given
        CellSnapshot uncheckedCell = CellSnapshot.ofUnchecked();
        CellSnapshot numberCell = CellSnapshot.ofNumber(3);
        CellSnapshot emptyCell = CellSnapshot.ofEmpty();
        CellSnapshot flagCell = CellSnapshot.ofFlag();

        //when
        boolean result1 = CellSignProvider.UNCHECKED.supports(uncheckedCell);
        boolean result2 = CellSignProvider.NUMBER.supports(numberCell);
        boolean result3 = CellSignProvider.EMPTY.supports(emptyCell);
        boolean result4 = CellSignProvider.FLAG.supports(flagCell);

        //then
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
    }

    @Test
    @DisplayName("동등성 테스트")
    void equalsTest() {

        //given
        CellSnapshot numberCell1 = CellSnapshot.of(CellSnapshotStatus.NUMBER,3);
        CellSnapshot numberCell2 = CellSnapshot.of(CellSnapshotStatus.NUMBER,3);

        //when
        boolean result = numberCell1.equals(numberCell2);

        //then

        assertTrue(result);

    }
}