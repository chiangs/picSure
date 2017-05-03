package data;

import java.util.List;

import entities.Reservation;

public interface ReservationDAO {
	public List<Reservation> userIndex(Integer id);
	public List<Reservation> storeIndex(Integer id);
	public Reservation show(Integer id);
	public Reservation update(Integer id, Reservation r);
	public Reservation create(Integer userId, Reservation r);
	public Boolean destroy(Integer id);
}
