package data;

import entities.Equipment;
import entities.Reservation;

public interface ReservationDAO {
	public Reservation show(Integer id);
	public Reservation update(Integer id, Reservation c);
	public Reservation create(Integer id, Reservation c);
	public Reservation destroy(Integer id);
}
