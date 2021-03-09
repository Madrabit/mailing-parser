package ru.madrabit.mailingparser;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface SeminarSearcher {

    List<String> readDocxFile(File file);

    Set<String> getFilesList(String dir);
}
