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
import entities.Address;
import entities.Cart;

@RestController
public class CartController {
	
	@Autowired
	private CartDAO cart;

	@RequestMapping(path="cart/{cid}", method=RequestMethod.GET)
	public Cart show(@PathVariable int cid, HttpServletRequest request, HttpServletResponse response){
		return cart.show(cid);
	}
	
	@RequestMapping(path="cart/{cid}", method=RequestMethod.PUT)
	public Cart update(@PathVariable int cid, @RequestBody String jsonCart, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Cart mappedCart = mapper.readValue(jsonCart, Cart.class);
			return cart.update(cid, mappedCart);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="cart/{cid}", method=RequestMethod.POST)
	public Cart create(@PathVariable int cid, @RequestBody String jsonCart, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Cart mappedCart = mapper.readValue(jsonCart, Cart.class);
			return cart.create(cid, mappedCart);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}
