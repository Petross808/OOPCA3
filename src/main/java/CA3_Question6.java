
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question6
{

    /*
    Will repeatedly ask the user to enter the commands in the format
    buy qty price
    or
    sell qty price
    or
    quit
     */
    public static void main(String[] args) {
        Queue<Block> blocks = new LinkedList<>();
        double profit = 0;
        Scanner in = new Scanner(System.in);
        String command = "";
        do
        {
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();
                blocks.add(new Block(qty, price));
            }
            else if(command.equalsIgnoreCase("sell"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();
                profit += sell(blocks, qty, price);
            }
        } while(!command.equalsIgnoreCase("quit"));
        System.out.println("Total Profit: " + profit);
    }
    static double sell(Queue<Block> queue, int qty, double price)
    {
        double profit = 0;
        while(qty > 0)
        {
            Block current = queue.peek();
            if(current.qty > qty)
            {
                current.qty -= qty;
                profit += (qty * price) - (qty * current.price);
                qty = 0;
            }
            else
            {
                qty -= current.qty;
                profit += (current.qty * price) - (current.qty * current.price);
                queue.remove();
            }
        }
        System.out.println("Profit: " + profit);
        return profit;
    }
}

