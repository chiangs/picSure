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

import data.InventoryItemDAO;
import entities.Address;
import entities.Inventory;
import entities.InventoryItem;

@RestController

public class InventoryItemController {

	@Autowired
	private InventoryItemDAO invItem;

	@RequestMapping(path = "inventoryitem/{iiid}", method = RequestMethod.GET)
	public InventoryItem show(@PathVariable int iiid, HttpServletRequest request, HttpServletResponse response) {
		return invItem.show(iiid);
	}

	@RequestMapping(path = "inventoryitem/{iiid}", method = RequestMethod.PUT)
	public InventoryItem update(@PathVariable int iiid, @RequestBody String jsonInvItem, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			InventoryItem mappedInvItem = mapper.readValue(jsonInvItem, InventoryItem.class);
			return invItem.update(iiid, mappedInvItem);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(path = "inventoryitem/{iid}/{eid}", method = RequestMethod.POST)
	public InventoryItem createInventoryItem(@PathVariable int eid,@PathVariable int iid, @RequestBody String jsonInvItem, HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			InventoryItem mappedInventoryItem = mapper.readValue(jsonInvItem, InventoryItem.class);
			return invItem.create(iid, eid, mappedInventoryItem);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(path="inventoryitem/{iiid}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int iiid, HttpServletRequest request, HttpServletResponse response){
		return invItem.destroy(iiid);
	}
}
