package controllers;

import java.util.List;

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
	
	@RequestMapping(path="address/{aid}", method=RequestMethod.GET)
	public Address show(@PathVariable int aid, HttpServletRequest request, HttpServletResponse response){
		return add.show(aid);
	}
	
	@RequestMapping(path="address/{aid}", method=RequestMethod.PUT)
	public Address update(@PathVariable int aid, @RequestBody String jsonAddress, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Address mappedAddress = mapper.readValue(jsonAddress, Address.class);
			return add.update(aid, mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="address", method=RequestMethod.POST)
	public Address createAddress(@RequestBody String jsonAddress, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Address mappedAddress = mapper.readValue(jsonAddress, Address.class);
			return add.createAddress(mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="address/{aid}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int aid, HttpServletRequest request, HttpServletResponse response){
		return add.destroy(aid);
	}
}


