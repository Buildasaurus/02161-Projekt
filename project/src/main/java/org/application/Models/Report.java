package org.application.Models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Report implements CSVConvertable {
    String text;

    public Report(String text) {
        this.text = text;
    }

    private File toCSV() {
        File csvFile = new File("report.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(this.text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return csvFile;
    }

    @Override
    public void saveToDisk(String path) {
        File csvFile = this.toCSV();
        File newFile = new File(path + "/" + csvFile.getName());
        if (csvFile.renameTo(newFile)) {
            System.out.println("Report saved to " + newFile.getAbsolutePath());
        }
        else {
            System.out.println("Failed to save report to " + path);
        }
    }
}


