package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellSnapshotTest {

    @Test
    @DisplayName("빈 셀 스냅샷 생성")
    void ofEmpty() {

        //given
        CellSnapshot cellSnapshot = CellSnapshot.ofEmpty();

        //when
        boolean result = cellSnapshot.isSameStatus(CellSnapshotStatus.EMPTY);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("깃발 셀 스냅샷 생성")
    void ofFlag() {
        //given
        CellSnapshot cellSnapshot = CellSnapshot.ofFlag();

        //when
        boolean result = cellSnapshot.isSameStatus(CellSnapshotStatus.FLAG);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("지뢰 셀 스냅샷 생성")
    void ofLandMine() {
        //given
        CellSnapshot cellSnapshot = CellSnapshot.ofLandMine();

        //when
        boolean result = cellSnapshot.isSameStatus(CellSnapshotStatus.LAND_MINE);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("숫자 셀 스냅샷 생성")
    void ofNumber() {
        //given
        CellSnapshot cellSnapshot = CellSnapshot.ofNumber(3);

        //when
        boolean result = cellSnapshot.isSameStatus(CellSnapshotStatus.NUMBER);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("숫자 셀 스냅샷은 근접한 마인 수를 반환한다.")
    void ofNumberNearMineCount() {
        //given
        CellSnapshot cellSnapshot = CellSnapshot.ofNumber(3);

        //when
        int result = cellSnapshot.getNearbyLandMineCount();

        //then
        assertEquals(3, result);
    }

    @Test
    @DisplayName("체크되지않은 셀 스냅샷 생성")
    void ofUnchecked() {
        //given
        CellSnapshot cellSnapshot = CellSnapshot.ofUnchecked();

        //when
        boolean result = cellSnapshot.isSameStatus(CellSnapshotStatus.UNCHECKED);

        //then
        assertTrue(result);
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