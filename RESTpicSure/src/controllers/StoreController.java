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

import data.StoreDAO;
import entities.Inventory;
import entities.Store;

@RestController
public class StoreController {

	@Autowired
	private StoreDAO storeDAO;
	
	@RequestMapping(value = "store", method = RequestMethod.GET)
	public List<Store> index(HttpServletRequest req, HttpServletResponse res) {
		return storeDAO.index();
	}
	
	@RequestMapping(value = "store/equipment/{equipmentId}", method = RequestMethod.GET)
	public List<Store> indexEquipment(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer equipmentId) {
		return storeDAO.indexEquipment(equipmentId);
	}

	@RequestMapping(value = "store/{storeId}", method = RequestMethod.GET)
	public Store show(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer storeId) {
		return storeDAO.show(storeId);
	}
	
	@RequestMapping(value = "store/{storeId}/inventory", method = RequestMethod.GET)
	public Inventory showInventory(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer storeId) {
		return storeDAO.showInventory(storeId);
	}

	@RequestMapping(value = "store/{storeId}", method = RequestMethod.PUT)
	public Store update(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer storeId, @RequestBody String jsonStore) {
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println(jsonStore);
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		System.out.println("****************************************8");
		try {
			ObjectMapper mapper = new ObjectMapper();
			Store mappedStore = mapper.readValue(jsonStore, Store.class);
			return storeDAO.update(storeId, mappedStore);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "user/{userId}/store", method = RequestMethod.POST)
	public Store create(HttpServletRequest req, HttpServletResponse res, @RequestBody String jsonStore, @PathVariable Integer userId) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Store mappedStore = mapper.readValue(jsonStore, Store.class);
			return storeDAO.create(userId, mappedStore);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "store/{storeId}", method = RequestMethod.DELETE)
	public Boolean destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer storeId) {
		return storeDAO.destroy(storeId);
	}

}
