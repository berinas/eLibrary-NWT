package etf.unsa.ba.BookDetails;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

import etf.unsa.ba.BookDetails.Services.GoogleDriveService;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class TestUpload {

	
	@Autowired
	GoogleDriveService driveService;
	
	@Test
	public void testUpload() {
		
		/*File file = new File("C:\\Users\\PC\\Desktop\\Computer Organization and Design 5th.pdf");
		com.google.api.services.drive.model.File file2  = driveService.upLoadFile((long) 1, file.getAbsolutePath(),"application/pdf");
		try {
			System.err.println(file2.toPrettyString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
}
