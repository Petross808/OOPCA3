import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question2
{
    /*
        Starter function to create the 2D array and populate it with 0

     */
    public static int[][]  floodFillStart() {
        Scanner kb = new Scanner(System.in);
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                arr[x][y] = 0;
            }
        }

        System.out.print("Row: ");
        int r = kb.nextInt();
        System.out.print("Column: ");
        int c = kb.nextInt();
        System.out.println();
        fill(r, c, arr);
        return arr;
    }
    /*
        Helper function to display the image
     */
    public static void display(int[][] arr)
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }
    private static void fill(int r, int c, int[][] arr)
    {
        Stack<Vector> toFill = new Stack();
        Vector current;
        int step = 0;

        toFill.push(new Vector(c,r));
        while(!toFill.empty())
        {
            step++;
            current = toFill.pop();
            arr[current.y][current.x] = step;
            for(Vector next: current.GetNeighbours())
            {
                if(next.existsInTable(arr) && arr[next.y][next.x] == 0 && !toFill.contains(next))
                {
                    toFill.push(next);
                }
            }
        }
        display(arr);
    }

    public static void start() { int[][] arr = floodFillStart(); }
    public static void main(String[] args) {
        start();
    }

}

class Vector
{
    public int x;
    public int y;
    Vector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector[] GetNeighbours()
    {
        return new Vector [] {
                new Vector(x+1,y),
                new Vector(x-1,y),
                new Vector(x,y+1),
                new Vector(x,y-1)
        };
    }

    public boolean existsInTable(int[][] table)
    {
        return (this.x >= 0 && this.x < table[0].length && this.y >= 0 && this.y < table.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}