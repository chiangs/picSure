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

import data.ReservationItemDAO;
import entities.ReservationItem;

@RestController
public class ReservationItemController {

		@Autowired
		private ReservationItemDAO resItemDAO;

		@RequestMapping(value = "reservationItem/{id}", method = RequestMethod.GET)
		public ReservationItem index(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer id) {
			return resItemDAO.show(id);
		}

		@RequestMapping(value = "reservationItem/{userId}/user", method = RequestMethod.POST)
		public ReservationItem create(@PathVariable Integer resId, @PathVariable Integer inventoryId, @RequestBody String jsonResItem) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				ReservationItem mappedResItem = mapper.readValue(jsonResItem, ReservationItem.class);
				return resItemDAO.create(resId, inventoryId, mappedResItem);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		@RequestMapping(value = "reservationItem/{id}", method = RequestMethod.PUT)
		public ReservationItem update(@PathVariable Integer id, @RequestBody String jsonResItem) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				ReservationItem mappedResItem = mapper.readValue(jsonResItem, ReservationItem.class);
				return resItemDAO.update(id, mappedResItem);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		@RequestMapping(value = "reservationItem/{id}", method = RequestMethod.DELETE)
		public Boolean destroy(@PathVariable Integer id) {
			return resItemDAO.destroy(id);
		}
}
