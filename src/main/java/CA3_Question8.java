import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 *  Name: Petr Sulc
 *  Class Group: GD2b
 */
public class CA3_Question8 {

    /*
        Reads in an equation from the user
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter equation");
        String equation = in.nextLine().trim();
        Stack<Double> numbers = new Stack<>();
        Stack<String> operands = new Stack<>();
        Iterator<Integer> iter = equation.chars().iterator();
        String cache = "";
        while(iter.hasNext())
        {
            System.out.println("test");
            String current = "" + (char)iter.next().intValue();
            if(current.matches("\\A[.0-9]\\Z"))
            {
                cache += current;
            }
            else if(current.matches("\\A[+\\-*/()]\\Z"))
            {
                if(!cache.isEmpty())
                {
                    numbers.push(Double.parseDouble(cache));
                    cache = "";
                }
                switch(current)
                {
                    case ")":
                        while(!operands.empty() && !operands.peek().equals("("))
                            evalTop(numbers,operands);
                        operands.pop();
                        break;
                    case "-":
                        cache += current;
                        current = "+";
                    case "+":
                        while(!operands.empty() && operands.peek().matches("\\A[*/]\\Z"))
                            evalTop(numbers,operands);
                    default:
                        operands.push(current);
                        break;
                }
            }
        }
        if(!cache.isEmpty())
        {
            numbers.push(Double.parseDouble(cache));
        }
        while(!operands.empty())
        {
            evalTop(numbers,operands);
        }
        System.out.println("Result: " + numbers.pop());
    }

    public static void evalTop(Stack<Double> numbers, Stack<String> operands)
    {
        System.out.println(numbers);
        System.out.println(operands);
        double num1 = numbers.pop();
        double num2 = numbers.pop();
        switch(operands.pop())
        {
            case "+":
                 numbers.push(num2 + num1);
                 return;
            case "-":
                numbers.push(num2 - num1);
                return;
            case "*":
                numbers.push(num2 * num1);
                return;
            case "/":
                numbers.push(num2 / num1);
                return;
            default:
                return;
        }
    }
}
