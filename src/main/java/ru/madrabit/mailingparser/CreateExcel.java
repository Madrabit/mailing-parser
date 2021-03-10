package ru.madrabit.mailingparser;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.madrabit.mailingparser.model.Item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CreateExcel {

    private final String filePath;


    public CreateExcel() {
        log.info(System.getProperty("user.dir"));
        filePath = System.getProperty("user.dir")
                + File.separator + "отчет по рассылке.xlsx";

    }

    public void createExcel(List<Item> itemsList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("test");

        int rowNumber = 0;
        Cell cell;
        Row row;
        //
        XSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rowNumber);

        // Id
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Дата");
        cell.setCellStyle(style);
        // Title
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Название");
        cell.setCellStyle(style);
        // Type
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Номера отделов");
        cell.setCellStyle(style);

        // Data
        for (Item i : itemsList) {
            row = sheet.createRow(++rowNumber);

            // Id (A)
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(i.getDate());
            // Title (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(i.getName());
            // Type (C)
            cell = row.createCell(2, CellType.STRING);
            StringBuilder deps = new StringBuilder();
            int j = 0;
            int depsSize = i.getDepartments().size();
            for (Integer dep : i.getDepartments()) {
                if (++j == depsSize) {
                    deps.append(dep);
                } else {
                    deps.append(dep).append(",");
                }
            }
            cell.setCellValue(deps.toString());
        }
        createFile(workbook);
    }


    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private void createFile(XSSFWorkbook workbook) {
        File file = new File(filePath);

        try (FileOutputStream outFile = new FileOutputStream(file)) {
            workbook.write(outFile);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.info("Created file: {}", file.getAbsolutePath());
    }

}

