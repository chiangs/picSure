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

import data.InventoryDAO;
import entities.Address;
import entities.Inventory;

@RestController
public class InventoryController {

	@Autowired
	private InventoryDAO inv;

	@RequestMapping(path="rest/{id}/inventory/{iid}", method=RequestMethod.GET)
	public Inventory show(@PathVariable int iid, HttpServletRequest request, HttpServletResponse response){
		return inv.show(iid);
	}
	
	@RequestMapping(path="rest/{id}/inventory/{iid}", method=RequestMethod.PUT)
	public Inventory update(@PathVariable int iid, @RequestBody String jsonInventory, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Inventory mappedInventory = mapper.readValue(jsonInventory, Inventory.class);
			return inv.update(iid, mappedInventory);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="rest/{id}/inventory", method=RequestMethod.POST)
	public Inventory createInventory(@PathVariable int id,@RequestBody String jsonInventory, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Inventory mappedInventory = mapper.readValue(jsonInventory, Inventory.class);
			return inv.create(id, mappedInventory);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="rest/{id}/invnentory/{iid}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int iid, HttpServletRequest request, HttpServletResponse response){
		return inv.destroy(iid);
	}
}






