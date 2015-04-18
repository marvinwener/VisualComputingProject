package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import static nl.tue.win.vcp.virtualbreitenbergenvironment.model.HeatSource.TEXTURE_PATH;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maikel
 */
public class HeatSourceTest {

    public HeatSourceTest() {
    }

    @Test
    public void testTextureExistance() throws IOException {
        // check whether we can load the texture file succesfully
        HeatSource.DRAW_GRADIENT = true;
        final String path = HeatSource.TEXTURE_PATH;
        File f = new File(path);
        assertTrue(f.exists());
    }

}
