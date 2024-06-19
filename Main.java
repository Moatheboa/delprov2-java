import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.print("How many trees do you have? ");
        int amount = input.nextInt();
        input.nextLine();
        String[] paths = new String[amount];

        for (int i = 0; i < amount; i++) {
            System.out.print("Path to tree " + i + ": ");
            paths[i] = input.nextLine();
        }
        input.close();

        for (int i = 0; i < amount; i++) {
            var sensorValues = readFile(paths[i]);
            printTree(sensorValues, i);
        }
    }

    public static int[] readFile(String path) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File(path));
        int[] sensorValues = new int[8];
        for (int i = 0; i < 8; i++) {
            sensorValues[i] = fileReader.nextInt();
        }
        fileReader.close();
        return sensorValues;
    }

    public static int calculateTotal(int[] sensorValues) {
        int total = 0;
        for (int i = 0; i < sensorValues.length; i++) {
            total += sensorValues[i];
        }
        return total;
        }

    public static boolean hasWarning(int[] sensorValues) {
        int total = calculateTotal(sensorValues);
        return total < 75 || total > 85;
    }

    public static void printTree(int[] sensorValues, int treeID) {
        System.out.print("\nTree " + treeID + ":");
        for (int d : sensorValues)
            System.out.format("%4d", d);
        System.out.print(" --> " + calculateTotal(sensorValues));

        if (hasWarning(sensorValues)) {
            System.out.print("  (WARNING)");
        }
    }
}