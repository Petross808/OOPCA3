import java.util.*;
/**
 *  Name: Petr Sulc
 *  Class Group: GD2b
 */
public class CA3_Question7
{
    /*
   Will repeatedly ask the user to enter the commands in the format
   buy company qty price
   or
   sell company qty price
   or
   quit
    */
    public static void main(String[] args) {
        Map<String, Queue<Block>> blocks = new HashMap<>();
        Scanner in = new Scanner(System.in);
        String command = "";
        do {
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                String company = in.next();
                int qty = in.nextInt();
                double price = in.nextDouble();
                blocks.putIfAbsent(company, new LinkedList<Block>());
                blocks.get(company).add(new Block(qty, price));
            }
            else if(command.equals("sell"))
            {
                String company = in.next();
                int qty = in.nextInt();
                double price = in.nextDouble();
                sell(blocks.get(company), qty, price);
            }
        }while(!command.equalsIgnoreCase("quit"));
    }

    static double sell(Queue<Block> queue, int qty, double price)
    {
        double profit = 0;
        while(qty > 0 && !queue.isEmpty())
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
