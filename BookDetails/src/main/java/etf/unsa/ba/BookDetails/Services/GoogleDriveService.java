package etf.unsa.ba.BookDetails.Services;

import com.google.api.services.drive.model.File;

public interface GoogleDriveService {

	public File upLoadFile(Long file_id,String filePath,String mimeType);

}
