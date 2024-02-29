package HW1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RegExp {
    public static void check(String line, String str1, String str2, int s2Count) {
        // First Problem
        String reverse_str = "";
        for (int i = line.length(); i > 0; i--) {
            reverse_str += line.charAt(i - 1);
        }
        if (line.equals(reverse_str)) {
            System.out.print("Y,");
        } else {
            System.out.print("N,");
        }
        // Second Problem
        if (line.contains(str1)) {
            System.out.println("Y,");
        } else {
            System.out.println("N,");
        }
        // Third Problem

    }

    public static void main(String[] args) {
        String str1 = args[1];
        String str2 = args[2];
        int s2Count = Integer.parseInt(args[3]);
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // For your testing of input correctness
        System.out.println("The input file: " + args[0]);
        System.out.println("str1 = " + str1);
        System.out.println("str2 = " + str2);
        System.out.println("num of repeated requests of str2 = " + s2Count);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = reader.readLine()) != null) {
                // You main code should be invoked here
                line = line.toLowerCase();
                // System.out.println(line);
                check(line, str1, str2, s2Count);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}