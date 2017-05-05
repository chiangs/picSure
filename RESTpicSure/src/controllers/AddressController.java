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

import data.AddressDAO;
import entities.Address;

@RestController

public class AddressController {
	
	@Autowired
	private AddressDAO add;
	
	@RequestMapping(path="ping", method=RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path="store/{storeId}/address", method=RequestMethod.GET)
	public Address show(@PathVariable int storeId, HttpServletRequest request, HttpServletResponse response){
		return add.show(storeId);
	}
	
	@RequestMapping(path="store/{storeId}/address", method=RequestMethod.PUT)
	public Address update(@PathVariable int storeId, @RequestBody String jsonAddress, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Address mappedAddress = mapper.readValue(jsonAddress, Address.class);
			return add.update(storeId, mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="address/{addressId}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int addressId, HttpServletRequest request, HttpServletResponse response){
		return add.destroy(addressId);
	}
}


