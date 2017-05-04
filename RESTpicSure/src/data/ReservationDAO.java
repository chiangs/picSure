package data;

import java.util.List;

import entities.Cart;
import entities.Reservation;

public interface ReservationDAO {
	public List<Reservation> userIndex(Integer id);
	public List<Reservation> storeIndex(Integer id);
	public Reservation show(Integer id);
	public Reservation create(Integer userId, Integer storeId, Cart c);
	public Boolean destroy(Integer id);
}
