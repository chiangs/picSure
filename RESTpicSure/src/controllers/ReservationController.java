package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.ReservationDAO;
import entities.Reservation;

@RestController
public class ReservationController {

	@Autowired
	private ReservationDAO reservationDAO;

	@RequestMapping(value = "reservation/{reservationId}", method = RequestMethod.GET)
	public Reservation show(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer reservationId) {
		return reservationDAO.show(reservationId);
	}

	@RequestMapping(value = "user/{userId}/reservation", method = RequestMethod.GET)
	public List<Reservation> userIndex(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer userId) {
		return reservationDAO.userIndex(userId);
	}
	
	@RequestMapping(value = "store/{userId}/reservation", method = RequestMethod.GET)
	public List<Reservation> storeIndex(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer userId) {
		return reservationDAO.storeIndex(userId);
	}
	
	@RequestMapping(value = "user/{userId}/store/{storeId}/reservation", method = RequestMethod.POST)
	public Reservation create(@PathVariable Integer userId, @PathVariable Integer storeId) {
			return reservationDAO.create(userId, storeId);
		
	}

	@RequestMapping(value = "reservation/{reservationId}", method = RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer reservationId) {
		return reservationDAO.destroy(reservationId);
	}
	
	@RequestMapping(value = "reservationItem/{reservationItemId}", method = RequestMethod.DELETE)
	public Boolean destroyReservationItem(@PathVariable Integer reservationItemId) {
		return reservationDAO.destroyReservationItem(reservationItemId);
	}
}
