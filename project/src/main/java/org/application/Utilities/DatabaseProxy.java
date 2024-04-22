package org.application.Utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.SystemModel;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class DatabaseProxy {
    private static String path = "database.json";

    public static SystemSchema readData() throws FileNotFoundException {
        Gson gson = new Gson();
        
        JsonReader reader = new JsonReader(new FileReader(path));

        return gson.fromJson(reader, SystemSchema.class);
    }
    
    public static void writeData() throws IOException {
        Gson gson = new Gson();

        List<Employee> employees = SystemModel.getEmployees();
        List<Project> projects = SystemModel.getProjects();
        SystemSchema schema = new SystemSchema(employees, projects);

        FileWriter writer = new FileWriter(path);

        gson.toJson(schema, writer);
        writer.flush();
        writer.close();
    }
}