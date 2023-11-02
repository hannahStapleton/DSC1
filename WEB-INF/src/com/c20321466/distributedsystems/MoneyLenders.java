package com.c20321466.distributedsystems;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.c20321466.distributedsystems.model.User;

@Path("/MoneyLenders")
public class MoneyLenders {

	@GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello(){
        return "Hello World";    
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
    @Path("/jsonDB/user/{userId}")
    @Produces("application/json")
    public User getUserByIdFromDBJSON(@PathParam("userId")String userId){
		UserDAO dao = new UserDAO();
		return dao.getUserById(userId);		
    }
	
	@GET
    @Path("/userfromDBXML/{userId}")
    @Produces("application/xml")
    public User getUserByIdFromDBXML(@PathParam("userId")String userId){
		UserDAO dao = new UserDAO();
		return dao.getUserById(userId);	
    }
	
	@POST
	@Path("/newUser")
    @Consumes("application/json")
    public String addUserToDBJSON(User user){
		UserDAO dao = new UserDAO();
		dao.persist(user);
		return "User added to DB from JSON Param "+user.getUserId();	
    }
	
	@PUT
    @Path("/updateUser/")
    @Produces("application/json")
    public User updateUser(User user){
		UserDAO dao = new UserDAO();
		return dao.merge(user);	
    }
	
	@DELETE
    @Path("/deleteUser/{userId}")
    @Produces("text/plain")
    public String deleteUser(@PathParam("userId")String userId){
		UserDAO dao = new UserDAO();
		User user = dao.getUserById(userId);
		dao.removeUser(user);	
		return "User "+user+" deleted";
    }
	
}