/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
  
import org.primefaces.event.FileUploadEvent;  
  
/**
 *
 * @author Aek
 */
public class FileUploadController {
    ArrayList<String> filenames = new ArrayList<String>();
    ArrayList<String> s_name = new ArrayList<String>();
    public void handleFileUpload(FileUploadEvent event) {  
        System.out.println("-=-=-=-=-=-=-==-=-=-=-=-=-=- start");
        try {
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
             String path = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/");
//        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
//        String name = fmt.format(new Date())
//                + event.getFile().getFileName().substring(
//                      event.getFile().getFileName().lastIndexOf('.'));
        System.out.println("-=-=-=-=-=-=-==-=-=-=-=-=-=-");
        filenames.add(path+event.getFile().getFileName());
        s_name.add(event.getFile().getFileName().toLowerCase());
        File file = new File(path  + event.getFile().getFileName());
        FileOutputStream  out = new FileOutputStream(file);
        out.write(event.getFile().getContents(), 0,event.getFile().getContents().length);
        out.close();
         System.out.println("-=-=-=-=-=-=-==-=-=-=-=-=-=- process ");
        } catch (IOException ex) {
            Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println("-=-=-=-=-=-=-==-=-=-=-=-=-=- start");
    } 
    public void upload(){
        int index = 0;
        String err = "";
  
             for(String filename :filenames){  
            try {
                Class.forName("org.postgresql.Driver");
                Connection con = null;
                try {
                    con = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/formula","postgres","postgres");
                } catch (SQLException ex) {
                    Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                }
               // con.setAutoCommit(false);
                PreparedStatement pstm = null ;
               
                FileInputStream input = null;
                     try {
                         input = new FileInputStream(filename);
                     } catch (FileNotFoundException ex) {
                         Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                POIFSFileSystem fs = null;
                     try {
                         fs = new POIFSFileSystem( input );
                     } catch (IOException ex) {
                         Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                HSSFWorkbook wb = null;
                     try {
                         wb = new HSSFWorkbook(fs);
                     } catch (IOException ex) {
                         Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                HSSFSheet sheet = wb.getSheetAt(0);
                Row row;
               
                   //String filename="";
                if(s_name.get(index).equalsIgnoreCase("dbscustomer.xls"))
                for(int i=1; i<=sheet.getLastRowNum(); i++){
                    row = sheet.getRow(i);
                    String code     = row.getCell(0).getStringCellValue();
                    String name     = row.getCell(1).getStringCellValue();
                    String group    = row.getCell(2).getStringCellValue();
                    String category = row.getCell(3).getStringCellValue();
                    String saleman  = row.getCell(4).getStringCellValue();
                    //INSERT INTO `cdcol`.`dbscustomer` (`code`, `name`, `Group`, `Category`, `Saleman`) VALUES ('1', '2', '3', '4', '5');
                    String sql = "INSERT INTO dbscustomer (code,name,groups,Category,Saleman) VALUES ('"+code+"', '"+name.replaceAll("'","\u2019")+"', '"+group+"', '"+category+"', '"+saleman+"');";
                     err=sql;
                    //                pstm = (PreparedStatement) con.prepareStatement(sql);
    //                pstm.execute();
                    Statement stm;
                    try {
                        stm = con.createStatement(); 
                        stm.execute(sql);
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Import rows cus "+err);
                    }
                    // err=sql;
                    System.out.println("Import rows cus "+i);
                }
                if(s_name.get(index).equalsIgnoreCase("dbsbudget.xls"))
                 for(int i=1; i<=sheet.getLastRowNum(); i++){
                    row = sheet.getRow(i);
                    String f1     = row.getCell(0).getStringCellValue();
                    String f2     = row.getCell(1).getStringCellValue().replaceAll("'", "\'");
                    String f3     = row.getCell(2).getStringCellValue();
                    String f4     = String.valueOf(row.getCell(3).getNumericCellValue());
                    String f5     = row.getCell(4).getStringCellValue();
                    String f6     = row.getCell(5).getStringCellValue();
                    String f7     = String.valueOf(row.getCell(6).getNumericCellValue());
                    String f8	  = String.valueOf(row.getCell(7).getNumericCellValue());
                    String f9	  = String.valueOf(row.getCell(8).getNumericCellValue());
                    String f10	  = String.valueOf(row.getCell(9).getNumericCellValue());
                    String f11	  = String.valueOf(row.getCell(10).getNumericCellValue());
                    String f12	  = String.valueOf(row.getCell(11).getNumericCellValue());
                    String f13	  = String.valueOf(row.getCell(12).getNumericCellValue());
                    String f14	  = String.valueOf(row.getCell(13).getNumericCellValue());
                    String f15	  = String.valueOf(row.getCell(14).getNumericCellValue());
                    String f16	  = String.valueOf(row.getCell(15).getNumericCellValue());
                    String f17	  = String.valueOf(row.getCell(16).getNumericCellValue());
                    String f18	  = String.valueOf(row.getCell(17).getNumericCellValue());
                    String f19	  = String.valueOf(row.getCell(18).getNumericCellValue());
                    String f20	  = String.valueOf(row.getCell(19).getNumericCellValue());
                  //  String f21	  = String.valueOf(row.getCell(20).getNumericCellValue());
                  // INSERT INTO `cdcol`.`dbsproduct` (`Code`, `Product`, `Supplier`, `Type`, `product Group`) VALUES ('1', '2', '3', '4', '5');                   
                    String sql = " INSERT INTO dbsbudget(salesmanname, customergroup, unit,unitprice, supplier, productcode, year, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, \"dec\", total) VALUES ('"+f1+"','"+f2+"','"+f3+"',"+f4+",'"+f5+"','"+f6+"',"+f7+","+f8+","+f9+","+f10+","+f11+","+f12+","+f13+","+f14+","+f15+","+f16+","+f17+","+f18+","+f19+","+f20+");";
                    err=sql;
                    //                pstm = (PreparedStatement) con.prepareStatement(sql);
    //                pstm.execute();
                   Statement stm;
                    try {
                        stm = con.createStatement();
                   
                     stm.execute(sql);
                     stm.close();
                      } catch (SQLException ex) {
                        Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Import rows "+i);
                
                    

                }
                  if(s_name.get(index).equalsIgnoreCase("dbscustomer.xls"))
                for(int i=1; i<=sheet.getLastRowNum(); i++){
                    row = sheet.getRow(i);
                    String code     = row.getCell(0).getStringCellValue();
                    String name     = row.getCell(1).getStringCellValue();
                    String group    = row.getCell(2).getStringCellValue();
                    String category = row.getCell(3).getStringCellValue();
                    String saleman  = row.getCell(4).getStringCellValue();
                    //INSERT INTO `cdcol`.`dbscustomer` (`code`, `name`, `Group`, `Category`, `Saleman`) VALUES ('1', '2', '3', '4', '5');
                    String sql = "INSERT INTO dbscustomer (code,name,groups,Category,Saleman) VALUES ('"+code+"', '"+name.replaceAll("'","\u2019")+"', '"+group+"', '"+category+"', '"+saleman+"');";
                     err=sql;
                    //                pstm = (PreparedStatement) con.prepareStatement(sql);
    //                pstm.execute();
                    Statement stm;
                    try {
                        stm = con.createStatement(); 
                        stm.execute(sql);
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Import rows cus "+err);
                    }
                    // err=sql;
                    System.out.println("Import rows cus "+i);
                }
                try {
                    // con.commit();
                    // pstm.close();
                     con.close();
                      input.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                System.out.println("Success import excel to mysql table");
                index++;
                    FacesMessage msg = new FacesMessage("Import Succesful"," " +index+ " ");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            }
             filenames.clear();
             s_name.clear();
        
    }
}
