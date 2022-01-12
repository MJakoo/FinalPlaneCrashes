import entities.Crash;
import entities.Location;
import entities.Operator;
import utils.Converter;
import utils.Extension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PlaneCrashesMain {

    public static void main(String[] args) throws IOException {
        Converter.exportDataFromExcelToList();
        System.out.println("----------Welcome to Console Application-----------\n");
        mainMenu();
    }

    public static void mainMenu() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            menuContent();
            String selectedOp = reader.readLine();
            if (Extension.checkSelectedNumber(selectedOp)) {
                switch (Integer.parseInt(selectedOp)) {
                    case 1:
                        Crash.listCrashes();
                        Crash.crashesMenuContent()  ;
                        break;
                    case 2:
                        Location.listLocations();
                        Location.locationMenuContent();
                        break;
                    case 3:
                        Operator.listOperators();
                        Operator.operationMenuContent();
                        break;
                    case 4:
                        System.exit(1);
                        break;
                    default:
                        System.out.printf("Please, choose the correct option...");
                        break;
                }
            }
            else {
                System.out.println("Please, enter only digit");
            }
        }
    }

    public static void menuContent() {
        System.out.printf("Please select one of these options...\n");
        System.out.printf("1. List all crashes...\n");
        System.out.printf("2. List all locations that accident happened there...\n");
        System.out.printf("3. List all operators...\n");
        System.out.printf("4. Exit the system...\n");
    }

}
