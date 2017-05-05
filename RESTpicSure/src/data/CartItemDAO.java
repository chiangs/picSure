package data;

import entities.CartItem;

public interface CartItemDAO {
	public CartItem show(Integer id);
	public CartItem create(Integer cartId, Integer inventoryItemId, CartItem cartItem);
	public Boolean destroy(Integer id);
}
