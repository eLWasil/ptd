import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileManager {

    public static void saveVectorToFile(List<Double> vector, String fileName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(fileName + ".txt");
            out.println("Fs = " + Lab1.Fn);

            for (int i = 0; i < vector.size(); i++) {
                out.println(vector.get(i));
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot save to file.");
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
