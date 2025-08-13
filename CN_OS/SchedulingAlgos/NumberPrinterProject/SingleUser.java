import java.util.Scanner;

public class SingleUser {
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter();
        Thread t1 = new Thread(printer::runProgram);
        t1.start();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command (start, stop, exit):");
            String cmd = sc.nextLine().trim().toLowerCase();

            switch (cmd) {
                case "start":
                    printer.start();
                    break;
                case "stop":
                    printer.stop();
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
}

