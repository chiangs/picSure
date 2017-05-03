package controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.AuthenticationDAO;
import entities.User;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationDAO authDAO;
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public User register(HttpSession session, @RequestBody User user) {
		User newUser = authDAO.register(user);
		session.setAttribute("sessionUser", newUser);
		return newUser;
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public User login(HttpSession session, @RequestBody User user) {
		User sessionUser = authDAO.authenticateUser(user);
		session.setAttribute("sessionUser", sessionUser);
		return sessionUser;
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public Boolean logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute("sessionUser");
		return true;
	}
	
	@RequestMapping(path="/unauthorized")
	public String unauth(HttpServletResponse response) {
		response.setStatus(401);
		return "unauthorized";
	}

}
