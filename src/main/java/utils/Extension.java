package utils;

import entities.Crash;
import entities.Location;
import entities.Operator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Extension {

    public static List<Crash> sortedList = new ArrayList<>();
    public static List<Location> sortedLocList = new ArrayList<>();
    public static List<Operator> sortedOpList = new ArrayList<>();

    public static LocalDate parseDate(String date) {
        String[] ss = date.split("/");
        int year = 0;
        int month = 0;
        int day = 0;
        for (int i = ss.length - 1; i > -1; i--) {
            if (i == 0)
                month = Integer.parseInt(ss[i]);
            else if (i == 1)
                day = Integer.parseInt(ss[i]);
            else {
                if (ss[i].length() != 4) {
                    year = Integer.parseInt("19" + ss[i]);
                } else {
                    year = Integer.parseInt(ss[i]);
                }
            }
        }
        LocalDate dt = LocalDate.of(year, month, day);
        return dt;
    }

    public static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void getDataBasedOnRange(List<Crash> list) throws IOException {
        if (list == null || list.size() != 0) {
            System.out.println("Please, write range  (ex: 5 100)");
            String from = "";
            String to = "";
            while (true) {
                System.out.println("From: ex( 5 )");
                from = reader.readLine();
                System.out.println("To: ex( 100 )");
                to = reader.readLine();
                if (Extension.checkSelectedNumber(from) && Extension.checkSelectedNumber(to)) {
                    if ((Integer.parseInt(from) <= Integer.parseInt(to) && Integer.parseInt(from) > 0)) {
                        Extension.sortedList = list.stream().skip(Integer.parseInt(from) - 1).limit(Integer.parseInt(to) - Integer.parseInt(from) + 1).collect(Collectors.toList());
                        Extension.sortedList
                                .forEach(w -> {
                                    System.out.println(w.toString());
                                });
                        break;
                    } else {
                        System.out.println("Please, enter correct range");
                    }
                } else {
                    System.out.println("Please, enter only digit");
                }
            }
        }
    }

    public static void getLocDataBasedOnRange(List<Location> list) throws IOException {
        if (list == null || list.size() != 0) {
            System.out.println("Please, write range  (ex: 5 100)");
            while (true) {
                System.out.println("From: ex( 5 )");
                String from = reader.readLine();
                System.out.println("To: ex( 100 )");
                String to = reader.readLine();
                if (Extension.checkSelectedNumber(from) && Extension.checkSelectedNumber(to)) {
                    if ((Integer.parseInt(from) <= Integer.parseInt(to) && Integer.parseInt(from) > 0)) {
                        Extension.sortedLocList = list.stream().skip(Integer.parseInt(from) - 1).limit(Integer.parseInt(to) - Integer.parseInt(from) + 1).collect(Collectors.toList());
                        Extension.sortedLocList.forEach(w -> {
                            System.out.println(w.getLocationContent());
                        });
                        break;
                    } else {
                        System.out.println("Please, enter correct range");
                    }
                } else {
                    System.out.println("Please, enter only digit");
                }
            }
        }
    }

    public static void getOperatorsDataBasedOnRange(List<Operator> list) throws IOException {
        if (list == null || list.size() != 0) {
            System.out.println("Please, write range  (ex: 5 100)");
            while (true) {
                System.out.println("From: ex( 5 )");
                String from = reader.readLine();
                System.out.println("To: ex( 100 )");
                String to = reader.readLine();
                if (Extension.checkSelectedNumber(from) && Extension.checkSelectedNumber(to)) {
                    if ((Integer.parseInt(from) <= Integer.parseInt(to) && Integer.parseInt(from) > 0)) {
                        Extension.sortedOpList = list.stream().skip(Integer.parseInt(from) - 1).limit(Integer.parseInt(to) - Integer.parseInt(from) + 1).collect(Collectors.toList());
                        Extension.sortedOpList
                                .forEach(w -> {
                                    System.out.println(w.getOperatorContent().toString());
                                });
                        break;
                    } else {
                        System.out.println("Please, enter correct range");
                    }
                } else {
                    System.out.println("Please, enter only digit");
                }
            }
        }
    }

    public static void listOnlySelectedField(List<Crash> list) throws IOException {
        System.out.println("Please, select field what you want to see (1,3,6,7)");
        Crash crash = new Crash();
        Class crashClass = crash.getClass();

        Field[] fields = crashClass.getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            System.out.println(i + ". " + fields[i].getName());
        }
        while (true) {
            System.out.println("Select column: (ex: 1,3,6,7): ");
            String selected = reader.readLine();
            if (Extension.checkSelectedColumn(selected)) {
                String[] selectedColumn = selected.split(",");
                Crash.crashList.forEach(w -> {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < selectedColumn.length; i++) {
                        sb.append(w.id);
                        if (Integer.parseInt(selectedColumn[i]) == 1) sb.append(", Date: " + w.getDate());
                        else if (Integer.parseInt(selectedColumn[i]) == 2) sb.append(", Time: " + w.getTime());
                        else if (Integer.parseInt(selectedColumn[i]) == 3)
                            sb.append(", Location: " + w.getLocation().getLocationContent());
                        else if (Integer.parseInt(selectedColumn[i]) == 4)
                            sb.append(", Operation: " + w.getOperator().getOperatorContent());
                        else if (Integer.parseInt(selectedColumn[i]) == 5) sb.append(", Flight: " + w.getFlight());
                        else if (Integer.parseInt(selectedColumn[i]) == 6) sb.append(", Route: " + w.getRoute());
                        else if (Integer.parseInt(selectedColumn[i]) == 7) sb.append(", Type: " + w.getType());
                        else if (Integer.parseInt(selectedColumn[i]) == 8)
                            sb.append(", Registration: " + w.getRegistration());
                        else if (Integer.parseInt(selectedColumn[i]) == 9) sb.append(", cn.In: " + w.getCnIn());
                        else if (Integer.parseInt(selectedColumn[i]) == 10) sb.append(", Aboard: " + w.getAboard());
                        else if (Integer.parseInt(selectedColumn[i]) == 11)
                            sb.append(", Fatality: " + w.getFatalities());
                        else if (Integer.parseInt(selectedColumn[i]) == 12) sb.append(", Ground: " + w.getGround());
                        else if (Integer.parseInt(selectedColumn[i]) == 13)
                            sb.append(", Survivors: " + w.getSurvivors());
                        else if (Integer.parseInt(selectedColumn[i]) == 13)
                            sb.append(", SurvivalRate: " + w.getSurvivalRate());
                        else if (Integer.parseInt(selectedColumn[i]) == 14) sb.append(", Summary: " + w.getSummary());
                        else if (Integer.parseInt(selectedColumn[i]) == 15) sb.append(", ClustID: " + w.getClustID());
                    }
                    System.out.println(sb);
                });
                break;
            } else {
                System.out.println("Please, choose only digits");
            }
        }
    }

    public static void sortData(List<Crash> crashList, List<Location> locationList, int sortBy) throws IOException {
        if (crashList != null) {
            if (sortBy == 3) {
                Extension.sortedList = crashList.stream().sorted((f1, f2) -> CharSequence.compare(f2.getLocation().getLocationContent(), f1.getLocation().getLocationContent())).collect(Collectors.toList());
                Extension.sortedList.forEach(w -> {
                    System.out.println(w);
                });
            } else if (sortBy == 4) {
                Extension.sortedList = crashList.stream().sorted((f1, f2) -> CharSequence.compare(f1.getLocation().getLocationContent(), f2.getLocation().getLocationContent())).collect(Collectors.toList());
                Extension.sortedList.forEach(w -> {
                    System.out.println(w);
                });
            } else if (sortBy == 2) {
                Extension.sortedList = crashList.stream().sorted(Comparator.comparing(Crash::getDate)).collect(Collectors.toList());
                Extension.sortedList.forEach(w -> {
                    System.out.println(w);
                });
            } else if (sortBy == 1) {
                List<Crash> list = crashList.stream().sorted(Comparator.comparing(Crash::getDate)).collect(Collectors.toList());
                Collections.reverse(list);
                Extension.sortedList = list;
                Extension.sortedList
                        .forEach(w -> {
                            System.out.println(w);
                        });
            }
        } else if (locationList != null) {

        } else {
            System.out.println("Please, choose correct option..");
        }
    }

    public static boolean checkSelectedNumber(String content) {
        return Pattern.matches("\\d+", content);
    }

    public static boolean checkDate(String content) {
        return Pattern.matches("(01|02|03|04|05|06|07|08|09|10|11|12)/[0-9]{2}/[0-9]{4}", content);
    }

    public static boolean checkSelectedColumn(String content) {
        String[] arr = content.trim().split(",");
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Pattern.matches("\\d+", arr[i])) {
                size++;
            }
        }
        return size == arr.length;
    }

    public static void exportToExcel() {
        Extension.sortedList.forEach(w -> {
            System.out.println(w);
        });
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Plane Crashes");
        int rowCount = 1;
        excelHeader(sheet, workbook);
        for (Crash crash : Extension.sortedList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            Cell cell = null;
            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getDate().toString());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getTime());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getLocation().getLocationContent());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getOperator().getOperatorContent());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getFlight());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getRoute());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getType());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getRegistration());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getCnIn());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getAboard());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getFatalities());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getGround());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getSurvivors());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getSurvivalRate());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getSummary());

            cell = row.createCell(columnCount++);
            cell.setCellValue(crash.getClustID());
        }
        String userHomeFolder = System.getProperty("user.home");
        try (FileOutputStream outputStream =
                     new FileOutputStream(new File(userHomeFolder, "PLaneCrashes" + LocalDateTime.now().toString().replace(':', '.') + ".xlsx"))) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully Exported file to folder with path " + userHomeFolder);

    }

    public static void excelHeader(Sheet sheet, Workbook wb) {
        Row row = sheet.createRow(0);
        int columnCount = 0;
        Cell cell = null;
        Crash crash = new Crash();
        Field[] fields = crash.getClass().getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            cell = row.createCell(columnCount++);
            cell.setCellValue(fields[i].getName().toUpperCase());
        }
    }
}
