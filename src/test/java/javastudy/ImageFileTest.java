package javastudy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class ImageFileTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void getFormatName_JPEGの場合() throws IOException {
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
    public void getFormatName_PNGの場合() throws IOException {
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
    public void getFormatName_PDFの場合() throws IOException {
        URL imageFile = getClass().getResource("ImageFileTest/sample.pdf");
        ImageInputStream iis = ImageIO.createImageInputStream(imageFile.openStream());
        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

        assertThat(readers.hasNext(), is(false));
    }

    @Test
    public void getFormatName_空のファイルの場合() throws IOException {
        URL imageFile = getClass().getResource("ImageFileTest/empty.dat");
        ImageInputStream iis = ImageIO.createImageInputStream(imageFile.openStream());
        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

        assertThat(readers.hasNext(), is(false));
    }
 
    
    @Test
    public void 幅と高さの取得_JPEG() throws IOException {
        URL imageFile = getClass().getResource("ImageFileTest/sample.jpg");
        
        try (ImageInputStream iis = ImageIO.createImageInputStream(imageFile.openStream())) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            ImageReader reader = readers.next();

            reader.setInput(iis);
            int width = reader.getWidth(0);
            int height = reader.getHeight(0);
            reader.dispose();
            
            assertThat(width, is(150));
            assertThat(height, is(99));
        }
        
    }
    
    @Test
    public void 幅と高さの取得_PNG() throws IOException {
        URL imageFile = getClass().getResource("ImageFileTest/sample.png");
        
        try (ImageInputStream iis = ImageIO.createImageInputStream(imageFile.openStream())) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            ImageReader reader = readers.next();

            reader.setInput(iis);
            int width = reader.getWidth(0);
            int height = reader.getHeight(0);
            reader.dispose();
            
            assertThat(width, is(150));
            assertThat(height, is(99));
        }
        
    }
 
    @Test
    public void JPEGの再保存() throws IOException {
        File tmp = temporaryFolder.newFile();
        URL imageFile = getClass().getResource("ImageFileTest/sample.jpg");
        
        BufferedImage image = ImageIO.read(imageFile);

//        ImageIO.write(image, "JPEG", tmp);

        ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpeg").next();
        try (ImageOutputStream os = ImageIO.createImageOutputStream(tmp)) {
            imageWriter.setOutput(os);
            JPEGImageWriteParam param = new JPEGImageWriteParam(null);
            
            // Qualityを明示的に指定する。
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.9f);
            
            imageWriter.write(null, new IIOImage(image, null, null), param);
            imageWriter.dispose();
        }
        // 結果の検証のしようがないので、例外なしでOKとする。
        
    }

}

