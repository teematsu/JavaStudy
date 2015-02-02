package javastudy;

import org.apache.commons.lang3.StringUtils;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringTest {
    
    @Test
    public void isEmpty_空() {
        String str = "";
        boolean actual = str.isEmpty();
        assertThat(actual, is(true));
    }
    @Test
    public void isEmpty_スペース() {
        String str = " ";
        boolean actual = str.isEmpty();
        assertThat(actual, is(false));
    }

    @Test
    public void utils_isBlank_全角スペース() {
        boolean actual = StringUtils.isBlank("　"); // Character#isWhitespace でtrueとなるものはblank扱い
        assertThat(actual, is(true));
    }
    
    @Test
    public void trim() {
        String str = "  aaa  ";
        String actual = str.trim();
        assertThat(actual, is("aaa"));
    }
    @Test
    public void trim_全角() {
        String str = "  aaa　";
        String actual = str.trim();
        assertThat(actual, is("aaa　")); // 全角空白は除去されない
    }

    @Test
    public void util_trim_null() {
        String str = null;
        String actual = StringUtils.trim(str);
        assertThat(actual, is(nullValue()));
        
    }
    
    @Test
    public void util_strip_全角() {
        String str = "  aaa　";
        String actual = StringUtils.strip(str);
        assertThat(actual, is("aaa"));
        
    }
    
    @Test
    public void 結合_null() {
        String str1 = "aaa";
        String str2 = null;
        String actual = str1 + str2;
        assertThat(actual, is("aaanull"));
    }
    
    @Test
    public void 結合_null_util利用() {
        String str1 = "aaa";
        String str2 = null;
        String actual = StringUtils.defaultString(str1) + StringUtils.defaultString(str2);
//        String actual = (str1 == null ? "" : str1) + (str2 == null ? "" : str2);
        assertThat(actual, is("aaa"));
    }
    
    @Test
    public void 結合_null_util_join利用() {
        String str1 = "aaa";
        String str2 = null;
        String actual = StringUtils.join(new String[]{str1, str2});
        assertThat(actual, is("aaa"));
    }
}
