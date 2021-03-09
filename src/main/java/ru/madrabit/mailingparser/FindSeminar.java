package ru.madrabit.mailingparser;

import java.io.File;

public interface FindSeminar {

    boolean searchName(String fileName, String name);

    boolean readDocxFile(File file, String name);
}
