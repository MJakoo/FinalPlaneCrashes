package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.Extension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Location extends BaseEntity {

    public static List<Location> locationList = new ArrayList<>();

    private String locationContent;

    public Location(String locationContent) {
        this.locationContent = locationContent;
    }

    static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void listLocations() {
        System.out.println("All locations that crashes happened there..");
        int count = 1;
        for (Location l : locationList) {
            System.out.printf(count + ". " + l.locationContent + "...\n");
            count++;
        }
    }

    public static void locationMenuContent() throws IOException {

        while (true) {
            System.out.printf("Please select one of these options...\n");
            System.out.printf("1. Get locations based on range... (ex:5 100)\n");
            System.out.printf("2. Get locations ascending order...\n");
            System.out.printf("3. Get locations descending order... \n");
            System.out.printf("4. Search with locations name... \n");
            System.out.printf("5. Back...\n");
            String selectedOp = reader.readLine();
            int sel = Integer.parseInt(selectedOp);
            switch (sel) {
                case 1:
                    Extension.getLocDataBasedOnRange(Location.locationList);
                    break;
                case 2:
                    getLocDescendingOrder();
                    break;
                case 3:
                    getLocationAscendingOrder();
                    break;
                case 4:
                    searchWithLocName();
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }

    public static void getLocDescendingOrder() throws IOException {
        Extension.sortedLocList = locationList.stream().sorted(Comparator.comparing(Location::getLocationContent)).collect(Collectors.toList());
        Extension.sortedLocList.forEach(w -> {
            System.out.println(w.locationContent);
        });
        Extension.getLocDataBasedOnRange(Extension.sortedLocList);
    }

    public static void getLocationAscendingOrder() throws IOException {
        List<Location> list = locationList.stream().sorted(Comparator.comparing(Location::getLocationContent)).collect(Collectors.toList());
        Collections.reverse(list);
        Extension.sortedLocList = list;
        Extension.sortedLocList
                .forEach(w -> {
                    System.out.println(w.locationContent);
                });
        Extension.getLocDataBasedOnRange(Extension.sortedLocList);
    }

    public static void searchWithLocName() throws IOException {
        System.out.println("Please, write: ");
        String name = reader.readLine();
        Extension.sortedLocList = locationList.stream().filter(w -> w.locationContent.toLowerCase().trim().startsWith(name.toLowerCase().trim()) ||
                w.locationContent.toLowerCase().trim().endsWith(name.toLowerCase().trim()) ||
                w.locationContent.toLowerCase().trim().contains(name.toLowerCase().trim())).collect(Collectors.toList());
        Extension.sortedLocList.forEach(w -> {
            System.out.println(w.locationContent);
        });
        Extension.getLocDataBasedOnRange(Extension.sortedLocList);
    }
}


