package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public List<Reservation> storeIndex(Integer id) {
		String q = "SELECT r FROM Reservation r WHERE r.store.id = :id";
		return em.createQuery(q, Reservation.class).setParameter("id", id).getResultList();
	}

	@Override
	public Reservation create(Integer userId, Integer storeId, Cart c) {	
		
		Reservation reservation = new Reservation();
		
		reservation.setStore(em.find(Store.class, storeId));
		reservation.setUser(em.find(User.class, userId));
		
		List<CartItem> items = c.getCartItems();
		
		for(int i = 0; i < items.size(); i++) {
			ReservationItem resItem = new ReservationItem();
			resItem.setInventoryitems(items.get(i).getInventoryItem());
			resItem.setTimeIn(items.get(i).getTimeIn());
			resItem.setTimeOut(items.get(i).getTimeOut());
			reservation.getReservationItems().add(resItem);
		}
		
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
}