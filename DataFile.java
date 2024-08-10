import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataFile {
	// Generic method to save to a file
    public static <T extends Serializable> void saveToFile(T objectToSave, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(objectToSave);
            System.out.println("Serialized data is saved in " + filename);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Generic method to load to a file
    public static <T extends Serializable> T loadFromFile(String filename) {
        T object = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            object = (T) in.readObject();
            System.out.println("Deserialized data from " + filename);
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return object;
    }
}
