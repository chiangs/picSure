package data;

import entities.Cart;

public interface CartDAO {
	public Cart show(Integer id);
	public Cart create(Integer id);
}
