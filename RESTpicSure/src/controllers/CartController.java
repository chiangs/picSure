package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.CartDAO;
import entities.Cart;

@RestController
public class CartController {

	@Autowired
	private CartDAO cart;

	@RequestMapping(path = "user/{userId}/cart", method = RequestMethod.GET)
	public Cart show(@PathVariable Integer userId, HttpServletRequest request, HttpServletResponse response) {
		return cart.show(userId);
	}
	
	@RequestMapping(path = "user/{userId}/cart", method = RequestMethod.GET)
	public Cart empty(@PathVariable Integer userId, HttpServletRequest request, HttpServletResponse response) {
		return cart.show(userId);
	}
}
