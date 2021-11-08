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
        String[] operation_command = new String[]{"Rectangle()","Line()","Circle()","Square()" , "Group()","Ungroup()",
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
            case 0 : case 1 : case 2 : case 3:
                x.add(operation_command[input]);
                break;
            case 13:
                System.out.println("Exiting Clevis");
                System.exit(0);
                break;

        }




    }

}
