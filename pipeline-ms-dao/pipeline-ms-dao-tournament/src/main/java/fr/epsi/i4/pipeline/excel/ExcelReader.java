/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.pipeline.excel;

import fr.epsi.i4.pipeline.model.bdd.tournoi.Tournoi;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author kbouzan
 */
public class ExcelReader {
    
    public static ImportTournoi readExcel(File file) throws IOException, InvalidFormatException{

        Workbook workbook = WorkbookFactory.create(file);
        
        Sheet sheet = workbook.getSheetAt(0);
        
        List<OrganisationImport> organisations = new ArrayList<OrganisationImport>();

        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        
        Tournoi tournoi = new Tournoi();
        
        Row tournoiRow = rowIterator.next();
        tournoi.nom = tournoiRow.getCell(0).getStringCellValue();
        tournoi.date = new Timestamp(tournoiRow.getCell(1).getDateCellValue().getTime());
        tournoi.idNiveau = tournoiRow.getCell(2).getStringCellValue();
        tournoi.idComplexe = new BigDecimal(tournoiRow.getCell(3).getNumericCellValue());
        
        rowIterator.next();
        while (rowIterator.hasNext()) {
            try{
                Row row = rowIterator.next();

                OrganisationImport orga = new OrganisationImport();
                orga.setJoueurs(new ArrayList<>());
                Utilisateur joueur1 = new Utilisateur();
                Utilisateur joueur2 = new Utilisateur();
                Utilisateur joueur3 = new Utilisateur();
                Utilisateur joueur4 = new Utilisateur();

                if(row.getCell(0) != null){
                    joueur1.setNom(row.getCell(0).getStringCellValue());
                }
                if(row.getCell(1) != null){
                    joueur1.setPrenom(row.getCell(1).getStringCellValue());
                }

                if(row.getCell(2) != null){
                    joueur2.setNom(row.getCell(2).getStringCellValue());
                }
                if(row.getCell(3) != null){
                    joueur2.setPrenom(row.getCell(3).getStringCellValue());
                }

                if(row.getCell(4) != null){
                    joueur3.setNom(row.getCell(4).getStringCellValue());
                }
                if(row.getCell(5) != null){
                    joueur3.setPrenom(row.getCell(5).getStringCellValue());
                }

                if(row.getCell(6) != null){
                    joueur4.setNom(row.getCell(6).getStringCellValue());
                }
                if(row.getCell(7) != null){
                    joueur4.setPrenom(row.getCell(7).getStringCellValue());
                }

                Utilisateur arbitre = new Utilisateur(row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue());

                orga.getJoueurs().add(joueur1);
                orga.getJoueurs().add(joueur2);
                orga.getJoueurs().add(joueur3);
                orga.getJoueurs().add(joueur4);
                orga.setArbitre(arbitre);
                orga.setCourt(row.getCell(10).getStringCellValue());
                orga.setDate(row.getCell(11).getDateCellValue());

                organisations.add(orga);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        workbook.close();
        ImportTournoi importTournoi = new ImportTournoi(tournoi, organisations);
        return importTournoi;
    }
}
