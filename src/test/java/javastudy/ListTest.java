package javastudy;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {
    
    @Test
    public void arraysAsList_元の配列と同期する() {
        String[] array = new String[]{"a", "b", "c"};
        List<String> list = Arrays.asList(array);
        list.set(0, "X");
        assertThat(list.get(0), is("X"));
        assertThat(array[0], is("X"));
    }
}
