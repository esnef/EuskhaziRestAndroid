package business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Singleton//Anotación de EJB compatible con Web Service
@Path("/prueba2")
public class RestServices2 {
	@Context
    private javax.servlet.http.HttpServletRequest hsr;
	private static final String jboss_home_dir="jboss.home.dir";
	private static final String PATH_FILES="/files";
	private final String UPLOADED_FILE_PATH = "d:\\";
	
	
	
}
