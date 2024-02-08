import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *  Name: Petr Sulc
 *  Class Group: GD2b
 */

public class CA3_Question3
{
    public static void readFile(String fileName) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File(fileName));
        HashMap<String, Set<Integer>> identifiers = new HashMap<>();
        for(int lineNum = 1; in.hasNextLine(); lineNum++)
        {
            for(String word: in.nextLine().split("[^A-Za-z0-9_]+"))
            {
                if(word == "") continue;
                identifiers.putIfAbsent(word, new TreeSet());
                identifiers.get(word).add(lineNum);
            }
        }
        identifiers.forEach((word,lines) -> System.out.println(word + " " + lines));
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile("src/main/java/CA3_Question1.java");
    }
}