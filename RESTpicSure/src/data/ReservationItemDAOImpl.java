package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.InventoryItem;
import entities.Reservation;
import entities.ReservationItem;

@Transactional
@Repository
public class ReservationItemDAOImpl implements ReservationItemDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ReservationItem show(Integer id) {
		return em.find(ReservationItem.class, id);
	}

	@Override
	public ReservationItem update(Integer id, ReservationItem r) {
		ReservationItem resItem = em.find(ReservationItem.class, id);
		resItem.setInventoryitems(r.getInventoryitems());
		resItem.setTimeIn(r.getTimeIn());
		resItem.setTimeOut(r.getTimeOut());
		return null;
	}

	@Override
	public ReservationItem create(Integer resId, Integer inventoryId, ReservationItem r) {
		r.setReservations(em.find(Reservation.class, resId));
		r.setInventoryitems(em.find(InventoryItem.class, inventoryId));
		em.persist(r);
		em.flush();
		return r;
	}

	@Override
	public Boolean destroy(Integer id) {
		try {
			em.remove(em.find(ReservationItem.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}



}
