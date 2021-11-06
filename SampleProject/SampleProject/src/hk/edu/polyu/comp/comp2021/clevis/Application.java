package hk.edu.polyu.comp.comp2021.clevis;


import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.util.ArrayList;

public class Application {


    public static void main(String[] args) {
        // Clevis clevis = new Clevis();
        // Initialize and utilize the system
        System.out.print("======================"+"\n");
        System.out.print("Welcome To Clevis"+ "\n");
        System.out.print("======================" + "\n");
        System.out.print("Menu:\n");
        int size = 13;
        int[] operation_index = new int[size];
        for (int i= 0 ; i < size ; i++){
            operation_index[i] = i;
        }
        String[] operation_command = new String[]{"Rectangle()","line()","Circle()","Group()","Ungroup()",
        "Delete()","Boundingbox()","Move()","Pick_and_move()","Intersect()","List()","Listall()","Quit()"};






    }

}
