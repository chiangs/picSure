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
import entities.Inventory;

@RestController
public class InventoryController {

	@Autowired
	private InventoryDAO inv;

	@RequestMapping(path="inventory/{inventoryId}", method=RequestMethod.GET)
	public Inventory show(@PathVariable int inventoryId, HttpServletRequest request, HttpServletResponse response){
		return inv.show(inventoryId);
	}
	
	@RequestMapping(path="inventory/{inventoryId}", method=RequestMethod.PUT)
	public Inventory update(@PathVariable int inventoryId, @RequestBody String jsonInventory, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Inventory mappedInventory = mapper.readValue(jsonInventory, Inventory.class);
			return inv.update(inventoryId, mappedInventory);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="user/{userId}/inventory", method=RequestMethod.POST)
	public Inventory createInventory(@PathVariable int userId, @RequestBody String jsonInventory, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Inventory mappedInventory = mapper.readValue(jsonInventory, Inventory.class);
			return inv.create(userId, mappedInventory);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="inventory/{inventoryId}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int inventoryId, HttpServletRequest request, HttpServletResponse response){
		return inv.destroy(inventoryId);
	}
}






