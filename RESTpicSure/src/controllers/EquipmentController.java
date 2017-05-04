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
import entities.Address;
import entities.Equipment;

@RestController
public class EquipmentController {

	@Autowired
	private EquipmentDAO equip;

	@RequestMapping(path = "equipment", method = RequestMethod.GET)
	public List<Equipment> show(HttpServletRequest request, HttpServletResponse response) {
		return equip.index();
	}
	
	@RequestMapping(path="equipment/{eid}", method=RequestMethod.GET)
	public Equipment show(@PathVariable int eid, HttpServletRequest request, HttpServletResponse response){
		return equip.show(eid);
	}
	
	@RequestMapping(path="equipment/{eid}", method=RequestMethod.PUT)
	public Equipment update(@PathVariable int eid, @RequestBody String jsonEquip, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Equipment mappedEquip = mapper.readValue(jsonEquip, Equipment.class);
			return equip.update(eid, mappedEquip);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="user/{id}/equipment", method=RequestMethod.POST)
	public Equipment createEquipment(@PathVariable int id,@RequestBody String jsonEquip, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Equipment mappedEquip = mapper.readValue(jsonEquip, Equipment.class);
			return equip.create(id, mappedEquip);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="equipment/{eid}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int eid, HttpServletRequest request, HttpServletResponse response){
		return equip.destroy(eid);
	}

}
