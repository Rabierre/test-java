package me.rabierre.paradigm.bettercalendar;

import java.util.Scanner;

/**
 * Calendar implemented with Factory Pattern.
 */
public class Calendar {
    public static void main(String[] args) {
        System.out.println("Input year pleas : ex) 2013");
        System.out.print(">> ");

        Scanner scanner = new Scanner(System.in);
        int year = Integer.parseInt(scanner.nextLine());

        new Year(year).print();
    }
}
