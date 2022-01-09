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
public class Operator extends BaseEntity {

    public static List<Operator> operatorList = new ArrayList<>();

    private String operatorContent;

    static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public Operator(String operatorContent) {
        this.operatorContent = operatorContent;
    }

    public static void listOperators() {
        int count = 1;
        System.out.println("All operators..");
        for (Operator l : operatorList) {
            System.out.printf(count + ". " + l.operatorContent + "...\n");
            count++;
        }
    }

    public static void operationMenuContent() throws IOException {

        while (true) {
            System.out.printf("Please select one of these options...\n");
            System.out.printf("1. Get operators based on range... (ex:5 100)\n");
            System.out.printf("2. Get operators ascending order...\n");
            System.out.printf("3. Get operators descending order... \n");
            System.out.printf("4. Search with operators name... \n");
            System.out.printf("5. Back...\n");
            String selectedOp = reader.readLine();
            int sel = Integer.parseInt(selectedOp);
            switch (sel) {
                case 1:
                    Extension.getOperatorsDataBasedOnRange(Operator.operatorList);
                    break;
                case 2:
                    getOperatorAscendingOrder();
                    break;
                case 3:
                    getOperatorDescendingOrder();
                    break;
                case 4:
                    searchWithOperatorName();
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }

    public static void getOperatorDescendingOrder() throws IOException {
        Extension.sortedOpList = operatorList.stream().sorted(Comparator.comparing(Operator::getOperatorContent)).collect(Collectors.toList());
        Extension.sortedOpList.forEach(w -> {
            System.out.println(w.getOperatorContent());
        });
        Extension.getOperatorsDataBasedOnRange(Extension.sortedOpList);
    }

    public static void getOperatorAscendingOrder() throws IOException {
        List<Operator> list = operatorList.stream().sorted(Comparator.comparing(Operator::getOperatorContent)).collect(Collectors.toList());
        Collections.reverse(list);
        Extension.sortedOpList = list;
        Extension.sortedOpList
                .forEach(w -> {
                    System.out.println(w.getOperatorContent());
                });
        Extension.getOperatorsDataBasedOnRange(Extension.sortedOpList);
    }

    public static void searchWithOperatorName() throws IOException {
        System.out.println("Please, write: ");
        String name = reader.readLine();
        Extension.sortedOpList = operatorList.stream().filter(w -> w.getOperatorContent().toLowerCase().trim().startsWith(name.toLowerCase().trim()) ||
                w.getOperatorContent().toLowerCase().trim().endsWith(name.toLowerCase().trim()) ||
                w.getOperatorContent().toLowerCase().trim().contains(name.toLowerCase().trim())).collect(Collectors.toList());
        Extension.sortedOpList.forEach(w -> {
            System.out.println(w.getOperatorContent());
        });
        Extension.getOperatorsDataBasedOnRange(Extension.sortedOpList);
    }
}
