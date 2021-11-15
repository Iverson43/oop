package hk.edu.polyu.comp.comp2021.clevis;


import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    public static int printmenu(String[] x){
        System.out.println("Menu:");
        for (int i = 0; i < x.length ; i++){
            System.out.println(i + " : " + x[i] );
        }
        System.out.println("-------------------\n");
        Scanner inputobj = new Scanner(System.in);
        System.out.println("Input your command: ");
        int input = inputobj.nextInt();
        return input;
    }
    public static void main(String[] args)  throws IOException {
        // Clevis clevis = new Clevis();
        // Initialize and utilize the system
        System.out.println("======================");
        System.out.println("Welcome To Clevis");
        System.out.println("======================" );
        String[] operation_command = new String[]{"Rectangle()","Line()","Circle()","Square()" , "Group()","Ungroup()",
        "Delete()","Boundingbox()","Move()","Pick_and_move()","Intersect()","List()","Listall()","Quit()"};
        Clevis x = new Clevis();

        boolean leave = false;
        while (leave == false){
            int Input = printmenu(operation_command);
            switch(Input)
            {
                case 0 : case 1 : case 2 : case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
                case 11 :case 12 :
                    x.add(operation_command[Input]);
                    x.write(operation_command[Input]);
                    break;
                case 13:
                    System.out.println("Exiting Clevis");
                    leave = true;
                    System.exit(0);
                    x.write(operation_command[Input]);
                    break;
            }
            System.out.println("Continue?:\ntype '0' to continue\n'1' to quit");
            Scanner leave1 = new Scanner(System.in);
            int j = leave1.nextInt();
            if (j == 0) continue;
            else System.exit(0);
        }

    }

}
