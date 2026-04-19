import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    // Queue representing cafeteria line (front = next to serve)
    static ArrayDeque<String> line = new ArrayDeque<>();

    // Stores arrival time of each person
    static HashMap<String, Integer> arrivalTime = new HashMap<>();

    // Logical clock (minutes)
    static int currentTime = 0;

    // Statistics
    static long totalWait = 0;
    static int servedCount = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        printHelp();

        while (true) {

            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] parts = input.split(" ");
            String command = parts[0].toUpperCase();

            switch (command) {

                case "HELP":
                    printHelp();
                    break;

                case "ARRIVE":
                    if (parts.length != 2) {
                        System.out.println("Usage: ARRIVE <name>");
                        break;
                    }
                    arrive(parts[1]);
                    break;

                case "VIP_ARRIVE":
                    if (parts.length != 2) {
                        System.out.println("Usage: VIP_ARRIVE <name>");
                        break;
                    }
                    vipArrive(parts[1]);
                    break;

                case "SERVE":
                    serve();
                    break;

                case "LEAVE":
                    if (parts.length != 2) {
                        System.out.println("Usage: LEAVE <name>");
                        break;
                    }
                    leave(parts[1]);
                    break;

                case "PEEK":
                    peek();
                    break;

                case "SIZE":
                    System.out.println("Size: " + line.size());
                    break;

                case "PRINT":
                    System.out.println("Line (front -> back): " + line);
                    break;

                case "TICK":
                    if (parts.length != 2) {
                        System.out.println("Usage: TICK <minutes>");
                        break;
                    }
                    tick(parts[1]);
                    break;

                case "STATS":
                    stats();
                    break;

                case "EXIT":
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Unknown command. Type HELP.");
            }
        }
    }

    // Prints list of commands
    static void printHelp() {

        System.out.println("Cafeteria Line Manager — Commands:");
        System.out.println("HELP");
        System.out.println("ARRIVE <name>");
        System.out.println("VIP_ARRIVE <name>");
        System.out.println("SERVE");
        System.out.println("LEAVE <name>");
        System.out.println("PEEK");
        System.out.println("SIZE");
        System.out.println("PRINT");
        System.out.println("TICK <minutes>");
        System.out.println("STATS");
        System.out.println("EXIT");
    }

    // Normal arrival at back of queue
    static void arrive(String name) {

        if (!validName(name)) return;

        if (arrivalTime.containsKey(name)) {
            System.out.println("Name already in system");
            return;
        }

        line.addLast(name); // O(1)
        arrivalTime.put(name, currentTime);

        System.out.println(name + " arrived at time " + currentTime +
                ". Line size = " + line.size());
    }

    // VIP arrival at front
    static void vipArrive(String name) {

        if (!validName(name)) return;

        if (arrivalTime.containsKey(name)) {
            System.out.println("Name already in system");
            return;
        }

        line.addFirst(name); // O(1)

        arrivalTime.put(name, currentTime);

        System.out.println("VIP " + name + " arrived at time " +
                currentTime + " (front). Line size = " + line.size());
    }

    // Serve next person
    static void serve() {

        if (line.isEmpty()) {
            System.out.println("No one to serve.");
            return;
        }

        String name = line.removeFirst();

        int wait = currentTime - arrivalTime.get(name);

        totalWait += wait;
        servedCount++;

        arrivalTime.remove(name);

        System.out.println("Served: " + name + " (waited " + wait + " min).");
    }

    // Person leaves line voluntarily
    static void leave(String name) {

        if (line.removeFirstOccurrence(name)) { // O(n)

            arrivalTime.remove(name);

            System.out.println(name + " left the line voluntarily. Line size = "
                    + line.size());
        } else {
            System.out.println("Not found");
        }
    }

    // Show next person
    static void peek() {

        if (line.isEmpty()) {
            System.out.println("Line is empty.");
        } else {
            System.out.println("Next: " + line.peekFirst());
        }
    }

    // Advance logical clock
    static void tick(String minutesStr) {

        try {

            int minutes = Integer.parseInt(minutesStr);

            if (minutes < 0) {
                System.out.println("Minutes must be >= 0");
                return;
            }

            currentTime += minutes;

            System.out.println("Time advanced by " + minutes +
                    " minutes. Current time = " + currentTime);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }

    // Print statistics
    static void stats() {

        double avg = 0.0;

        if (servedCount > 0) {
            avg = (double) totalWait / servedCount;
        }

        System.out.printf("Served count = %d, Avg wait = %.2f min.%n",
                servedCount, avg);
    }

    // Validate names
    static boolean validName(String name) {

        if (name.isEmpty() || name.contains(" ")) {
            System.out.println("Invalid name.");
            return false;
        }

        return true;
    }
}