package ru.madrabit.mailingparser;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

@Slf4j
public class FindSeminarImpl implements FindSeminar {

    @Override
    public boolean readDocxFile(File file, String name) {
        boolean result = false;
        try (FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFTable> tables = document.getTables();
            if (tables.size() > 1) {
                for (int i = 0; i < tables.size() - 1; i++) {
                    for (XWPFTableRow row : tables.get(i).getRows()) {
                        log.info(row.getCell(1).getText());
                        final String text = row.getCell(1).getText();
                        if (text.matches(name)) {
                            result = true;
                        }
                    }
                }
            }
//            else if (tables.size() <= 1) {
//             List<XWPFParagraph> paragraphs = document.getParagraphs();
//                for (XWPFParagraph para : paragraphs) {
//                    log.info(para.getText());
//                    if (para.getText().contains(name)) {
//                        System.out.println("The match as per the Document is True");
//                        result = true;
//                    }
//                }
//            }
        } catch (Exception e) {
            log.error("Exception {}", e.getMessage());
        }
        return result;
    }
}
