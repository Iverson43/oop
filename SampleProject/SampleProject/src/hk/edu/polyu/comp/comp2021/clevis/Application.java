package hk.edu.polyu.comp.comp2021.clevis;


import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {


    public static void main(String[] args) {
        // Clevis clevis = new Clevis();
        // Initialize and utilize the system
        System.out.println("======================");
        System.out.println("Welcome To Clevis");
        System.out.println("======================" );
        System.out.println("Menu:");
        int size = 13;
        int[] operation_index = new int[size];
        for (int i= 0 ; i < size ; i++){
            operation_index[i] = i;
        }
        String[] operation_command = new String[]{"Rectangle()","line()","Circle()","Group()","Ungroup()",
        "Delete()","Boundingbox()","Move()","Pick_and_move()","Intersect()","List()","Listall()","Quit()"};
        for (int i = 0; i < operation_command.length ; i++){
            System.out.println(i + " : " + operation_command[i] );
        }
        Clevis x = new Clevis();
        Scanner inputobj = new Scanner(System.in);
        System.out.println("Input your command: ");
        int input = inputobj.nextInt();
        switch(input)
        {
            case 12:
                System.out.println("Exiting Clevis");
                System.exit(0);
                break;

        }




    }

}
