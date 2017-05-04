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

import data.EquipmentDAO;
import entities.Equipment;

@RestController
public class EquipmentController {

	@Autowired
	private EquipmentDAO equip;

	@RequestMapping(path = "equipment/", method = RequestMethod.GET)
	public List<Equipment> index(HttpServletRequest request, HttpServletResponse response) {
		return equip.index();
	}
	
	@RequestMapping(path = "store/{storeId}/equipment/", method = RequestMethod.GET)
	public List<Equipment> indexStore(@PathVariable Integer storeId, HttpServletRequest request, HttpServletResponse response) {
		return equip.indexStore(storeId);
	}
	
	@RequestMapping(path="equipment/{equipmentId}", method=RequestMethod.GET)
	public Equipment show(@PathVariable int equipmentid, HttpServletRequest request, HttpServletResponse response){
		return equip.show(equipmentid);
	}
	
	@RequestMapping(path="equipment/{equipmentId}", method=RequestMethod.PUT)
	public Equipment update(@PathVariable int equipmentId, @RequestBody String jsonEquip, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Equipment mappedEquip = mapper.readValue(jsonEquip, Equipment.class);
			return equip.update(equipmentId, mappedEquip);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="user/{userid}/equipment", method=RequestMethod.POST)
	public Equipment createEquipment(@PathVariable int userid,@RequestBody String jsonEquip, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Equipment mappedEquip = mapper.readValue(jsonEquip, Equipment.class);
			return equip.create(userid, mappedEquip);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="equipment/{equipmentId}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int equipmentId, HttpServletRequest request, HttpServletResponse response){
		return equip.destroy(equipmentId);
	}

}
