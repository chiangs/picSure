package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.CartItem;
import entities.Inventory;
import entities.Reservation;
import entities.Store;

@Transactional
@Repository
public class InventoryDAOImpl implements InventoryDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Inventory show(Integer id) {
		String q = "SELECT i FROM Inventory i WHERE i.store.id = :id";
		return em.createQuery(q, Inventory.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Inventory create(Integer storeId, Inventory i) {
		Store store = em.find(Store.class, storeId);
		i.setStore(store);
		em.persist(i);
		em.flush();
		return i;
	}
}
