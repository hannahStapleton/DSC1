package com.c20321466.distributedsystems;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.c20321466.distributedsystems.model.User;


@Path("/MoneyLenders")
public class MoneyLenders {
	
//private static Map<String, User> users = new HashMap<String, User>();
	
//	static {
//		
//        User user1 = new User();
//        user1.setUserId("1");
//        user1.setUserName("Fabrizio");
//        user1.setJob("Software Engineer");
//        users.put(user1.getUserId(), user1);
//        
//        User user2 = new User();
//        user2.setUserId("2");
//        user2.setUserName("Justin");
//        user2.setJob("Business Analyst");
//        users.put(user2.getUserId(), user2);
//        
//    }

	@GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello(){
        return "Hello World";    
    }
	
	@GET
    @Path("/echo/{message}")
    @Produces("text/plain")
    public String echo(@PathParam("message")String message){
        return message;  
    }
	
	@GET
    @Path("/user/{userid}")
    @Produces("application/xml")
    public User getUser(@PathParam("userid")String userId){
		return users.get(userId);		
    }
	
	@POST
	@Path("/createxml")
    @Consumes("application/xml")
    public String addUser(User user){
		return "User added " + user.getUserName();		
    }
	
	@POST
	@Path("/createjson")
    @Consumes("application/json")
    public String addJSONUser(User user){
		return "User added " + user.getUserName();		
    }
	
	@GET
    @Path("/json/users/")
    @Produces("application/json")
    public List<User> listUsersJSON(){
		return new ArrayList<User>(users.values());
    }

	@GET
    @Path("/json/user/{userid}")
    @Produces("application/json")
    public User getUserJSON(@PathParam("userid")String userId){
		return users.get(userId);		
    }
	
	@GET
    @Path("/usersxmlfromdb")
    @Produces("application/xml")
    public List<User> getUsersFromDB(){
        UserDAO dao = new UserDAO();
        return dao.getAllUsers();
    }
	
	@GET
    @Path("/usersjsonfromdb")
    @Produces("application/json")
    public List<User> getJSONUsersFromDB(){
        UserDAO dao = new UserDAO();
        return dao.getAllUsers();
    }
	
	@GET
    @Path("/jsonDB/user/{userName}")
    @Produces("application/json")
    public User getUserByNameFromDBJSON(@PathParam("userName")String userName){
		UserDAO dao = new UserDAO();
		return dao.getUserByName(userName);		
    }
	
	@GET
    @Path("/userfromDBXML/{userName}")
    @Produces("application/xml")
    public User getUserByNameFromDBXML(@PathParam("userName")String userName){
		UserDAO dao = new UserDAO();
		return dao.getUserByName(userName);	
    }
	
	@POST
	@Path("/newUser")
    @Consumes("application/json")
    public String addUserToDBJSON(User user){
		UserDAO dao = new UserDAO();
		dao.persist(user);
		return "User added to DB from JSON Param "+user.getUserName();	
    }
	
	@PUT
    @Path("/updateUser/")
    @Produces("application/json")
    public User updateUser(User user){
		UserDAO dao = new UserDAO();
		return dao.merge(user);	
    }
	
	@DELETE
    @Path("/deleteUser/{userName}")
    @Produces("text/plain")
    public String deleteUser(@PathParam("userName")String userName){
		UserDAO dao = new UserDAO();
		User emp = dao.getUserByName(userName);
		dao.removeUser(emp);	
		return "User "+emp+" deleted";
    }
	
	
}

