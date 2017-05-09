package controllers;

import java.util.List;
import java.util.Set;

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
import entities.InventoryItem;

@RestController

public class InventoryItemController {

	@Autowired
	private InventoryItemDAO invItem;

	@RequestMapping(path = "inventoryItem", method = RequestMethod.GET)
	public List<InventoryItem> index(HttpServletRequest request, HttpServletResponse response) {
		return invItem.index();
	}
	
	@RequestMapping(path = "inventoryItem/{equipmentType}/equipmentType", method = RequestMethod.GET)
	public List<InventoryItem> index(@PathVariable String equipmentType, HttpServletRequest request, HttpServletResponse response) {
		return invItem.indexEquipmentType(equipmentType);
	}

	@RequestMapping(path = "inventoryItem/{inventoryItemId}", method = RequestMethod.GET)
	public InventoryItem show(@PathVariable int inventoryItemId, HttpServletRequest request, HttpServletResponse response) {
		return invItem.show(inventoryItemId);
	}

	@RequestMapping(path = "inventoryItem/{inventoryItemId}", method = RequestMethod.PUT)
	public InventoryItem update(@PathVariable int inventoryItemId, @RequestBody String jsonInvItem, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			InventoryItem mappedInvItem = mapper.readValue(jsonInvItem, InventoryItem.class);
			return invItem.update(inventoryItemId, mappedInvItem);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(path = "inventory/{inventoryid}/equipment/{equipmentId}/inventoryItem", method = RequestMethod.POST)
	public InventoryItem createInventoryItem(@PathVariable int equipmentId,@PathVariable int inventoryid, HttpServletRequest request, HttpServletResponse response) {

			return invItem.create(inventoryid, equipmentId);
	}
	
	
	@RequestMapping(path="inventoryItem/{inventoryItemId}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int inventoryItemId, HttpServletRequest request, HttpServletResponse response){
		return invItem.destroy(inventoryItemId);
	}
	
	@RequestMapping(path="inventoryItems/inventory/{inventoryId}", method=RequestMethod.PUT)
	public List <InventoryItem> updateItems(@PathVariable int inventoryId,@RequestBody String jsonInvItem, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			InventoryItem mappedInvItem = mapper.readValue(jsonInvItem, InventoryItem.class);
			return invItem.updateItems(inventoryId, mappedInvItem);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
