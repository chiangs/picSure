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

import data.CartItemDAO;
import entities.CartItem;

@RestController
public class CartItemController {

	@Autowired
	private CartItemDAO cartItem;

	@RequestMapping(path = "cartItem/{cartItemId}/", method = RequestMethod.GET)
	public CartItem show(@PathVariable int cartItemId, HttpServletRequest request, HttpServletResponse response) {
		return cartItem.show(cartItemId);
	}

	@RequestMapping(path = "user/{userId}/inventoryItem/{inventoryItemId}/cartItem", method = RequestMethod.POST)
	public CartItem createCartItem(@PathVariable int userId, @PathVariable int inventoryItemId, @RequestBody String jsonCartItem,
			HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			CartItem mappedCartItem = mapper.readValue(jsonCartItem, CartItem.class);
			return cartItem.create(userId, inventoryItemId, mappedCartItem);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(path = "cartItem/{cartItemId}", method = RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int cartItemId, HttpServletRequest request, HttpServletResponse response) {
		return cartItem.destroy(cartItemId);
	}
}
