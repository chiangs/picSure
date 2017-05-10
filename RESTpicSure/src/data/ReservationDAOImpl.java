package data;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.rmi.CORBA.UtilDelegate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Cart;
import entities.CartItem;
import entities.Reservation;
import entities.ReservationItem;
import entities.Store;
import entities.User;

@Transactional
@Repository
public class ReservationDAOImpl implements ReservationDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Reservation show(Integer id) {
		return em.find(Reservation.class, id);
	}
	
	@Override
	public List<Reservation> userIndex(Integer id) {
		String q = "SELECT r FROM Reservation r WHERE r.user.id = :id";
		return em.createQuery(q, Reservation.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<Reservation> storeIndex(Integer userId) {
		User u = em.find(User.class, userId);
		Store s = u.getStore().get(0);
		String q = "SELECT r FROM Reservation r WHERE r.store.id = :id";
		return em.createQuery(q, Reservation.class).setParameter("id", s.getId()).getResultList();
	}

	@Override
	public Reservation create(Integer userId, Integer storeId) {	
		
		Reservation reservation = new Reservation();
		List<ReservationItem> reservationItems = new ArrayList<>();
		User u = em.find(User.class, userId);
		
		reservation.setStore(em.find(Store.class, storeId));
		reservation.setUser(u);
		reservation.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		em.persist(reservation);
		em.flush();
		
		String q = "SELECT c FROM CartItem c WHERE c.cart.id = :id";
		List<CartItem> items = em.createQuery(q, CartItem.class).setParameter("id", u.getCart().getId()).getResultList();
			
		for(int i = 0; i < items.size(); i++) {
			ReservationItem resItem = new ReservationItem();
			resItem.setInventoryitems(items.get(i).getInventoryItem());
			resItem.setTimeIn(items.get(i).getTimeIn());
			resItem.setTimeOut(items.get(i).getTimeOut());
			resItem.setReservations(reservation);
			reservationItems.add(resItem);
		}
		
		reservation.setReservationItems(reservationItems);
		em.persist(reservation);
		em.flush();
		return reservation;
	}

	@Override
	public Boolean destroy(Integer id) {
		try {
			em.remove(em.find(Reservation.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean destroyReservationItem(Integer reservationItemId) {
		
		ReservationItem item = em.find(ReservationItem.class, reservationItemId);
		Reservation res = em.find(Reservation.class, item.getReservations().getId());
		List<ReservationItem> updatedItems = res.getReservationItems();
		
		for (int i = 0; i < updatedItems.size(); i++) {
			if (updatedItems.get(i).getId() == reservationItemId){
				updatedItems.remove(updatedItems.get(i));
			}
		}
		
		res.setReservationItems(updatedItems);
		
		try {
			em.remove(em.find(ReservationItem.class, reservationItemId));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}