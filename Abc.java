import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Abc {

    public Abc(){
    }
    public void asa() throws IOException {
        //Creating a File object for directory
        File directoryPath = new File("D:\\richa\\Simplilearn\\Projects\\First\\temp");
        //List of all files and directories
        boolean flag = true;
        while (flag) {
            String contents[] = directoryPath.list();
            List<String> fileList = Arrays.asList(contents);
            Collections.sort(fileList);
            System.out.println("Following are the files in the location : " + directoryPath);
            fileList.stream().forEach(System.out::println);

            System.out.println("####################################################");
            System.out.println("Please choose the action you want to perform : ");
            System.out.println("\tTo Add New File Enter 1");
            System.out.println("\tTo Delete File Enter 2");
            System.out.println("\tTo Search a File Enter 3");
            System.out.println("\tTo Go Back to Main Menu Enter 4");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter the new file name with .txt extension:");
                    String newFileName = sc.next();
                    String path = directoryPath.toString() + File.separator + newFileName;
                    File f1 = new File(path);
                    if (f1.createNewFile()) {
                        System.out.println("Please enter the content inside file");
                        sc.nextLine();
                        String next = sc.nextLine();
                        Files.writeString(Path.of(path), next);
                    } else {
                        System.out.println("Following file already exists " + newFileName);
                    }
                    break;
                case 2:
                    System.out.println("Enter file name to delete");
                    String deleteFileName = sc.next();
                    String deletePath = directoryPath.toString() + File.separator + deleteFileName;
                    File f2 = new File(deletePath);
                    if (f2.delete()) {
                        System.out.println("File Deleted successfully " + deleteFileName);
                    } else {
                        System.out.println("Following File doesn't exists " + deleteFileName);
                    }
                    break;
                case 3:
                    String searchFile = sc.next();
                    String searchPath = directoryPath.toString() + File.separator + searchFile;
                    File file = new File(searchPath);
                    if (file.exists()) {
                        System.out.println("Entered file exists " + searchFile);
                    } else {
                        System.out.println("Entered file doesn't exists " + searchFile);
                    }
                    break;
                case 4:
                    System.out.println("Going back to the main menu");
                    flag = false;
                    break;
            }
            System.out.println("___________________________________________________");
        }
    }
}
