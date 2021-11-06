package hk.edu.polyu.comp.comp2021.clevis;


import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import javax.lang.model.element.Element;


import java.util.ArrayList;

public class Application {

    public static void main(String[] args){
        // Clevis clevis = new Clevis();
        // Initialize and utilize the system
        System.out.print("======================");
        System.out.print("Welcome To Clevis");
        System.out.print("======================");
        System.out.print("Menu:");
        ArrayList<String> x = new ArrayList<>();

        String filename = "operation.html";
        File file = new File(filename);
        Path path = Paths.get(filename);
        Scanner scanner = new Scanner(path);

    }

}
