package leets.leets_mate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class LeetsMateApplicationTests{

    private LeetsMateApplication app;

    @BeforeEach
    void setUp() {
        app = new LeetsMateApplication();
    }

    @Test
    public void testGetMembers() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String input = "김서현, 김지현, 김수현\n";
        provideInput(input);

        Method method = LeetsMateApplication.class.getDeclaredMethod("getMembers");
        method.setAccessible(true);
        List<String> members = (List<String>) method.invoke(app);

    }

    private void provideInput(String data) {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }
}
