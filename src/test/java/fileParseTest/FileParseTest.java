package fileParseTest;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.v85.input.Input;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {

    ClassLoader cl = FileParseTest.class.getClassLoader();

    @Test
    void zipTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/sample-zip-file.zip"));
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("sample-zip-file.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                switch (entry.getName()) {
                    case "all about GIT.pdf":
                        try (InputStream is = zf.getInputStream(entry)) {
                            PDF pdf = new PDF(is);
                            assertThat(pdf.author).isEqualTo("Scott Chacon, Ben Straub");
                        }
                        break;

                    case "file_example_XLSX.xlsx":
                        try (InputStream is = zf.getInputStream(entry)) {
                            XLS xls = new XLS(is);
                            assertThat(
                                    xls.excel.getSheetAt(0)
                                            .getRow(3)
                                            .getCell(6)
                                            .getStringCellValue())
                                    .isEqualTo("21/05/2015");
                        }
                        break;

                    case "username.csv":
                        try (InputStream is = zf.getInputStream(entry)) {
                            CSVReader reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                            List<String[]> content = reader.readAll();
                            String[] row = content.get(1);
                            assertThat(row[0]).contains("Rachel");
                        }
                        break;

                }
            }
        }
    }

    @Test
    void jsonTest() throws Exception {
        File file = new File("src/test/resources/JsonExample.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Task task = objectMapper.readValue(file, Task.class);
        assertThat(task.firstName).isEqualTo("Alex");
        assertThat(task.lastName).isEqualTo("Spirid");
        assertThat(task.age).isEqualTo(55);
        assertThat(task.user).isFalse();
        assertThat(task.contents.get(0).taskName).isEqualTo("Task1");
    }
}