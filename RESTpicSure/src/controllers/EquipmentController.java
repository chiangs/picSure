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

	@RequestMapping(path = "equipment", method = RequestMethod.GET)
	public List<Equipment> index(HttpServletRequest request, HttpServletResponse response) {
		return equip.index();
	}
	
	@RequestMapping(path="equipment/{equipmentId}", method=RequestMethod.GET)
	public Equipment show(@PathVariable Integer equipmentId, HttpServletRequest request, HttpServletResponse response){
		return equip.show(equipmentId);
	}
	
	@RequestMapping(path="equipment/{equipmentId}", method=RequestMethod.PUT)
	public Equipment update(@PathVariable Integer equipmentId, @RequestBody String jsonEquip, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Equipment mappedEquip = mapper.readValue(jsonEquip, Equipment.class);
			return equip.update(equipmentId, mappedEquip);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="equipment", method=RequestMethod.POST)
	public Equipment createEquipment(@RequestBody String jsonEquip, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(jsonEquip);
			Equipment mappedEquip = mapper.readValue(jsonEquip, Equipment.class);
			return equip.create(mappedEquip);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path="equipment/{equipmentId}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer equipmentId, HttpServletRequest request, HttpServletResponse response){
		return equip.destroy(equipmentId);
	}

}
