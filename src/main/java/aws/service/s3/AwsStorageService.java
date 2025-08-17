package aws.service.s3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.Multipart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Component
public class AwsStorageService {

	
	
	public File downLoadFileFromS3(Integer Id) {
		File file = null;
		
		return file;
	}
	
	public ResponseEntity<String> uploadFileTos3(File file){
		
		
		
		return null;
		
	}
	
	public File convertMultiPartFileToFile(MultipartFile file) throws IOException {
		
		File file2 = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(file2);
		
		fos.write(file.getBytes());
		
		return file2;
	}
	
}
