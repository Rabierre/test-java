package me.rabierre.paradigm.bettercalendar;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 24.
 * Time: 오전 1:58
 * To change this template use File | Settings | File Templates.
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
