package ru.madrabit.mailingparser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class SeminarSearcherImpl implements SeminarSearcher {

    @Override
    public List<String> readDocxFile(File file) {
        List<String> names = new ArrayList<>();
        boolean result = false;
        try (FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFTable> tables = document.getTables();
            if (tables.size() > 1) {
                for (int i = 0; i < tables.size() - 1; i++) {
                    for (XWPFTableRow row : tables.get(i).getRows()) {
                        names.add(row.getCell(1).getText());
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
        return names;
    }

    @Override
    public Set<String> getFilesList(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .filter(file ->  "docx".equals(FilenameUtils.getExtension(file.getName())) ||
                        "doc".equals(FilenameUtils.getExtension(file.getName())))
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
