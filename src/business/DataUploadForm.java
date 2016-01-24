package business;

import java.io.InputStream;
import java.io.Serializable;

import javax.ws.rs.FormParam;

public class DataUploadForm implements Serializable {

	   @FormParam("xml")
	   private String xml;

	   

	@FormParam("file")
	   private InputStream fileStream;

	   public DataUploadForm() {
	       super();
	   }
	   public String getXml() {
			return xml;
		}

		public void setXml(String xml) {
			this.xml = xml;
		}

		public InputStream getFileStream() {
			return fileStream;
		}

		public void setFileStream(InputStream fileStream) {
			this.fileStream = fileStream;
		}

	   // Getters and setters here
}
