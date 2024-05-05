package org.application.Models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Report implements CSVConvertable {
    String text;

    public Report(Project project) {
        StringBuilder reportText = new StringBuilder();
        reportText.append("Project Name: ").append(project.getName()).append("\n");
        reportText.append("Project ID: ").append(project.getProjectID()).append("\n");
        reportText.append("Start Week: ").append(project.getStartWeek().getTime()).append("\n");
        reportText.append("End Week: ").append(project.getEndWeek().getTime()).append("\n");
        reportText.append("Project Leader ID: ").append(project.getProjectLeaderID()).append("\n");

        double overallProgress = project.getOverallProgress();
        reportText.append("Overall Progress: ").append(String.format("%.2f", overallProgress)).append("\n");

        reportText.append("Activities: \n");
        for (ProjectActivity activity : project.getActivities()) {
            reportText.append(activity.toString()).append("\n"); // assuming toString method in ProjectActivity class provides relevant details
            reportText.append("Activity Progress: ").append(String.format("%.2f", activity.getProgress())).append("\n");
        }

        text = reportText.toString();
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


