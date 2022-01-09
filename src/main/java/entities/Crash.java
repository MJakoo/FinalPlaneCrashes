package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.Extension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Crash extends BaseEntity {

    public static List<Crash> crashList = new ArrayList<>();
    private LocalDate date;
    private String time;
    private Location location;
    private Operator operator;
    private String flight;
    private String route;
    private String type;
    private String registration;
    private String cnIn;
    private int aboard;
    private int fatalities;
    private int ground;
    private int survivors;
    private Double survivalRate;
    private String summary;
    private String clustID;

    public static void listCrashes() {
        for (Crash l : crashList) {
            System.out.printf(l.toString());
        }
    }

    @Override
    public String toString() {
        return
                id +
                        ". Date: " + date +
                        ", Time: " + time +
                        ", Location: " + location.getLocationContent() +
                        ", Operator: " + operator.getOperatorContent() +
                        ", Flight: " + flight +
                        ", Route: " + route +
                        ", Type: " + type +
                        ", Registration: " + registration +
                        ", CnIn: " + cnIn +
                        ", Aboard: " + aboard +
                        ", Fatalities: " + fatalities +
                        ", Ground: " + ground +
                        ", Survivors: " + survivors +
                        ", SurvivalRate: " + survivalRate +
                        ", Summary: " + summary +
                        ", ClustID: " + clustID + "\n";
    }

    public static void crashesMenuContent() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            System.out.printf("Please select one of these options...\n");
            System.out.printf("1. Get some crashes based on range... (ex: 5 100)\n");
            System.out.printf("2. List only the selected fields of each entity(Crash)...\n");
            System.out.printf("3. Sort crashes...\n");
            System.out.printf("4. Search crashes...\n");
            System.out.printf("5. List column names...\n");
            System.out.printf("6. Filter...\n");
            System.out.printf("7. Back...\n");
            String selectedOp = reader.readLine();
            if (Extension.checkSelectedNumber(selectedOp)) {
                switch (Integer.parseInt(selectedOp)) {
                    case 1:
                        Extension.getDataBasedOnRange(Crash.crashList);
                        askExportQ();
                        break;
                    case 2:
                        Extension.listOnlySelectedField(Crash.crashList);
                        break;
                    case 3:
                        sortMenu();
                        askExportQ();
                        break;
                    case 4:
                        searchFor();
                        askExportQ();
                        break;
                    case 5:
                        listColumnName();
                        break;
                    case 6:
                        filterCrashes();
                        askExportQ();
                        break;
                    case 7:
                        return;
                    default:
                        System.out.printf("Please, choose the correct option...");
                        break;
                }
            } else {
                System.out.println("Please, enter only digit");
            }
        }
    }

    public static void sortMenu() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            System.out.println("1. Date - descending order");
            System.out.println("2. Date - ascending order");
            System.out.println("3. Location - descending order");
            System.out.println("4. Location - ascending order");
            System.out.println("5. Back...");
            String selected = reader.readLine();
            if (Extension.checkSelectedNumber(selected)) {
                int sel = Integer.parseInt(selected);
                switch (sel) {
                    case 1:
                        Extension.sortData(Crash.crashList, null, 1);
                        break;
                    case 2:
                        Extension.sortData(Crash.crashList, null, 2);
                        break;
                    case 3:
                        Extension.sortData(Crash.crashList, null, 3);
                        break;
                    case 4:
                        Extension.sortData(Crash.crashList, null, 4);
                        break;
                    case 5:
                        return;
                    default:
                        break;
                }
                askExportQ();
            } else {
                System.out.println("Please, enter only digit");
            }
        }
    }

    public static void searchFor() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            System.out.println("1. Search crashes with location...");
            System.out.println("2. Search crashes with operator");
            System.out.println("3. Search crashes by year");
            System.out.println("4. Search crashes by date (ex: 12/30/1999)");
            System.out.println("5. Back...");
            System.out.println("Select one option...");
            String selected = reader.readLine();

            if (Extension.checkSelectedNumber(selected)) {
                int sel = Integer.parseInt(selected);
                switch (sel) {
                    case 1:
                        System.out.println("Please, write location name...");
                        String lName = reader.readLine();
                        Extension.sortedList = crashList.stream().filter(w -> w.getLocation().getLocationContent().toLowerCase().contains(lName.toLowerCase())).collect(Collectors.toList());
                        Extension.sortedList
                                .forEach(s -> {
                                    System.out.println(s);
                                });
                        break;
                    case 2:
                        System.out.println("Please, write operator name...");
                        String oName = reader.readLine();
                        Extension.sortedList = crashList.stream().filter(w -> w.getOperator().getOperatorContent().toLowerCase().contains(oName.toLowerCase())).collect(Collectors.toList());
                        Extension.sortedList
                                .forEach(s -> {
                                    System.out.println(s);
                                });
                        break;
                    case 3:
                        while (true) {
                            System.out.println("Please, write year...");
                            String year = reader.readLine();
                            if (Extension.checkSelectedNumber(year)) {
                                int y = Integer.parseInt(year);
                                Extension.sortedList = crashList.stream().filter(w -> w.getDate().getYear() == y).collect(Collectors.toList());
                                Extension.sortedList
                                        .forEach(s -> {
                                            System.out.println(s);
                                        });
                                break;
                            } else {
                                System.out.println("Please, enter the only digits...");
                            }
                        }
                        break;
                    case 4:
                        while (true) {
                            System.out.println("Please, write date...(05/20/1909)");
                            String date = reader.readLine();
                            //convert String to LocalDate
                            if (Extension.checkDate(date)) {
                                LocalDate localDate = Extension.parseDate(date);
                                System.out.println(localDate);
                                Extension.sortedList = crashList.stream().filter(w -> w.getDate().equals(localDate)).collect(Collectors.toList());
                                Extension.sortedList
                                        .forEach(s -> {
                                            System.out.println(s);
                                        });
                                break;
                            } else {
                                System.out.println("Please, enter date correct (ex: 05/20/1909 )");
                            }
                        }
                    case 5:
                        return;
                    default:
                        break;
                }
                askExportQ();
            } else {
                System.out.println("Please, enter only digit");
            }
        }
    }

    public static void listColumnName() {
        System.out.println("---- Column names----");
        Class crashClass = new Crash().getClass();
        Field[] fields = crashClass.getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            System.out.println(i + ". " + fields[i].getName());
        }
        System.out.println("\n");
    }

    public static void filterCrashes() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            System.out.println("1. Crashes that Happened START WITH smt..");
            System.out.println("2. Crashes that Happened END WITH smt..");
            System.out.println("3. Crashes that Unknown flights... ");
            System.out.println("4. Crashes that Unknown routes... ");
            System.out.println("5. Crashes that there are no survivors..");
            System.out.println("6. Crashes that High Fatality..");
            System.out.println("7. Crashes that Low Fatality..");
            System.out.println("8. Back...");
            System.out.println("Select one option...");
            String selected = reader.readLine();
            if (Extension.checkSelectedNumber(selected)) {

                int sel = Integer.parseInt(selected);
                switch (sel) {
                    case 1:
                        System.out.println("Write: ");
                        String start = reader.readLine();
                        Extension.sortedList = crashList.stream().filter(f -> f.getLocation().getLocationContent().toLowerCase().startsWith(start.toLowerCase()) ||
                                f.getOperator().getOperatorContent().toLowerCase().startsWith(start.toLowerCase())).collect(Collectors.toList());
                        Extension.sortedList.forEach(w -> {
                            System.out.println(w);
                        });
                        break;
                    case 2:
                        System.out.println("Write: ");
                        String end = reader.readLine();
                        Extension.sortedList = crashList.stream().filter(f -> f.getLocation().getLocationContent().toLowerCase().endsWith(end.toLowerCase()) ||
                                f.getOperator().getOperatorContent().toLowerCase().endsWith(end.toLowerCase())).collect(Collectors.toList());
                        Extension.sortedList.forEach(w -> {
                            System.out.println(w);
                        });
                        break;
                    case 3:
                        Extension.sortedList = crashList.stream().filter(f -> f.getFlight() == null).collect(Collectors.toList());
                        Extension.sortedList.forEach(w -> {
                            System.out.println(w);
                        });
                        break;
                    case 4:
                        Extension.sortedList = crashList.stream().filter(f -> f.getRoute() == null).collect(Collectors.toList());
                        Extension.sortedList.forEach(w -> {
                            System.out.println(w);
                        });
                        break;
                    case 5:
                        Extension.sortedList = crashList.stream().filter(f -> f.getSurvivors() == 0).collect(Collectors.toList());
                        Extension.sortedList.forEach(w -> {
                            System.out.println(w);
                        });
                        break;
                    case 6:
                        Extension.sortedList = crashList.stream().filter(f -> f.getClustID().equals("High Fatality")).collect(Collectors.toList());
                        Extension.sortedList.forEach(w -> {
                            System.out.println(w);
                        });
                        break;
                    case 7:
                        Extension.sortedList = crashList.stream().filter(f -> f.getClustID().equals("Low Fatality")).collect(Collectors.toList());
                        Extension.sortedList.forEach(w -> {
                            System.out.println(w);
                        });
                        break;
                    case 8:
                        return;
                    default:
                        break;
                }
                askExportQ();
            } else {
                System.out.println("Please, enter only digit");
            }
        }

    }

    public static void askExportQ() throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            System.out.println("Do you want to export the result to excel?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Do you want to get some data with range ?");
            System.out.println("3. Yes");
            System.out.println("4. No");
            String selected = reader.readLine();
            if (Extension.checkSelectedNumber(selected)) {
                switch (Integer.parseInt(selected)) {
                    case 1:
                        Extension.exportToExcel();
                        break;
                    case 2:
                        return;
                    case 3:
                        Extension.getDataBasedOnRange(Extension.sortedList);
                        break;
                    case 4:
                        return;
                    default:
                        break;
                }
                break;
            } else {
                System.out.println("Please, select correct option...");
            }

        }
    }

}
