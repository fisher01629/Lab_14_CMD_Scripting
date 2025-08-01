import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


import static java.nio.file.StandardOpenOption.CREATE;
public class FileInspector
{


    public static void main(String[] args)
    {
        int charsCount = 0;
        int linesCount = 0;
        int wordsCount = 0;
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";


        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    linesCount++;
                    charsCount += rec.length();
                    String trimmedLine = rec.trim();
                    if (!trimmedLine.isEmpty()) {
                        String[] words = trimmedLine.split("\\s+");
                        wordsCount += words.length;
                    }
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
                System.out.println("\nSummary Report:");
                System.out.println("File name: " + selectedFile.getName());
                System.out.println("Number of lines: " + linesCount);
                System.out.println("Number of words: " + wordsCount);
                System.out.println("Number of characters: " + charsCount);


            } else  {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
