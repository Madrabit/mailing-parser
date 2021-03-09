package ru.madrabit.mailingparser;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

@Slf4j
public class FindSeminarImpl implements FindSeminar {

    @Override
    public boolean searchName(String fileName, String name) {
        boolean result = false;
        String[] words = null;
        File f1 = new File(fileName);
        StringBuffer buffer = new StringBuffer();
        String string = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new
                FileInputStream(f1)))) {
            while ((string = reader.readLine()) != null) {
                buffer.append(string);
            }
            System.out.println(buffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean readDocxFile(File file, String name) {
        boolean result = false;
        try {
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            XWPFDocument document = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            List<XWPFTable> tables = document.getTables();
            if (tables.size() > 1) {
                for (int i = 0; i < tables.size() - 1; i++) {
                    for (XWPFTableRow row : tables.get(i).getRows()) {
                        log.info(row.getCell(1).getText());
                        final String text = row.getCell(1).getText();
                        if (text.matches(name)) {
                            System.out.println("The match as per the Document is True");
                            result = true;
                        }

                        System.out.println(" ");
                    }
                }
            } else if (tables.size() <= 1) {
                for (XWPFParagraph para : paragraphs) {
                    log.info(para.getText());
                    if (para.getText().contains(name)) {
                        System.out.println("The match as per the Document is True");
                        result = true;
                    }
                }
            }
            fis.close();
        } catch (Exception e) {
           log.error("Exception {}", e.getMessage());
        }
        return result;
    }
}
