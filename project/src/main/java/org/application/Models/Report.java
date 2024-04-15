package org.application.Models;

import java.io.File;

public class Report implements PDFConvertable{
    String text;
    public Report(String text) {
        this.text = text;
    }

    public File toPDF()
    {
        //TODO: make this insert relevant data into the file.
        return new File(text+".pdf");
    }
}
