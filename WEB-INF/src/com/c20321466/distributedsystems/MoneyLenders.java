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

import com.c20321466.distributedsystems.model.Deposit;
import com.c20321466.distributedsystems.model.Loan;
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
    public User getUserByIdFromDBJSON(@PathParam("userId")int userId){
		UserDAO dao = new UserDAO();
		return dao.getUserById(userId);		
    }
	
	@GET
    @Path("/userfromDBXML/{userId}")
    @Produces("application/xml")
    public User getUserByIdFromDBXML(@PathParam("userId")int userId){
		UserDAO dao = new UserDAO();
		return dao.getUserById(userId);	
    }

	@GET
    @Path("/loansjsonfromdb")
    @Produces("application/json")
    public List<Loan> getJSONLoansFromDB(){
        LoanDAO loanDao = new LoanDAO();
        return loanDao.getAllLoans();
    }

	@GET
    @Path("/depositsjsonfromdb")
    @Produces("application/json")
    public List<Deposit> getJSONDepositsFromDB(){
        DepositDAO depositDao = new DepositDAO();
        return depositDao.getAllDeposits();
    }
	
	@GET
    @Path("/jsonDB/loan/{loanId}")
    @Produces("application/json")
    public Loan getLoanByIdFromDBJSON(@PathParam("loanId")int loanId){
		LoanDAO loanDao = new LoanDAO();
		return loanDao.getLoanById(loanId);		
    }
	
	@POST
	@Path("/newUser")
    @Consumes("application/json")
    public String addAccountToDBJSON(User user){
		UserDAO userDao = new UserDAO();
		LoanDAO loanDao = new LoanDAO();
		Loan loan = user.getLoan();
        user.setLoan(loan);
        userDao.persist(user);
		loan.setUser(user);
        loanDao.persist(loan);
		return "User added to DB from JSON Param "+user.getUserId();		
    }

	
	@POST
    @Path("/createDeposit/{loanId}")
    public String createDeposit(@PathParam("loanId") int loanId, Deposit deposit) {
		String response = "oops";
		LoanDAO loanDao = new LoanDAO();
		DepositDAO depositDao = new DepositDAO();
		Loan loan = loanDao.getLoanById(loanId);
		if (loan != null) {
			deposit.setLoanId(loanId);
			depositDao.persist(deposit);
            loan.addDeposit(deposit);
    		response = "Deposit added to Loan "+loanId;
            loanDao.merge(loan);
		}
		return response;
	}
	
	@PUT
    @Path("/updateUser/{userId}")
    @Produces("application/json")
    public User updateUser(@PathParam("userId") int userId,User user){
		UserDAO dao = new UserDAO();
		User currentUser = dao.getUserById(userId);
		if (currentUser != null) {
			currentUser.setUserName(user.getUserName());
			currentUser.setUserAddress(user.getUserAddress());
			currentUser.setUserPhone(user.getUserPhone());
			currentUser.setUserSalary(user.getUserSalary());
			currentUser.setLoan(user.getLoan());
		}
		return dao.merge(currentUser);	
    }
	
	@PUT
    @Path("/updateLoan/{loanId}")
    @Produces("application/json")
    public Loan updateLoan(@PathParam("loanId") int loanId, Loan loan){
		LoanDAO loanDao = new LoanDAO();
		Loan currentLoan = loanDao.getLoanById(loanId);
		if (currentLoan != null) {
			currentLoan.setLoanDescription(loan.getLoanDescription());
			currentLoan.setDeposits(loan.getDeposits());
			currentLoan.setLoanAmount(loan.getLoanAmount());
		}
		return loanDao.merge(loan);	
    }
	
	@DELETE
    @Path("/deleteUser/{userId}")
    @Produces("text/plain")
    public String deleteUser(@PathParam("userId")int userId){
		UserDAO dao = new UserDAO();
		User user = dao.getUserById(userId);
		dao.removeUser(user);	
		return "User "+user+" deleted";
    }
	
	@DELETE
    @Path("/deleteLoan/{loanId}")
    @Produces("text/plain")
    public String deleteLoan(@PathParam("loanId")int loanId){
		LoanDAO loanDao = new LoanDAO();
		Loan loan = loanDao.getLoanById(loanId);
		loanDao.remove(loan);	
		return "Loan "+loan+" deleted";
    }
	
}