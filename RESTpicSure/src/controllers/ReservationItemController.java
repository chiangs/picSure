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
		
		@RequestMapping(value = "reservation/{reservationId}/reservationItem", method = RequestMethod.GET)
		public ReservationItem index(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer reservationId) {
			return resItemDAO.show(reservationId);
		}

		@RequestMapping(value = "reservationItem/{reservationItemId}", method = RequestMethod.PUT)
		public ReservationItem update(@PathVariable Integer reservationItemId, @RequestBody String jsonResItem) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				ReservationItem mappedResItem = mapper.readValue(jsonResItem, ReservationItem.class);
				return resItemDAO.update(reservationItemId, mappedResItem);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		@RequestMapping(value = "reservation/{reservationId}/inventoryItem/{inventoryItemId}/reservationItem", method = RequestMethod.POST)
		public ReservationItem create(@PathVariable Integer reservationId, @PathVariable Integer inventoryItemId, @RequestBody String jsonResItem) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				ReservationItem mappedResItem = mapper.readValue(jsonResItem, ReservationItem.class);
				return resItemDAO.create(reservationId, inventoryItemId, mappedResItem);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		@RequestMapping(value = "reservationItem/{reservationItemId}", method = RequestMethod.DELETE)
		public Boolean destroy(@PathVariable Integer reservationItemId) {
			return resItemDAO.destroy(reservationItemId);
		}
}
