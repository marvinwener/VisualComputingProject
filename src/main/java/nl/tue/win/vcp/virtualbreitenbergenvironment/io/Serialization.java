package nl.tue.win.vcp.virtualbreitenbergenvironment.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Provides methods to read and write objects to files.
 *
 * @author maikel
 */
public class Serialization {

    public static Object read(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        }
    }
}
