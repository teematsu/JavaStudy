package javastudy;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;


public class FileUtilsTest {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
        /*
           TemporaryFolderは、newFolder メソッドで一時ディレクトリを作成でき、
           テスト終了時(@Afterに相当)にそのディレクトリを削除する。
           一時ディレクトリは、たとえばWindowsならC:\Users\<username>\AppData\Local\Temp に
           作成される。
        */
    
    
    @Test
    public void forceMkdir_親ディレクトリも作成される() throws IOException {
        File base = tempFolder.newFolder();
        File dir = new File(base.getAbsolutePath() + "/foo/bar");
        FileUtils.forceMkdir(dir);
        assertThat(dir.exists(), is(true));
        assertThat(dir.isDirectory(), is(true));
    }

    @Test
    public void forceMkdir_ディレクトリが存在するときは正常終了する() throws IOException {
        File base = tempFolder.newFolder();
        File dir = new File(base.getAbsolutePath() + "/foo/bar");
        FileUtils.forceMkdir(dir);
        assertThat(dir.exists(), is(true));
        assertThat(dir.isDirectory(), is(true));
        FileUtils.forceMkdir(dir);
        assertThat(dir.isDirectory(), is(true));
    }
    
    @Test(expected = IOException.class)
    public void forceMkdir_ファイルが存在するとIOException() throws IOException {
        File base = tempFolder.newFolder();
        File dir = new File(base.getAbsolutePath() + "/foo");
        dir.createNewFile();
        assertThat(dir.exists(), is(true));
        assertThat(dir.isFile(), is(true));
        FileUtils.forceMkdir(dir);
    }
}
