


import java.io.*;
import java.util.Scanner;

public class FileIO
{
    private String fileName;
    private boolean readWrite;
    private String content;

    final String path = System.getProperty("user.dir");

    public FileIO(String fileName, boolean readWrite)
    {
        this.fileName = fileName;
        this.readWrite = readWrite;
        content = "";

    }
    public void readFile()
    {
        try {
            File myObj = new File(path + File.separator + fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                content += myReader.nextLine() + "\n";

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public String getContent()
    {
        return content;
    }
    public void writeFile(String text)
    {
        FileWriter fw= null;
        try {
            fw = new FileWriter(path + File.separator + fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fw.append(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setContent(String content) {
        this.content = content;
    }





}
