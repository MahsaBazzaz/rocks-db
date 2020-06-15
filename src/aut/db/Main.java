package aut.db;

import aut.db.DataBase;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String seperateValue(String input) {
        Pattern p = Pattern.compile("[-+]?([0-9]*\\.[0-9]+|[0-9]+)$");
        Matcher m = p.matcher(input);
        while (m.find()) {
            return m.group();
        }
        return null;
    }

    public static void main(final String[] args) {
        DataBase myDB = new DataBase();
        Scanner sc = new Scanner(System.in);
        String input, instruction, key, value;
        while (true) {
            input = sc.nextLine();
            instruction = input.substring(0, 6);
            value = seperateValue(input);
            if (value != null)
                input = input.replaceAll(value, "");
            input = input.replaceAll(instruction, "");
            input = input.replaceAll("^\\s", "");
            input = input.replaceAll("\\s$", "");
            key = input;
            instruction = instruction.replaceAll("\\s$", "");
            switch (instruction) {
                case "create":
                    if (value != null && key.length() != 0) {
                        myDB.create(key, value);
                        System.out.println();
                    }
                    break;
                case "fetch":
                    if (key.length() != 0) {
                        myDB.fetch(key);
                        System.out.println();
                    }
                    break;
                case "update":
                    if (value != null && key.length() != 0) {
                        myDB.update(key, value);
                        System.out.println();
                    }
                    break;
                case "delete":
                    if (key.length() != 0) {
                        myDB.delete(key);
                        System.out.println();
                    }
                    break;
                default:
                    System.out.println(false);
                    System.out.println();
            }
        }
    }


}