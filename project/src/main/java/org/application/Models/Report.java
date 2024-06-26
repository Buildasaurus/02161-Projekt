// 50/50 Lucia Jonathan

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

        if (project.getStartWeek() != null) {
            reportText.append("Start Week: ").append(project.getStartWeek().getTime()).append("\n");
        } else {
            reportText.append("Start date not chosen yet\n");
        }
        if (project.getEndWeek() != null) {
            reportText.append("End Week: ").append(project.getEndWeek().getTime()).append("\n");
        } else {
            reportText.append("End date not chosen yet\n");
        }
        reportText.append("Project Leader ID: ").append(project.getProjectLeaderID()).append("\n");

        if (!project.getActivities().isEmpty()){
            reportText.append("Activities: \n");
            for (ProjectActivity activity : project.getActivities()) {
                reportText.append(activity.toString()).append("\n"); // assuming toString method in ProjectActivity class provides relevant details
                reportText.append("Activity Progress: ").append(String.format("%.2f", activity.getProgress())).append("\n");
            }
        }
        else{
            reportText.append("No Activities").append("\n");
        }

        double overallProgress = project.getOverallProgress();
        reportText.append("Overall Progress: ").append(String.format("%.2f", overallProgress));

        text = reportText.toString();
    }


    public void saveToDisk() throws IOException
    {
        File csvFile = new File("report.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(this.text);
        }
        catch (IOException e) {
            throw new IOException("Folder already exists at this name: report.csv");
        }
    }
}


