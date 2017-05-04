package controllers;

import java.util.List;

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
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressDAO add;
	
	@RequestMapping(path="ping", method=RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path="rest/address/{id}", method=RequestMethod.GET)
	public Address show(@PathVariable int id){
		return add.show(id);
	}
	
	@RequestMapping(path="rest/address/{id}", method=RequestMethod.PUT)
	public Address update(@PathVariable int id, @RequestBody String jsonAddress){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Address mappedAddress = mapper.readValue(jsonAddress, Address.class);
			return add.update(id, mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="rest/address", method=RequestMethod.POST)
	public Address createAddress(@RequestBody String jsonAddress){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Address mappedAddress = mapper.readValue(jsonAddress, Address.class);
			return add.createAddress(mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="rest/address/{id}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int id){
		return add.destroy(id);
	}
}


