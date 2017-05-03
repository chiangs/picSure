package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Cart;
import entities.CartItem;
import entities.InventoryItem;

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
	public CartItem update(Integer id, CartItem c) {
		CartItem item = em.find(CartItem.class, id);
		item.setTimeIn(c.getTimeIn());
		item.setTimeOut(c.getTimeOut());
		return item;
	}

	@Override
	public CartItem create(Integer cartId, Integer inventoryItemId, CartItem c) {
		c.setCart(em.find(Cart.class, cartId));
		c.setInventoryItem(c.getInventoryItem());
		c.setTimeIn(c.getTimeIn());
		c.setTimeOut(c.getTimeOut());
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
