package com.company.audit;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ServiceAudit {
    FileWriter fileWriter;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void appendCommand(String command) throws IOException {
        fileWriter.append(command);
        fileWriter.append(",");
        fileWriter.append(dateTimeFormatter.format(LocalDateTime.now()));
        fileWriter.append("\n");
        fileWriter.flush();
    }

    public ServiceAudit() {
        try{
            this.fileWriter = new FileWriter("data/audit.csv");
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
