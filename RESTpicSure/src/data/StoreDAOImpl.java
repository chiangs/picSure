package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Inventory;
import entities.Store;
import entities.User;

@Transactional
@Repository
public class StoreDAOImpl implements StoreDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Store show(Integer id) {
		return em.find(Store.class, id);
	}

	@Override
	public Store update(Integer id, Store s) {
		Store store = em.find(Store.class, id);
		store.setEmail(s.getEmail());
		store.setName(s.getName());
		store.setPhone(s.getPhone());
		store.setInventory(s.getInventory());
		
		em.persist(store);
		em.flush();
		return store;
	}

	@Override
	public Store create(Integer id, Store s) {
		Inventory inventory = new Inventory();
		em.persist(inventory);
		em.flush();
		
		List<User> users = new ArrayList<>();
		
		s.setInventory(inventory);
		s.setUsers(users);
		
		s.getUsers().add(em.find(User.class, id));
		
		em.persist(s);
		em.flush();
		return s;
	}

	@Override
	public Boolean destroy(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
