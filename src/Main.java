import command.CommandProcessor;
import graph.Graph;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph();

        CommandProcessor processor =
                new CommandProcessor(graph);

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------------");
        System.out.println(" Urban Transport System");
        System.out.println("--------------------------------");
        System.out.println("Type HELP to see commands.");
        System.out.println();

        boolean running = true;

        while (running) {

            System.out.print("> ");

            String line = scanner.nextLine();

            running = processor.execute(line);

        }

        scanner.close();

        System.out.println();
        System.out.println("Program terminated.");

    }

}


