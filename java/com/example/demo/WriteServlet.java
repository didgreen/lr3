package com.example.demo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        sc.getRequestDispatcher("/jsp/write.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathStr = "C:\\Users\\USER\\IdeaProjects\\servettt\\demo\\src\\main\\webapp\\myData.dat";
        Path path = Paths.get(pathStr);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Cat> CatList = new ArrayList<>();
        String jsonArrayString="";
        if(!Files.exists(path)) {
            Files.createFile(path);
        }
        jsonArrayString = Files.readString(path);
        if(jsonArrayString!="") {
            Type listOfCat = new TypeToken<ArrayList<Cat>>() {}.getType();
            CatList = gson.fromJson(jsonArrayString, listOfCat);
        }
        String data = request.getReader().readLine();
        Cat cat = gson.fromJson(data, Cat.class);
        CatList.add(cat);
        FileHandler.writeCatToFile(cat);
    }

}