package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Cart;
import entities.CartItem;
import entities.InventoryItem;
import entities.User;

@Transactional
@Repository
public class CartItemDAOImpl implements CartItemDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public CartItem show(Integer id) {
		return em.find(CartItem.class, id);
	}

	@Override
	public CartItem create(Integer userId, Integer inventoryItemId, CartItem c) {
		User u = em.find(User.class, userId);
		c.setCart(em.find(Cart.class, u.getCart()));
		c.setInventoryItem(em.find(InventoryItem.class, inventoryItemId));
		c.setTimeIn(c.getTimeIn());
		c.setTimeOut(c.getTimeOut());
		em.persist(c);
		em.flush();
		return null;
	}

	@Override
	public Boolean destroy(Integer id) {
		try {
			em.remove(em.find(CartItem.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
