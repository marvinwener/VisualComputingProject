package nl.tue.win.vcp.virtualbreitenbergenvironment.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    public static void write(Object obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        }
    }
}
