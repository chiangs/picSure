package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import data.UserDAO;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
}
