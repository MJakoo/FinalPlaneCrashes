package utils;

import entities.Crash;
import entities.Location;
import entities.Operator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Converter {
    public static void exportDataFromExcelToList() throws IOException {
        try {
            FileInputStream file = new FileInputStream(new File("src/main/resources/resources/Large_Passenger_Plane_Crashes_1933_to_2009.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int rowCount = 0;
            while (rowIterator.hasNext()) {
                Crash newCrash = new Crash();
                Row row = rowIterator.next();
                if (rowCount > 0) {
                    newCrash.id = rowCount;
                    if (row.getCell(0) == null) newCrash.setDate(null);
                    else newCrash.setDate(Extension.parseDate(row.getCell(0).toString()));
                    if (row.getCell(1) == null) newCrash.setTime(null);
                    else newCrash.setTime(row.getCell(1).toString());

                    if (row.getCell(2) == null) newCrash.setLocation(null);
                    else {
                        Location newLoc = new Location(row.getCell(2).toString());
                        newCrash.setLocation(newLoc);
                        Location.locationList.add(newLoc);
                    }
                    if (row.getCell(3) == null) newCrash.setOperator(null);
                    else {
                        Operator newO = new Operator(row.getCell(3).toString());
                        newCrash.setOperator(newO);
                        Operator.operatorList.add(newO);
                    }

                    if (row.getCell(4) == null) newCrash.setFlight(null);
                    else newCrash.setFlight(row.getCell(4).toString());
                    if (row.getCell(5) == null) newCrash.setRoute(null);
                    else newCrash.setRoute(row.getCell(5).toString());
                    if (row.getCell(6) == null) newCrash.setType(null);
                    else newCrash.setType(row.getCell(6).toString());
                    if (row.getCell(7) == null) newCrash.setRegistration(null);
                    else newCrash.setRegistration(row.getCell(7).toString());
                    if (row.getCell(8) == null) newCrash.setCnIn(null);
                    else newCrash.setCnIn(row.getCell(8).toString());
                    if (row.getCell(9) == null) newCrash.setAboard(0);
                    else newCrash.setAboard((int) Double.parseDouble(row.getCell(9).toString()));
                    if (row.getCell(10) == null) newCrash.setFatalities(0);
                    else newCrash.setFatalities((int) Double.parseDouble(row.getCell(10).toString()));
                    if (row.getCell(11) == null) newCrash.setGround(0);
                    else newCrash.setGround((int) Double.parseDouble(row.getCell(11).toString()));
                    if (row.getCell(12) == null) newCrash.setSurvivors(0);
                    else newCrash.setSurvivors((int) Double.parseDouble(row.getCell(12).toString()));
                    if (row.getCell(13) == null) newCrash.setSurvivalRate(null);
                    else newCrash.setSurvivalRate(Double.parseDouble(row.getCell(13).toString()));
                    if (row.getCell(14) == null) newCrash.setSummary(null);
                    else newCrash.setSummary(row.getCell(14).toString());
                    if (row.getCell(15) == null) newCrash.setClustID(null);
                    else newCrash.setClustID(row.getCell(15).toString());
                    Crash.crashList.add(newCrash);
                }
                rowCount++;
            }
            file.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }
}
