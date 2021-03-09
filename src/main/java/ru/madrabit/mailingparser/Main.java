package ru.madrabit.mailingparser;

import ru.madrabit.mailingparser.model.Item;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        String baseDir = System.getProperty("user.dir")
                + File.separator  + "mock-files";
        DirsCollector collector = new DirsCollector();
        SeminarSearcher seminarSearcher = new SeminarSearcherImpl();
        Set<String> dirsList = collector.getDirsList(baseDir);
        List<Item> items = new ArrayList<>();

        for (String dir : dirsList) {
            final String date = collector.parseDirToDate(dir);
            Set<String> subDirs = collector.getDirsList(baseDir + File.separator + dir);
            for (String subDir : subDirs) {
                final List<Integer> departments = collector.parseDepartmentFromDir(subDir);
                final Set<String> filesList = seminarSearcher
                        .getFilesList(baseDir + File.separator + dir + File.separator + subDir);
                for (String docFile : filesList) {

                    File file = new File(baseDir + File.separator + dir
                            + File.separator + subDir + File.separator + docFile);
                    final List<String> names = seminarSearcher.readDocxFile(file);
                    for (String name : names) {
                        items.add(new Item(name, date, departments));
                    }

                }
            }
        }

        for (Item item : items) {
            System.out.println(item.getName() + " : " + item.getDate() + " : " + item.getDepartments());
        }
    }
}
