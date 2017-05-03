package data;

import entities.CartItem;

public interface CartItemDAO {
	public CartItem show(Integer id);
	public CartItem update(Integer id, CartItem c);
	public CartItem create(Integer cartId, Integer inventoryItemId, CartItem c);
	public Boolean destroy(Integer id);
}
