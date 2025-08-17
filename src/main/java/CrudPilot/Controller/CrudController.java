package CrudPilot.Controller;

import CrudPilot.Commons.TransactionLogger;
import CrudPilot.Constants.EndpointReferrerConstants;
import CrudPilot.Executor.CrudExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@RestController
public class CrudController {

    Logger logger = LoggerFactory.getLogger(CrudController.class);

    @Autowired
    CrudExecutor crudExecutor;

    @Autowired
    TransactionLogger txnLogger;

    @Value("${upload.file.path}")
    String filePath;

    
    
	@RequestMapping("/home") public String home() { 
		return "index"; // Return the name of the JSP file without the .jsp extension }
	}
    
    @PostMapping("/createTable")
    public Callable<ResponseEntity<Map<String, Object>>> createTable(@RequestBody Map<String, Object> request) {
        return () -> {
            logger.info("Create Table called. Request received : {}", request);
            if (crudExecutor.createTable(request)) {
                txnLogger.logTransaction(request);
                return new ResponseEntity<>(request, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put(HttpStatus.BAD_REQUEST.toString(), "Exception occurred while Creating table");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        };
    }
    
	@PostMapping("/insertData")
	public ResponseEntity<Map<String, Object>> insertData(@RequestBody Map<String, Object> request) {
		logger.info("Insert Table called. Request received : {}", request);

		if (crudExecutor.insertData(request)) {
			return new ResponseEntity<>(request, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(request, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(EndpointReferrerConstants.SHOW_TABLE_API)
	public ResponseEntity<List<Map<String, Object>>> ShowData(@RequestBody Map<String, Object> request) {
		logger.info("Show Table called. Request received : {}", request);

		return new ResponseEntity<>(crudExecutor.showData(request), HttpStatus.OK);
	}
	
	@PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("data") String data) {
        try {
            writeStringToFile(data, filePath);            
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (IOException e) {
            logger.error("Exception occurred in uploadFile probable cause", e);
            return new ResponseEntity<>("Error", HttpStatus.EXPECTATION_FAILED);
        }
    }
	
	private void writeStringToFile(String data, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, data.getBytes());
    }
}
