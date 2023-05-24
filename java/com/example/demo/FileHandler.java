package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileHandler {
    private static String pathStr = "C:\\Users\\USER\\IdeaProjects\\servettt\\demo\\src\\main\\webapp\\myData.dat";
    private static Path path = Paths.get(pathStr);
    public static void writeCatToFile(Cat cat) throws IOException {
        String catString = "";
        catString+=cat.getName()+",";
        catString+=cat.getOrigin()+",";
        catString+=cat.getSize()+",";
        catString+=cat.getAge()+",";
        catString+=cat.getPounds()+"\n";
        if(!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.write(path, catString.getBytes(), StandardOpenOption.APPEND);
    }
    public static ArrayList<Cat> readCatsFromFile() throws IOException {
        if(Files.exists(path)) {
            BufferedReader reader = new BufferedReader(new FileReader(pathStr));
            String data = reader.readLine();
            ArrayList<Cat> cats = new ArrayList<>();
            while(data != null) {
                String[] parameters = data.split(",");
                Cat tempCat = new Cat(parameters[0],parameters[1],parameters[2],Float.parseFloat(parameters[3]),Float.parseFloat(parameters[4]));
                cats.add(tempCat);
                data = reader.readLine();
            }
            reader.close();
            return cats;
        }
        return null;
    }
}