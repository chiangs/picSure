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
	public CartItem create(Integer userId, Integer inventoryItemId, CartItem cartItem) {
		User u = em.find(User.class, userId);
		System.out.println(u.getCart().getId());
		cartItem.setCart(em.find(Cart.class, u.getCart().getId()));
		cartItem.setInventoryItem(em.find(InventoryItem.class, inventoryItemId));
		em.persist(cartItem);
		em.flush();
		return cartItem;
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
