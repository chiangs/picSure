package data;

import entities.ReservationItem;

public interface ReservationItemDAO {
	public ReservationItem show(Integer id);
	public ReservationItem update(Integer id, ReservationItem c);
	public ReservationItem create(Integer id, ReservationItem c);
	public ReservationItem destroy(Integer id);
}
