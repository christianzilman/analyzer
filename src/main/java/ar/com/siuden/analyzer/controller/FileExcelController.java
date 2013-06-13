/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author juliozilman
 */
public class FileExcelController {

    public static void prueba() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("workbook.xls");
            // create a new workbook
            Workbook wb = new HSSFWorkbook();
            // create a new sheet
            Sheet s = wb.createSheet();
            // declare a row object reference
            Row r = null;
            // declare a cell object reference
            Cell c = null;
            // create 3 cell styles
            CellStyle cs = wb.createCellStyle();
            CellStyle cs2 = wb.createCellStyle();
            CellStyle cs3 = wb.createCellStyle();
            DataFormat df = wb.createDataFormat();
            // create 2 fonts objects
            Font f = wb.createFont();
            Font f2 = wb.createFont();
            //set font 1 to 12 point type
            f.setFontHeightInPoints((short) 12);
            //make it blue
            f.setColor((short) 0xc);
            // make it bold
            //arial is the default font
            f.setBoldweight(Font.BOLDWEIGHT_BOLD);
            //set font 2 to 10 point type
            f2.setFontHeightInPoints((short) 10);
            //make it red
            f2.setColor((short) Font.COLOR_RED);
            //make it bold
            f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            f2.setStrikeout(true);
            //set cell stlye
            cs.setFont(f);
            //set the cell format
            cs.setDataFormat(df.getFormat("#,##0.0"));
            //set a thin border
            cs2.setBorderBottom(cs2.BORDER_THIN);
            //fill w fg fill color
            cs2.setFillPattern((short) CellStyle.SOLID_FOREGROUND);
            //set the cell format to text see DataFormat for a full list
            cs2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
            // set the font
            cs2.setFont(f2);
            // set the sheet name in Unicode
            wb.setSheetName(0, "\u0422\u0435\u0441\u0442\u043E\u0432\u0430\u044F "
                    + "\u0421\u0442\u0440\u0430\u043D\u0438\u0447\u043A\u0430");
            // in case of plain ascii
            // wb.setSheetName(0, "HSSF Test");
            // create a sheet with 30 rows (0-29)
            int rownum;
            for (rownum = (short) 0; rownum < 30; rownum++) {
                // create a row
                r = s.createRow(rownum);
                // on every other row
                if ((rownum % 2) == 0) {
                    // make the row height bigger  (in twips - 1/20 of a point)
                    r.setHeight((short) 0x249);
                }

                //r.setRowNum(( short ) rownum);
                // create 10 cells (0-9) (the += 2 becomes apparent later
                for (short cellnum = (short) 0; cellnum < 10; cellnum += 2) {
                    // create a numeric cell
                    c = r.createCell(cellnum);
                    // do some goofy math to demonstrate decimals
                    c.setCellValue(rownum * 10000 + cellnum
                            + (((double) rownum / 1000)
                            + ((double) cellnum / 10000)));

                    String cellValue;

                    // create a string cell (see why += 2 in the
                    c = r.createCell((short) (cellnum + 1));

                    // on every other row
                    if ((rownum % 2) == 0) {
                        // set this cell to the first cell style we defined
                        c.setCellStyle(cs);
                        // set the cell's string value to "Test"
                        c.setCellValue("Test");
                    } else {
                        c.setCellStyle(cs2);
                        // set the cell's string value to "\u0422\u0435\u0441\u0442"
                        c.setCellValue("\u0422\u0435\u0441\u0442");
                    }


                    // make this column a bit wider
                    s.setColumnWidth((short) (cellnum + 1), (short) ((50 * 8) / ((double) 1 / 20)));
                }
            }
            //draw a thick black border on the row at the bottom using BLANKS
            // advance 2 rows
            rownum++;
            rownum++;
            r = s.createRow(rownum);
            // define the third style to be the default
            // except with a thick black border at the bottom
            cs3.setBorderBottom(cs3.BORDER_THICK);
            //create 50 cells
            for (short cellnum = (short) 0; cellnum < 50; cellnum++) {
                //create a blank type cell (no value)
                c = r.createCell(cellnum);
                // set it to the thick black border style
                c.setCellStyle(cs3);
            }

            //end draw thick black border
            // demonstrate adding/naming and deleting a sheet
            // create a sheet, set its title then delete it
            s = wb.createSheet();
            wb.setSheetName(1, "DeletedSheet");
            wb.removeSheetAt(1);
            //end deleted sheet
            // write the workbook to the output stream
            // close our file (don't blow out our file handles
            wb.write(out);
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(FileExcelController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(FileExcelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void prueba2() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sample sheet");

 
        List<Object[]> keyset = new ArrayList<Object[]>();
        keyset.add(new Object[]{"1-juan-2013","4", "6", "5"});
       // keyset.add(new Object[]{1d, "John", 1500000d});
       // keyset.add(new Object[]{2d, "Sam", 800000d});
        
        int rownum = 0;
        
        
        Row row2 = sheet.createRow(rownum++);
        
        Cell cellFecha = row2.createCell(0);
        cellFecha.setCellValue("Fecha");              
        
        Cell cellGaceta = row2.createCell(1);
        cellGaceta.setCellValue("La Gaceta");              
                
        
        Row row3 = sheet.createRow(rownum++);
        
        Cell cellF = row3.createCell(0);
        cellF.setCellValue("");              
        
        Cell cellRobo = row3.createCell(1);
        cellRobo.setCellValue("Robo");
        
        Cell cellHurto = row3.createCell(2);
        cellHurto.setCellValue("Hurto");
        
        Cell cellArrebato = row3.createCell(3);
        cellArrebato.setCellValue("Arrebato");
        
        
        
        for (Object key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr =(Object[]) key;
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
            
        }

        try {
            FileOutputStream out =
                    new FileOutputStream(new File("new.xls"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileExcelController.prueba2();
    }
}
