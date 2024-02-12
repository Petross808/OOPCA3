import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *  Name: Petr Sulc
 *  Class Group: GD2b
 */
public class CA3_Question10
{

    public static void main(String[] args) throws FileNotFoundException
    {
        Map<String, TreeSet<DistanceTo>> cities = new HashMap<>();
        Scanner file = new Scanner(new File("cities_connections.txt"));
        while(file.hasNextLine())
        {
            String city1 = file.next();
            String city2 = file.next();
            int distance = file.nextInt();
            cities.putIfAbsent(city1, new TreeSet<>());
            cities.get(city1).add(new DistanceTo(city2,distance));
        }

        file = new Scanner(new File("cities_connections.txt"));

        String from = file.next();
        PriorityQueue<DistanceTo> queue = new PriorityQueue<>();
        queue.add(new DistanceTo(from,0));
        Map<String, Integer> shortestKnownDistances = new HashMap<>();

        final DistanceTo current[] = new DistanceTo[1];
        while(!queue.isEmpty())
        {
            current[0] = queue.poll();
            if(!shortestKnownDistances.containsKey(current[0].getTarget()))
            {
                shortestKnownDistances.put(current[0].getTarget(), current[0].getDistance());
                cities.get(current[0].getTarget()).forEach((city) -> queue.add(new DistanceTo(city.getTarget(), current[0].getDistance() + city.getDistance())));
            }
        }
        System.out.println("Starting point: " + from);
        System.out.println(shortestKnownDistances);

    }
}
