import java.util.Scanner;
import java.util.Stack;
/**
 *  Name: Petr Sulc
 *  Class Group: GD2b
 */

public class CA3_Question1
{
    public static void runSimulation()
    {
        Scanner input = new Scanner(System.in);
        Stack<Integer> driveway = new Stack();
        Stack<Integer> street = new Stack();
        int action;
        do {
            System.out.print("\nAction: ");
            action = input.nextInt();
        } while(handleAction(driveway, street, action) == 1);
    }

    private static int handleAction(Stack<Integer> in, Stack<Integer> out, int action)
    {
        printState(in, out);
        if(action > 0)
        {
            in.push(action);
            printState(in, out);
            return 1;
        }
        else if (action < 0)
        {
            action = -action;
            while(in.peek() != action)
            {
                out.push(in.pop());
                printState(in, out);
            }

            in.pop();
            printState(in, out);

            while(!out.empty())
            {
                in.push(out.pop());
                printState(in, out);
            }
            return 1;
        }
        else
        {
            return 0;
        }
    }

    private static void printState(Stack<Integer> in, Stack<Integer> out)
    {
        System.out.print("Driveway: ");
        for(int car: in)
        {
            System.out.print(car + " ");
        }
        System.out.print("\nStreet: ");
        for(int car: out)
        {
            System.out.print(car + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
