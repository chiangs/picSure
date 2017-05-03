package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Reservation;
import entities.ReservationItem;
import entities.User;

@Transactional
@Repository
public class ReservationDAOImpl implements ReservationDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Reservation> userIndex(Integer id) {
		String q = "SELECT r FROM Reservation r WHERE r.userId = :id";
		return em.createQuery(q, Reservation.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<Reservation> storeIndex(Integer id) {
		String q = "SELECT r FROM Reservation r WHERE r.reservationItem.inventoryItem.inventory.storeId = :id";
		return em.createQuery(q, Reservation.class).setParameter("id", id).getResultList();
	}

	@Override
	public Reservation show(Integer id) {
		return em.find(Reservation.class, id);
	}

	@Override
	public Reservation update(Integer id, Reservation r) {
		Reservation res = em.find(Reservation.class, id);
		res.setReservationItems(r.getReservationItems());
		return res;
	}

	@Override
	public Reservation create(Integer userId, Reservation r) {
		List<ReservationItem> resItems = new ArrayList<>();
		r.setReservationItems(resItems);
		r.setCreatedDate(r.getCreatedDate());
		r.setUser(em.find(User.class, userId));
		
		
		em.persist(r);
		em.flush();
		return r;
	}

	@Override
	public Boolean destroy(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}