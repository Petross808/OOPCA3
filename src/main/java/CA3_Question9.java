import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *  Name: Petr Sulc
 *  Class Group: GD2b
 */

/*
Direction enum used to indicate direction.
 */
enum DIRECTION {
    NORTH, SOUTH, EAST, WEST;
    public static DIRECTION reverse(DIRECTION dir)
    {
        return switch (dir) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }
}
class Path {
    public int x,y;
    public DIRECTION dir;
    public Path(int x, int y, DIRECTION dir)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}


public class CA3_Question9
{
    public static void display(int[][] image)
    {
        for (int x = 0; x < image.length; x++)
        {
            for (int y = 0; y < image[0].length; y++)
            {
                System.out.printf("%4d", image[x][y]);
            }
            System.out.println();
        }
    }
    public static void solve(int[][] maze, int xStart, int yStart)
    {
        System.out.printf("Solving from [%s, %s]%n", xStart, yStart);
        Stack<Path> paths = new Stack<>();
        getOpenPaths(maze,xStart,yStart).forEach((dir) -> paths.push(new Path(xStart,yStart,dir)));
        Path current;
        while(!paths.empty())
        {
            current = paths.pop();
            int[] tile = getTile(current.x, current.y, current.dir);
            System.out.printf("[%s, %s]%n", tile[0], tile[1]);
            if(!isEmptyValidTile(maze,tile[0], tile[1]))
            {
                continue;
            }
            if(maze[tile[1]][tile[0]] == 2)
            {
                System.out.printf("Reached the end of the maze at [%s, %s]%n", tile[0], tile[1]);
                return;
            }

            Set<DIRECTION> openPaths = getOpenPaths(maze,tile[0],tile[1]);
            openPaths.remove(DIRECTION.reverse(current.dir));
            openPaths.forEach((dir) -> paths.push(new Path(tile[0],tile[1],dir)));
        }
        System.out.println("No end found");
    }

    public static Set<DIRECTION> getOpenPaths(int[][] maze, int xPos, int yPos)
    {
        Set<DIRECTION> out = new HashSet();
        int[] newPos;
        for(DIRECTION dir: DIRECTION.values())
        {
            newPos = getTile(xPos, yPos, dir);
            if(isEmptyValidTile(maze, newPos[0], newPos[1]))
            {
                out.add(dir);
            }
        }
        return out;
    }

    public static int[] getTile(int x, int y, DIRECTION dir)
    {
        int[][] directionValues = {{0,-1},{0,1},{1,0},{-1,0}};
        return new int[] { x + directionValues[dir.ordinal()][0], y + directionValues[dir.ordinal()][1] };
    }

    public static boolean isEmptyValidTile(int[][] maze, int x, int y)
    {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[y][x] != 1;
    }

    public static void main(String[] args)
    {
        int[][] maze = new int[][]
        {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1},
                {1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1},
                {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1},
                {1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1}
        };

        display(maze);
        solve(maze,5,5);
    }
}
