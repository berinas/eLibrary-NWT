package etf.unsa.ba.BookDetails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import etf.unsa.ba.BookDetails.Services.GoogleDriveService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDetailsApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	GoogleDriveService driveService;
	
	
	@Test
	public void testUpload() {
		
		/*File file = new File("C:\\Users\\PC\\Desktop\\pz.pdf");
		com.google.api.services.drive.model.File file2  = driveService.upLoadFile((long) 1, file.getAbsolutePath(),"application/pdf");
		try {
			System.err.println(file2.toPrettyString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
}
