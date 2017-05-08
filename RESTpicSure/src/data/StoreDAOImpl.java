package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Address;
import entities.Inventory;
import entities.InventoryItem;
import entities.Store;
import entities.User;

@Transactional
@Repository
public class StoreDAOImpl implements StoreDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private InventoryDAO inventoryDAO;

	@Override
	public Store show(Integer id) {
		return em.find(Store.class, id);
	}
	
	@Override
	public List<Store> indexEquipment(Integer equipmentId) {
		String q = "SELECT s FROM Store s JOIN FETCH Inventory i ON s.id = i.store.id JOIN FETCH InventoryItem ii ON i.id = ii.inventory.id JOIN FETCH Equipment e ON ii.equipment.id = e.id WHERE e.id = :id and s.active = true";
		return em.createQuery(q, Store.class).setParameter("id", equipmentId).getResultList();
	}
	
	@Override
	public List<Store> index() {
		String q = "SELECT s FROM Store s WHERE s.active = true";
		return em.createQuery(q, Store.class).getResultList();
	}
	
	@Override 
	public Inventory showInventory(Integer storeId) {
		String q = "SELECT i FROM Inventory i WHERE i.store.id = :id";
		return em.createQuery(q, Inventory.class).setParameter("id", storeId).getSingleResult();
	}

	@Override
	public Store update(Integer id, Store s) {
		Store store = em.find(Store.class, id);
		store.setEmail(s.getEmail());
		store.setName(s.getName());
		store.setPhone(s.getPhone());
		store.setInventory(s.getInventory());
		store.setActive(s.getActive());
		if (s.getAddress().getId() > 0) {
			store.setAddress(em.find(Address.class, s.getAddress().getId()));
		} else {
			store.setAddress(s.getAddress());
		}
		em.persist(store);
		em.flush();
		return store;
	}

	@Override
	public Store create(Integer id, Store s) {
		List<User> users = new ArrayList<>();
		s.setUsers(users);
		s.getUsers().add(em.find(User.class, id));

		em.persist(s);
		em.flush();

		List<InventoryItem> items = new ArrayList<>();
		Inventory inventory = new Inventory();
		inventory.setInventoryItems(items);

		inventoryDAO.create(s.getId(), inventory);
		return s;
	}

	@Override
	public Boolean destroy(Integer id) {
//		String q = "SELECT s FROM Store s JOIN FETCH Lister l ON s.id = l.store.id JOIN FETCH User u ON u.id = l.user.id WHERE u.id = :id";
		String q = "SELECT s FROM Store s JOIN FETCH s.users WHERE s.id = :id";
		Store s = em.createQuery(q, Store.class).setParameter("id", id).getSingleResult();
		try {
			System.out.println(s.getUsers().get(0).getfName());
			s.setActive(false);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
}
