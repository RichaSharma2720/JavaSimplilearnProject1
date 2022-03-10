import java.io.IOException;
import java.util.Scanner;

public class FileCreationDeletion {
    public static void main(String[] args) throws IOException {
        BusinessOperation r = new BusinessOperation();
        //Abc a = new Abc();
        Scanner sc=new Scanner(System.in);
        System.out.println("******************************************************");
        System.out.println("*********  Welcome to LockersMe.com **************");
        System.out.println("*********  Developed By : Richa  *********************");
        System.out.println("******************************************************");



        boolean Flag = true;
        while(Flag) {
            System.out.println("#######################################");
            System.out.println("\t\tSelect the option one");
            System.out.println("\t\t1. Retrieve Files");
            System.out.println("\t\t2. More Business Operations");
            System.out.println("\t\t3. Close Program");
            System.out.println("#######################################");
            int number=sc.nextInt();
            switch (number) {
                case 1:
                    r.getFolderContent();
                    break;
                case 2:
                    r.testing();
                    break;
                case 3:
                    System.out.println("Closing The Program");
                    Flag = false;
                    break;
                default:
                    System.out.println("Invalid Value.............");
            }
        }
    }
}
