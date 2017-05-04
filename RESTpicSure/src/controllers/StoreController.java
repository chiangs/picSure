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

import data.StoreDAO;
import entities.Store;
import entities.User;

@RestController
public class StoreController {

	@Autowired
	private StoreDAO storeDAO;

	@RequestMapping(value = "store/{id}", method = RequestMethod.GET)
	public Store index(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer id) {
		return storeDAO.show(id);
	};

	@RequestMapping(value = "store/", method = RequestMethod.POST)
	public Store create(@RequestBody String jsonUser) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User mappedUser = mapper.readValue(jsonUser, User.class);
			return storeDAO.create(mappedUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "store/{id}", method = RequestMethod.PUT)
	public Store update(@PathVariable Integer id, @RequestBody String jsonUser) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User mappedUser = mapper.readValue(jsonUser, User.class);
			return storeDAO.update(id, mappedUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "store/{id}", method = RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return storeDAO.destroy(id);
	}

}
