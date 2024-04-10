package leets.leets_mate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LeetsMateApplicationTests {
    private LeetsMateApplication app;

    @BeforeEach
    void setUp() {
        app = new LeetsMateApplication();
    }

    @Test
    void 입력받은_문자열을_파싱하여_리스트로_만든다() {
        String members = "리츠에,오신,걸,환영합니다";
        List<String> actual = app.parseMembers(members);
        assertThat(actual).containsExactly("리츠에", "오신", "걸", "환영합니다");
    }

    @Test
    void 멤버수를_반환한다() {
        List<String> members = Arrays.asList("리츠에", "오신", "걸", "환영합니다");
        int actual = app.memberNumber(members);
        assertThat(actual).isEqualTo(4);
    }

    @Test
    void 멤버수와_최대_멤버수를_잘못_입력한_경우_예외를_반환한다() {
        Exception exception = assertThrows(RuntimeException.class, () -> app.checkDataValidity(3, 4));
        assertThat(exception.getMessage()).contains("GROUP_SIZE_ERROR");
    }

    @Test
    void 멤버_문자열에_영어를_입력한_경우_예외를_반환한다() {
        Exception exception = assertThrows(RuntimeException.class, () -> app.checkHasNoEnglish("welcome,to,leets"));
        assertThat(exception.getMessage()).contains("INPUT_FORMAT_ERROR");
    }

    @Test
    void 랜덤_그룹_생성_테스트() {
        List<String> members = Arrays.asList("가", "나", "다", "라", "마", "바", "사");
        int maximumGroupSize = 3;
        List<List<String>> groups = LeetsMateApplication.generateRandomGroups(members, maximumGroupSize);

        // 그룹 수 검증
        assertTrue(groups.size() * maximumGroupSize >= members.size());

        // 각 그룹 내 인원 수 검증
        groups.forEach(group -> assertTrue(group.size() <= maximumGroupSize));
    }
}
