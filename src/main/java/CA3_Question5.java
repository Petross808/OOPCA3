import java.util.LinkedList;
import java.util.Scanner;

/**
 *  Name: Petr Sulc
 *  Class Group: GD2b
 */

public class CA3_Question5
{

    public static void main(String[] args)
    {
        LinkedList<String> land = new LinkedList<>();
        LinkedList<String> takeoff = new LinkedList<>();

        Scanner in = new Scanner(System.in);
        boolean running = true;
        while(running)
        {
            System.out.print("Enter command: ");
            switch (in.next())
            {
                case "next":
                    if(land.isEmpty()) {
                        if (takeoff.isEmpty()) {
                            System.out.println("No flights in queue");
                            break;
                        }
                        System.out.println("Flight " + takeoff.pop() + " took off");
                    }
                    else {
                        System.out.println("Flight " + land.pop() + " landed");
                    }
                    break;
                case "quit":
                    running = false;
                    break;
                case "takeoff":
                    takeoff.add(in.next());
                    break;
                case "land":
                    land.add(in.next());
                    break;
                default:
                    System.out.println("Invalid input");
                    in = new Scanner(System.in);
                    break;
            }
        }
    }
}
