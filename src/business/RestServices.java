package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Feature;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import json.ExamJSON;
import json.ExamsJSON;
import json.MobileJSON;
import json.MobilesJSON;
import json.UserJSON;
import json.UsersJSON;
import model.Exam;
import model.Mobile;
import model.User;


@Singleton//Anotación de EJB compatible con Web Service
@Path("/prueba")
public class RestServices {
	@Context
    private javax.servlet.http.HttpServletRequest hsr;
	private static final String jboss_home_dir="jboss.home.dir";
	private static final String PATH_FILES="/files";
	
	@PersistenceContext 
	EntityManager em;
	 
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.TEXT_PLAIN)//indicamos que produce un codigo de texto plano
	@Path("/test")
	public boolean testOnline() {	
		System.out.println("Test ok IP:"+hsr.getRemoteAddr());
		//List<Mobile> mobileList=(List<Mobile>)em.createNamedQuery("Mobile.findAll");
		
		String yourSystemPath = System.getProperty("jboss.home.dir");
		File fileToSave = new File(yourSystemPath,"foo2.bar");  
		try {
			Writer out = new OutputStreamWriter(new FileOutputStream(fileToSave), "UTF-8");
			out.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("A");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("B");
		}finally{
			
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/saveMobile1")	
	public boolean getMobile1(MobileJSON mobileJSON) {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		if(mobileJSON==null){
			System.out.println("Los parametros pasados no son los correctos is null 1");

		}else if(mobileJSON.getMobilesMAC()==null){
			System.out.println("Los parametros pasados no son los correctos is null 2");
		}
		if(mobileJSON==null || mobileJSON.getMobilesMAC()==null ){
			System.out.println("Los parametros pasados no son los correctos");
			return false;
		}else{
			System.out.println("ha recibido correctamente los datos");
		}
		
		String yourSystemPath = System.getProperty(jboss_home_dir)+PATH_FILES;
		File fileToSave = new File(yourSystemPath, mobileJSON.getMobilesMAC()+".json");  
		if(fileToSave.isFile()){
			if(fileToSave.delete()){
				System.out.println("EL ARCHIVO SE HA BORRADO");
				Gson gson =new Gson();
				String json=gson.toJson(mobileJSON);
				if(json==null || json.trim().equals(""))return false;
				try {
				Writer writer = new OutputStreamWriter(new FileOutputStream(fileToSave), "UTF-8");
				PrintWriter out = new PrintWriter(writer);
				out.println(json.trim());
				writer.close();
				return true;
				} catch (UnsupportedEncodingException | FileNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("A");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("B");
				}finally{
				}
			}else{
				System.out.println("EL ARCHIVO NO SE HA BORRADO");
			}
		}else{
			System.out.println("NO EXISTE EL ARCHIVO QUE SE QUIERE ACCEDER");
		}
		
		
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/saveMobile2")	
	public boolean getMobile2(MobileJSON mobileJSON) {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		if(mobileJSON==null){
			System.out.println("Los parametros pasados no son los correctos is null 1");

		}else if(mobileJSON.getMobilesMAC()==null){
			System.out.println("Los parametros pasados no son los correctos is null 2");
		}
		if(mobileJSON==null || mobileJSON.getMobilesMAC()==null ){
			System.out.println("Los parametros pasados no son los correctos");
			return false;
		}else{
			System.out.println("ha recibido correctamente los datos");
		}
		String yourSystemPath = System.getProperty(jboss_home_dir)+PATH_FILES;
		File fileToSave = new File(yourSystemPath, mobileJSON.getMobilesMAC()+".json");  
		if(fileToSave.isFile()){
			if(fileToSave.delete()){
				System.out.println("EL ARCHIVO SE HA BORRADO");
			}else{
				System.out.println("EL ARCHIVO NO SE HA BORRADO");
			}
		}else{
			System.out.println("NO EXISTE EL ARCHIVO QUE SE QUIERE ACCEDER");
		}
		try {
		Writer out = new OutputStreamWriter(new FileOutputStream(fileToSave), "UTF-8");
		
		out.close();
		return true;
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("A");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("B");
		}finally{
		}
		
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getMobile1/{isbn}")	
	public String getMobile1(@PathParam("isbn") String mac) {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		if(mac==null || mac.equals("")){
			return null;
		}
		String yourSystemPath = System.getProperty(jboss_home_dir)+PATH_FILES;
		File fileToSave = new File(yourSystemPath,mac+".json");  
		System.out.println("fileToSave.getPath() "+fileToSave.getPath()+" fileToSave.getName() "+fileToSave.getName());
		if(fileToSave.isFile()){
			System.out.println("ARCHIVO EXISTE");
			try {
				FileInputStream fis = new FileInputStream(fileToSave);
				byte[] data = new byte[(int) fileToSave.length()];
				fis.read(data);
				fis.close();
				System.out.println("EL ARCHIVO SE HA LEIDO");
				String json=new String(data);
				System.out.println("EL ARCHIVO SE HA LEIDO json "+json);
				System.out.println("SE HA INICADO GSON");
				System.out.println("SE HA GENERADO LAS CLASES");
				return json;
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("A");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("B");
			}catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("c");
			}finally{
			}
		}else{
			System.out.println("NO EXISTE EL ARCHIVO QUE SE QUIERE ACCEDER 1");
		}
		
		
		
		
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.TEXT_PLAIN)//indicamos que produce un codigo de texto plano
	@Path("/test3")
	public boolean testOnline3() {	
		System.out.println("Test ok IP:"+hsr.getRemoteAddr());
		List<Mobile> mobileList=(List<Mobile>)em.createNamedQuery("Mobile.findAll").getResultList();
		return true;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.TEXT_PLAIN)//indicamos que produce un codigo de texto plano
	@Path("/test4")
	public boolean testOnline4() {	
		System.out.println("Test ok IP:"+hsr.getRemoteAddr());
		List<Mobile> mobileList=(List<Mobile>)em.createNamedQuery("Mobile.findAll").getResultList();
		
		for(int con=0;con<mobileList.size();con++){
			System.out.println("ID:"+mobileList.get(con).getIdmobiles()+" mac:"+mobileList.get(con).getMobilesMAC());
			em.remove(mobileList.get(con));
		}
		
		
		return true;
	}
	
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.TEXT_PLAIN)//indicamos que produce un codigo de texto plano
	@Path("/test2")
	public boolean testOnline2() {	
		System.out.println("Test ok IP:"+hsr.getRemoteAddr());
		
		
		
		Mobile mobile=new Mobile();
		Date date=new Date();
		mobile.setMobilesMAC("saasfasfasfafdsaf"+date.toString());
		
		User user=new User();
		user.setName("csdafsdafgasd");
		user.setPass("123444323");
		
		if(mobile.getUsers()==null){
			mobile.setUsers(new ArrayList<User>());
			System.out.println("hola");
		}
		if(user.getExams()==null){
			user.setExams(new ArrayList<Exam>());
			System.out.println("hola2");
		}
		/*
		for(int con2=0;con2<5;con2++){
			Exam exam=new Exam();
			exam.setDrafting("fd");
			exam.setIdexams(7687);
			exam.setLevel("sasadfsda"+date.toString());
			user.addExam(exam);
		}
		*/
		mobile.addUser(user);
		
		em.persist(mobile);//you need this, as there is no cascading from Product to Feature.
		//em.getTransaction().commit();//if you work with a resource-local persistence unit. Otherwise if JTA: commit the transaction the JTA-specific way (if that was not already done by the container)
		
		List<Mobile> mobileList=(List<Mobile>)em.createNamedQuery("Mobile.findAll").getResultList();
		for(int con=0;con<mobileList.size();con++){
			System.out.println("ID:"+mobileList.get(con).getIdmobiles()+" mac:"+mobileList.get(con).getMobilesMAC());
		}
		
		return true;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.TEXT_PLAIN)//indicamos que produce un codigo de texto plano
	@Path("/newUser")
	public boolean newUser() {	
		System.out.println("Test ok IP:"+hsr.getRemoteAddr());

		User user=new User();
		Date date=new Date();
		String name="csdafsdafgasd"+date.toString();
		user.setName(name);
		user.setPass("123444323");
		
		
		
		
		List<Exam> exams=new ArrayList<Exam>();
		for(int con=0;con<10;con++){
			Exam exam=new Exam();
			exam.setDrafting("asdvgsdvsdfgvasdgsdgasdfgasdgsadgsdgsdfagfdavfdfdfgvbf"+con);
			exam.setLevel("A2");
			exam.setNumExams(con);
			exam.setTypeExam("sdsdddsfgasdg");
			exams.add(exam);
		}
		user.setExams(exams);
		em.persist(user);
		//em.getTransaction().begin();
		//em.persist(user);//you need this, as there is no cascading from Product to Feature.
		//em.getTransaction().commit();
		//em.getTransaction().commit();//if you work with a resource-local persistence unit. Otherwise if JTA: commit the transaction the JTA-specific way (if that was not already done by the container)
		/*
		List<User> userList=(List<User>)em.createNamedQuery("User.findAll").getResultList();
		for(int con=0;con<userList.size();con++){
			System.out.println("asd"+con);
			System.out.println("name:"+userList.get(con).getName()+" pass:"+userList.get(con).getPass());
			if(userList.get(con).getName()==name){
				if(userList.get(con).getExams()==null){
					userList.get(con).setExams(new ArrayList<Exam>());
				}
				for(int con1=0;con1<10;con1++){
					Exam exam=new Exam();
					exam.setDrafting("asdvgsdvsdfgvasdgsdgasdfgasdgsadgsdgsdfagfdavfdfdfgvbf"+con1);
					exam.setLevel("A2");
					exam.setNumExams(con1);
					exam.setTypeExam("sdsdddsfgasdg");
					
					userList.get(con).addExam(exam);
				}
			}
			
		}
		*/
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.TEXT_PLAIN)//indicamos que produce un codigo de texto plano
	@Path("/newMobile")
	public boolean newMobile() {	
		System.out.println("Test ok IP:"+hsr.getRemoteAddr());
		Date date=new Date();
		String mac="hola"+date.toString();
		List<Mobile> mobilesList=(List<Mobile>)em.createNamedQuery("Mobile.findByMAC").setParameter("mobilesMAC",mac).getResultList();//Consultar la lista de todas las lecciones
		if(mobilesList!=null){
			System.out.println("size: "+mobilesList.size());
			if(mobilesList.size()==0){
				//No existe el movil
				Mobile newMobile=new Mobile();
				newMobile.setMobilesMAC(mac);
				newMobile.setUsers(new ArrayList<User>());
				User newUser=new User();
				newUser.setName("dasdd");
				newUser.setPass("fdsgrg");
				newUser.setExams(new ArrayList<Exam>());
				Exam exam=new Exam();
				exam.setDrafting("dsfsdafsdag");
				exam.setLevel("saasdf");
				exam.setResultExams(46);
				exam.setTypeExam("safas");
				newUser.addExam(exam);
				em.persist(newMobile);
				mobilesList=(List<Mobile>)em.createNamedQuery("Mobile.findByMAC").setParameter("mobilesMAC",mac).getResultList();//Consultar la lista de todas las lecciones
				System.out.println("size: "+mobilesList.size());
				if(mobilesList.size()==1){
					Mobile mobile=mobilesList.get(0);
					if(mobile.getUsers()==null){
						mobile.setUsers(new ArrayList<User>());
					}
					//mobile.addUser(newUser);
					//em.refresh(mobile);
					em.persist(mobile);
					mobilesList=(List<Mobile>)em.createNamedQuery("Mobile.findByMAC").setParameter("mobilesMAC",mac).getResultList();//Consultar la lista de todas las lecciones
					System.out.println("size: "+mobilesList.size());
					mobile=mobilesList.get(0);
					User user=mobile.getUsers().get(0);
				}
			}
		}
		/*
		User user=new User();
		Date date=new Date();
		String name="csdafsdafgasd"+date.toString();
		user.setName(name);
		user.setPass("123444323");
		
		
		
		
		List<Exam> exams=new ArrayList<Exam>();
		for(int con=0;con<10;con++){
			Exam exam=new Exam();
			exam.setDrafting("asdvgsdvsdfgvasdgsdgasdfgasdgsadgsdgsdfagfdavfdfdfgvbf"+con);
			exam.setLevel("A2");
			exam.setNumExams(con);
			exam.setTypeExam("sdsdddsfgasdg");
			exams.add(exam);
		}
		user.setExams(exams);
		em.persist(user);
		*/
		return true;
	}
	
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllExams")	
	public List<ExamJSON> getAllExams() {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		
		
		
		List<Exam> examsList=(List<Exam>)em.createNamedQuery("Exam.findAll").getResultList();//Consultar la lista de todas las lecciones
			
		return convertExams(examsList);

	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllUsers")	
	public List<UserJSON> getAllUsers() {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		System.out.println("requestLessons2: "+hsr.getRemoteAddr());

		List<User> usersList=(List<User>)em.createNamedQuery("User.findAll").getResultList();//Consultar la lista de todas las lecciones
		System.out.println("requestLessons1: "+hsr.getRemoteAddr());

		return null;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllMobiles")	
	public List<MobileJSON> getAllMobiles() {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		
		List<Mobile> mobilesList=(List<Mobile>)em.createNamedQuery("Mobile.findAll").getResultList();//Consultar la lista de todas las lecciones

		return convertMobile(mobilesList);
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getMobile/{isbn}")	
	public MobileJSON getMobile(@PathParam("isbn") String mac) {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		if(mac==null || mac.equals("")){
			return null;
		}
		List<Mobile> mobilesList=(List<Mobile>)em.createNamedQuery("Mobile.findByMAC").setParameter("mobilesMAC",mac).getResultList();//Consultar la lista de todas las lecciones
		if(mobilesList==null){
			return null;
		}
		if(mobilesList.size()!=0){
			System.out.println("mobilesList.size(): "+mobilesList.size());
			List<MobileJSON> mobilesListJSON=convertMobile(mobilesList);
			return mobilesListJSON.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/setMobile/{isbn}")	
	public boolean setMobile(@PathParam("isbn") String mac,ArrayList<UserJSON> users) {
		//System.out.println(users);
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		//ArrayList<UserJSON> user;
		if(mac==null || mac.equals("")){
			return false;
		}
		List<Mobile> mobilesList=(List<Mobile>)em.createNamedQuery("Mobile.findByMAC").setParameter("mobilesMAC",mac).getResultList();//Consultar la lista de todas las lecciones
		if(mobilesList==null){
			return false;
		}
		if(mobilesList.size()==0){
			System.out.println("mobilesList.size(): "+mobilesList.size());
			//no existe el movil
			Mobile mobile=new Mobile();
			mobile.setMobilesMAC(mac);
			em.persist(mobile);
			mobilesList=(List<Mobile>)em.createNamedQuery("Mobile.findByMAC").setParameter("mobilesMAC",mac).getResultList();//Consultar la lista de todas las lecciones
		}
		ArrayList<User> users2=convertUsers2(users);
		if(mobilesList.size()!=0){
			System.out.println("mobilesList.size()2: "+mobilesList.size());
			if(mobilesList.get(0).getUsers()==null){
				mobilesList.get(0).setUsers(new ArrayList<User>());
			}
			System.out.println("users.size()2: "+users.size());
			System.out.println("users2.size()2: "+users2.size());
			Mobile mobile2=mobilesList.get(0);
			em.remove(mobile2);
			mobilesList.get(0).setUsers(users2);
			System.out.println("mobile2.getUsers().size()2: "+mobile2.getUsers().size());
			
			for(int con=0;con<mobile2.getUsers().size();con++){
				System.out.println("mobile2.getUsers().get(con).getName(): "+con+" "+mobile2.getUsers().get(con).getName());
				mobile2.getUsers().get(con).setMobile(mobile2);
			}
			
			em.persist(mobile2);
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/addStudent")	
	public boolean addStudent(MobileJSON mobileJSON) {
		System.out.println("addStudent: "+hsr.getRemoteAddr());
		
		return false;
	}
	
	
	/*
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllExams")	
	public ExamsJSON getAllExams() {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		
		
		
		List<Exam> examsList=(List<Exam>)em.createNamedQuery("Exam.findAll").getResultList();//Consultar la lista de todas las lecciones
			
		return convertExams(examsList);

	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllUsers")	
	public UsersJSON getAllUsers() {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		
		List<User> usersList=(List<User>)em.createNamedQuery("User.findAll").getResultList();//Consultar la lista de todas las lecciones

		return convertUsers(usersList);
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllMobiles")	
	public MobilesJSON getAllMobiles() {
		System.out.println("requestLessons: "+hsr.getRemoteAddr());
		
		List<Mobile> mobilesList=(List<Mobile>)em.createNamedQuery("Mobile.findAll").getResultList();//Consultar la lista de todas las lecciones

		return convertMobile(mobilesList);
	}
	*/
	
	
	
	private List<ExamJSON> convertExams(List<Exam> examsList){
		if(examsList==null){
			System.out.println("No se puede pasar un argunmento null en ConvertExams");
			return null;
		}
		ExamsJSON examsJSON=new ExamsJSON();
		List<ExamJSON> examJSONList=new ArrayList<ExamJSON>();
		for(int i=0;i<examsList.size();i++){//Para cada lección de la lista
			Exam l=examsList.get(i);
			ExamJSON lJSON=new ExamJSON(l.getLevel(),l.getNumExams(),l.getResultExams(),l.getTypeExam(),l.getVoiceFileName(),l.getDrafting());//Crear objeto LessonJSON, copiando lessonCode y title
			examJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}
		examsJSON.setExams(examJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON

		return examJSONList;
	}
	
	private ArrayList<Exam> convertExams2(ArrayList<ExamJSON> examJSONList){
		if(examJSONList==null){
			System.out.println("No se puede pasar un argunmento null en ConvertExams");
			return null;
		}
		ArrayList<Exam> examsList=new ArrayList<Exam>();
		for(int i=0;i<examJSONList.size();i++){//Para cada lección de la lista
			ExamJSON l=examJSONList.get(i);
			Exam lJSON=new Exam(l.getLevel(),l.getNumExams(),l.getResultExams(),l.getTypeExam(),l.getVoiceFileName(),l.getDrafting());//Crear objeto LessonJSON, copiando lessonCode y title
			examsList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}

		return examsList;
	}
	
	
	
	
	private List<UserJSON> convertUsers(List<User> usersList){
		
		List<UserJSON> userJSONList=new ArrayList<UserJSON>();
		UsersJSON usersJSON=new UsersJSON();
		for(int i=0;i<usersList.size();i++){//Para cada lección de la lista
			User l=usersList.get(i);
			UserJSON lJSON=new UserJSON(l.getName(),l.getPass(),convertExams(l.getExams()));//Crear objeto LessonJSON, copiando lessonCode y title
			userJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}
		usersJSON.setUsers(userJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON

		return userJSONList;
		
	}
	
	private ArrayList<User> convertUsers2(ArrayList<UserJSON> userJSONList){
		if(userJSONList==null){
			System.out.println("No se puede pasar un argunmento null en ConvertExams");
			return null;
		}
		ArrayList<User> usersList=new ArrayList<User>();
		for(int i=0;i<userJSONList.size();i++){//Para cada lección de la lista
			UserJSON l=userJSONList.get(i);
			User lJSON=new User(l.getName(),l.getPass(),convertExams2((ArrayList<ExamJSON>) l.getExams()));//Crear objeto LessonJSON, copiando lessonCode y title
			usersList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}

		return usersList;
	}
	
	
	private List<MobileJSON> convertMobile(List<Mobile> mobilesList){
		
		List<MobileJSON> mobileJSONList=new ArrayList<MobileJSON>();
		MobilesJSON mobilesJSON=new MobilesJSON();
		for(int i=0;i<mobilesList.size();i++){//Para cada lección de la lista
			Mobile l=mobilesList.get(i);
			MobileJSON lJSON=new MobileJSON(l.getMobilesMAC(),convertUsers(l.getUsers()));//Crear objeto LessonJSON, copiando lessonCode y title
			mobileJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}
		mobilesJSON.setUsers(mobileJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON

		return mobileJSONList;
		
	}
	
	private ArrayList<Mobile> convertMobiles2(ArrayList<MobileJSON> mobilesJSONList){
		if(mobilesJSONList==null){
			System.out.println("No se puede pasar un argunmento null en ConvertExams");
			return null;
		}
		ArrayList<Mobile> mobilesList=new ArrayList<Mobile>();
		for(int i=0;i<mobilesJSONList.size();i++){//Para cada lección de la lista
			MobileJSON l=mobilesJSONList.get(i);
			Mobile lJSON=new Mobile(l.getMobilesMAC(),convertUsers2((ArrayList<UserJSON>) l.getUsers()));//Crear objeto LessonJSON, copiando lessonCode y title
			mobilesList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}

		return mobilesList;
	}
	
	
	/*
	private ExamsJSON convertExams(List<Exam> examsList){
		if(examsList==null){
			System.out.println("No se puede pasar un argunmento null en ConvertExams");
			return null;
		}
		ExamsJSON examsJSON=new ExamsJSON();
		List<ExamJSON> examJSONList=new ArrayList<ExamJSON>();
		for(int i=0;i<examsList.size();i++){//Para cada lección de la lista
			Exam l=examsList.get(i);
			ExamJSON lJSON=new ExamJSON(l.getLevel(),l.getNumExams(),l.getResultExams(),l.getTypeExam(),l.getVoiceFileName(),l.getDrafting());//Crear objeto LessonJSON, copiando lessonCode y title
			examJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}
		examsJSON.setExams(examJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON

		return examsJSON;
	}
	
	
	
	private UsersJSON convertUsers(List<User> usersList){
		
		List<UserJSON> userJSONList=new ArrayList<UserJSON>();
		UsersJSON usersJSON=new UsersJSON();
		for(int i=0;i<usersList.size();i++){//Para cada lección de la lista
			User l=usersList.get(i);
			UserJSON lJSON=new UserJSON(l.getName(),l.getPass(),convertExams(l.getExams()));//Crear objeto LessonJSON, copiando lessonCode y title
			userJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}
		usersJSON.setUsers(userJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON

		return usersJSON;
		
	}
	
	private MobilesJSON convertMobile(List<Mobile> mobilesList){
		
		List<MobileJSON> mobileJSONList=new ArrayList<MobileJSON>();
		MobilesJSON mobilesJSON=new MobilesJSON();
		for(int i=0;i<mobilesList.size();i++){//Para cada lección de la lista
			Mobile l=mobilesList.get(i);
			MobileJSON lJSON=new MobileJSON(l.getMobilesMAC(),convertUsers(l.getUsers()));//Crear objeto LessonJSON, copiando lessonCode y title
			mobileJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}
		mobilesJSON.setUsers(mobileJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON

		return mobilesJSON;
		
	}
	*/
	
	
	
	
}
