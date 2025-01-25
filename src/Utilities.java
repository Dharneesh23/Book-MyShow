
import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {

    public static HashMap<Character, ArrayList<String>> generateGrid(long numberofseats, String grid) {//method to generate the seat pattern
        var starremoved = grid.split("\\*");
        int total= 0;


        for (String starremove : starremoved) {
            long temp = Long.parseLong(starremove);
            total += temp;
        }


        if (numberofseats % total == 0) {//if it is divisible and no seats left
            var hashmap = new HashMap<Character, ArrayList<String>>();
            char row_name = 'A';//row name
            while (numberofseats > 0) {
                ArrayList<String> row = new ArrayList<>();//to store the every row in array list
                for (int i = 0; i < starremoved.length; i++) {//number of columns
                    long groupSize = Long.parseLong(starremoved[i]);


                    for (int j = 0; j < groupSize; j++) {
                        row.add("[] ");//seats added to the row
                    }


                    if (i < starremoved.length - 1) {
                        row.add("<walk_space>");//to add a space at walk space
                    }
                }
                hashmap.put(row_name, row);//add to hash map
                row_name++;//next row
                numberofseats -= total;
            }

            return hashmap;//return the seat grid
        }
        System.out.println("Enter the correct number of seats:");
        return null;
    }
}