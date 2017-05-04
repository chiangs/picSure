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
import entities.Address;
import entities.CartItem;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {

	@Autowired
	private CartItemDAO cartItem;

	@RequestMapping(path = "rest/{id}/cartItem/{cid}", method = RequestMethod.GET)
	public CartItem show(@PathVariable int cid, HttpServletRequest request, HttpServletResponse response) {
		return cartItem.show(cid);
	}

	@RequestMapping(path = "rest/{id}/cartItem/{cid}", method = RequestMethod.PUT)
	public CartItem update(@PathVariable int cid, @RequestBody String jsonCartItem, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			CartItem mappedCartItem = mapper.readValue(jsonCartItem, CartItem.class);
			return cartItem.update(cid, mappedCartItem);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(path = "rest/{id}/cartItem/{iid}", method = RequestMethod.POST)
	public CartItem createCartItem(@PathVariable int id, @PathVariable int iid, @RequestBody String jsonCartItem,
			HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			CartItem mappedCartItem = mapper.readValue(jsonCartItem, CartItem.class);
			return cartItem.create(id, iid, mappedCartItem);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(path = "rest/{id}/cartItem/{cid", method = RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int cid, HttpServletRequest request, HttpServletResponse response) {
		return cartItem.destroy(cid);
	}
}
