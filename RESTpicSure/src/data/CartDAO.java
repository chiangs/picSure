package data;

import entities.Cart;

public interface CartDAO {
	public Cart show(Integer id);
	public Cart update(Integer id, Cart c);
	public Cart create(Integer id, Cart c);
}
