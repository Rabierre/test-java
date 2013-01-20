package me.rabierre.paradigm;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 20.
 * Time: 오후 11:50
 * To change this template use File | Settings | File Templates.
 */
public class Multiplication {
    private int[] elements;

    public Multiplication() {
        elements = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    public void printAll() {
        for (int element : elements) {
            for (int element2 : elements) {
                System.out.print(element * element2 + "\t");
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        new Multiplication().printAll();
    }
}
