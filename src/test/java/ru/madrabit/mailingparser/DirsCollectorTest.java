package ru.madrabit.mailingparser;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

class DirsCollectorTest {

    private String baseDir = "";
    private DirsCollector collector;
    private String subDirs = "";
    @BeforeEach
    public void init () {
        baseDir = System.getProperty("user.dir")
                + File.separator  + "mock-files";
        subDirs = baseDir + File.separator + "2019-11-19";
        collector = new DirsCollector();
    }

    @Test
    public void collectDirs() {

        final Set<String> dirs = collector.getDirsList(subDirs);
        for (String dir : dirs) {
            System.out.println(collector.parseDepartmentFromDir(dir));
        }
    }

    @Test
    void parseDirToDate() {
        String dirName = "2019-11-28";
        Assert.assertEquals("28.11.2019",  collector.parseDirToDate(dirName));
    }
}
