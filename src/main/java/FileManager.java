import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileManager {

    public static void saveVectorToFile(List<Double> vector, String fileName) {
        saveVectorToFile(vector, fileName, "");
    }

    public static void saveVectorToFile(List<Double> vector, String fileName, String shortVecName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(fileName + ".txt");
            out.println("Fs = " + Lab1.Fn);

            String line;
            for (int i = 0; i < vector.size(); i++) {
                line = i + ". " + shortVecName + "[" + i + "] = " + vector.get(i);
                out.println(line);
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
