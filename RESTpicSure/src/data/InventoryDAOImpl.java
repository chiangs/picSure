package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.CartItem;
import entities.Inventory;
import entities.Store;

@Transactional
@Repository
public class InventoryDAOImpl implements InventoryDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Inventory show(Integer id) {
		return em.find(Inventory.class, id);
	}

	@Override
	public Inventory update(Integer id, Inventory i) {
		Inventory inventory = em.find(Inventory.class, id);
		inventory.setIventoryItems(i.getIventoryItems());
		return inventory;
	}

	@Override
	public Inventory create(Integer id, Inventory i) {
		Store store = em.find(Store.class, id);
		i.setStore(store);
		em.persist(i);
		em.flush();
		return i;
	}

	@Override
	public Boolean destroy(Integer id) {
		try {
			em.remove(em.find(Inventory.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
