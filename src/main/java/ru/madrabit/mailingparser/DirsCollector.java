package ru.madrabit.mailingparser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public class DirsCollector {
    public Set<String> getDirsList(String baseDir) {
        final File[] files = new File(baseDir).listFiles();
        if (files != null) {
            return Stream.of(files)
                    .filter(file -> file.isDirectory())
                    .map(File::getName)
                    .collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }


    }

    public String parseDirToDate(String dir) {
        final String[] split = dir.split("-");
        StringBuilder builder = new StringBuilder();
        builder.append(split[2]);
        builder.append(".");
        builder.append(split[1]);
        builder.append(".");
        builder.append(split[0]);
        return builder.toString();
    }

    public List<Integer> parseDepartmentFromDir(String dir) {
        List<Integer> list = new ArrayList<>();
        String[] split = dir.split(", | ,|,| ");
        int i = 0;
        while (i < split.length && StringUtils.isNumeric(split[i])) {
            list.add(Integer.parseInt(split[i]));
            i++;
        }
        return list;
    }

}
