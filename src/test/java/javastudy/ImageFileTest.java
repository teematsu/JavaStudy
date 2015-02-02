package javastudy;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImageFileTest {

    @Test
    public void JPEGの場合() throws IOException {
        URL imageFile = getClass().getResource("ImageFileTest/flower.jpg");
        ImageInputStream iis = ImageIO.createImageInputStream(imageFile.openStream());
        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
        ImageReader reader = readers.next();
        String format = reader.getFormatName();
        
        int c = 1;
        for (; readers.hasNext(); c++) {}
        
        assertThat(format, is("JPEG"));
        assertThat(c, is(1));
        
    }

    @Test
    public void PNGの場合() throws IOException {
        URL imageFile = getClass().getResource("ImageFileTest/flower.png");
        ImageInputStream iis = ImageIO.createImageInputStream(imageFile.openStream());
        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
        ImageReader reader = readers.next();
        String format = reader.getFormatName();

        int c = 1;
        for (; readers.hasNext(); c++) {}
        
        assertThat(format, is("png"));
        assertThat(c, is(1));
        
    }

    @Test
    public void PDFの場合() throws IOException {
        URL imageFile = getClass().getResource("ImageFileTest/sample.pdf");
        ImageInputStream iis = ImageIO.createImageInputStream(imageFile.openStream());
        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

        assertThat(readers.hasNext(), is(false));
    }

    @Test
    public void 空のファイルの場合() throws IOException {
        URL imageFile = getClass().getResource("ImageFileTest/empty.dat");
        ImageInputStream iis = ImageIO.createImageInputStream(imageFile.openStream());
        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

        assertThat(readers.hasNext(), is(false));
    }
    
}

