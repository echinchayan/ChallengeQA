package helpers;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalReport {

    public static void main (String []args){
        String ruta=new File("").getAbsolutePath()+"\\build\\reports\\cucumber\\";
        File report= new File(ruta+"CHALLENGE");

        List<String> jsons= new ArrayList<>();
        jsons.add(ruta+"report.json");

        Configuration configuration= new Configuration(report,"CHALLENGE");
        configuration.setBuildNumber("v1.0");
        configuration.addClassifications("SERVICIO","Usuarios");
        configuration.addClassifications("PROPIETARIO","Erick Chinchayan");
        configuration.addClassifications("SISTEMA OPERATIVO","Windows");

        ReportBuilder reportBuilder= new ReportBuilder(jsons,configuration);
        reportBuilder.generateReports();
    }
}

