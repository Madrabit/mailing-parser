package ru.madrabit.mailingparser;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

@Slf4j
class SeminarSearcherImplTest {
    String path = "";
    String name = "";
    @BeforeEach
    public void init() {
        path = System.getProperty("user.dir")
                + File.separator  + "mock-files" + File.separator  + "2019-11-19" + File.separator
                + "2 искл Кредит Европа,АК Барс, Росэксимбанк" + File.separator  + "RE_драгоценные_металлы_комментарии_.docx";
        name = "Организация работы с драгоценными металлами и определение подлинности слитков и монет";

    }

    @Test
    public void readDocFile() {
        final SeminarSearcher seminarSearcher = new SeminarSearcherImpl();
    }

}
