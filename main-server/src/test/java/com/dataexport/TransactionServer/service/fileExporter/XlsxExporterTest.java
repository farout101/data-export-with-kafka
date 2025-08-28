package com.dataexport.TransactionServer.service.fileExporter;

import com.dataexport.TransactionServer.model.AtmTransaction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class XlsxExporterTest {

    @InjectMocks
    private XlsxExporter xlsxExporter;

    private final String exportDir = "exported-files";
    private final String subDir = "xlsx-files";

    @AfterEach
    void tearDown() throws IOException {
        Path directory = Paths.get(exportDir);
        if (Files.exists(directory)) {
            Files.walk(directory)
                 .sorted(Comparator.reverseOrder())
                 .map(Path::toFile)
                 .forEach(File::delete);
        }
    }

    @Test
    void export_shouldCreateXlsxFileWithCorrectData() throws IOException {
        // Arrange
        Integer exportId = 123;
        List<AtmTransaction> data = new ArrayList<>();
        data.add(new AtmTransaction(1L, "ATM001", "hash1", 100.0, LocalDateTime.now(), "Location 1", 500.0, "Completed"));
        data.add(new AtmTransaction(2L, "ATM002", "hash2", 200.0, LocalDateTime.now(), "Location 2", 600.0, "Pending"));

        // Act
        xlsxExporter.export(exportId, data);

        // Assert
        File file = new File(exportDir + "/" + subDir + "/export_" + exportId + ".xlsx");
        assertTrue(file.exists(), "Exported file should exist");

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            assertNotNull(sheet);

            // Check header row
            Row headerRow = sheet.getRow(0);
            assertEquals("atmTxId", headerRow.getCell(0).getStringCellValue());
            assertEquals("atmId", headerRow.getCell(1).getStringCellValue());
            assertEquals("cardNumberHash", headerRow.getCell(2).getStringCellValue());
            assertEquals("withdrawalAmount", headerRow.getCell(3).getStringCellValue());
            assertEquals("transactionTime", headerRow.getCell(4).getStringCellValue());
            assertEquals("atmLocation", headerRow.getCell(5).getStringCellValue());
            assertEquals("balanceRemaining", headerRow.getCell(6).getStringCellValue());
            assertEquals("transactionStatus", headerRow.getCell(7).getStringCellValue());


            // Check data row 1
            Row dataRow1 = sheet.getRow(1);
            assertEquals(1.0, dataRow1.getCell(0).getNumericCellValue());
            assertEquals("ATM001", dataRow1.getCell(1).getStringCellValue());
            assertEquals("hash1", dataRow1.getCell(2).getStringCellValue());
            assertEquals(100.0, dataRow1.getCell(3).getNumericCellValue());
            assertNotNull(dataRow1.getCell(4).getStringCellValue()); // Just check it's not null
            assertEquals("Location 1", dataRow1.getCell(5).getStringCellValue());
            assertEquals(500.0, dataRow1.getCell(6).getNumericCellValue());
            assertEquals("Completed", dataRow1.getCell(7).getStringCellValue());

            // Check data row 2
            Row dataRow2 = sheet.getRow(2);
            assertEquals(2.0, dataRow2.getCell(0).getNumericCellValue());
            assertEquals("ATM002", dataRow2.getCell(1).getStringCellValue());
            assertEquals("hash2", dataRow2.getCell(2).getStringCellValue());
            assertEquals(200.0, dataRow2.getCell(3).getNumericCellValue());
            assertNotNull(dataRow2.getCell(4).getStringCellValue()); // Just check it's not null
            assertEquals("Location 2", dataRow2.getCell(5).getStringCellValue());
            assertEquals(600.0, dataRow2.getCell(6).getNumericCellValue());
            assertEquals("Pending", dataRow2.getCell(7).getStringCellValue());
        }
    }

    @Test
    void export_shouldNotCreateFileForNullData() {
        // Arrange
        Integer exportId = 456;

        // Act
        xlsxExporter.export(exportId, null);

        // Assert
        File file = new File(exportDir + "/" + subDir + "/export_" + exportId + ".xlsx");
        assertFalse(file.exists());
    }

    @Test
    void export_shouldNotCreateFileForEmptyData() {
        // Arrange
        Integer exportId = 789;
        List<Object> data = new ArrayList<>();

        // Act
        xlsxExporter.export(exportId, data);

        // Assert
        File file = new File(exportDir + "/" + subDir + "/export_" + exportId + ".xlsx");
        assertFalse(file.exists());
    }
}