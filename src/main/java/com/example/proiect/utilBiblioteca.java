package com.example.proiect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class utilBiblioteca extends Main {

    public static int nextID(){
        int x;

        try{
            File obj = new File("nextID.txt");
            Scanner myReader = new Scanner(obj);
            x= myReader.nextInt();
            myReader.close();
            x++;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try{
            FileWriter myWriter =  new FileWriter("nextID.txt");
            myWriter.write(String.valueOf(x));
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return x;
    }



}
