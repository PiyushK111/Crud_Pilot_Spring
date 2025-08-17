package CrudPilot.Executor;

import CrudPilot.Controller.BulkUploadController;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@Service
public class BulkUploadExecutor {

    Logger logger = LoggerFactory.getLogger(BulkUploadExecutor.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean convertExcelToSql(MultipartFile excelDataFile) throws IOException {
        StringBuilder sqlQuery = new StringBuilder();

        try (Workbook workbook = WorkbookFactory.create(excelDataFile.getInputStream())) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                String tableName = sheet.getSheetName();

                sqlQuery.append("INSERT INTO ").append(tableName).append(" (");

                Row headerRow = sheet.getRow(0);
                Iterator<Cell> cellIterator = headerRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    sqlQuery.append(cell.getStringCellValue());

                    if (cellIterator.hasNext()) {
                        sqlQuery.append(", ");
                    }
                }

                sqlQuery.append(") VALUES ");

                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    sqlQuery.append("(");

                    Iterator<Cell> dataCellIterator = row.cellIterator();

                    while (dataCellIterator.hasNext()) {
                        Cell cell = dataCellIterator.next();
                        if (cell.getCellType() == CellType.STRING) {
                            sqlQuery.append("'").append(cell.getStringCellValue()).append("'");
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            sqlQuery.append(cell.getNumericCellValue());
                        }

                        if (dataCellIterator.hasNext()) {
                            sqlQuery.append(", ");
                        }
                    }

                    sqlQuery.append(")");

                    if (rowIndex < sheet.getLastRowNum()) {
                        sqlQuery.append(", ");
                    }
                }

                sqlQuery.append(";");
            }
            logger.info("Bulk Upload Query generated : {}", sqlQuery);
            jdbcTemplate.execute(sqlQuery.toString());
            return true;
        } catch (Exception e) {
            logger.error("Exception occurred in BulkUpload probable cause : ", e);
            return false;
        }
    }

}
