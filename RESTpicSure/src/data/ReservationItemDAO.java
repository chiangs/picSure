package data;

import java.util.List;

import entities.ReservationItem;

public interface ReservationItemDAO {
	public ReservationItem show(Integer id);
	public List<ReservationItem> index(Integer id);
	public ReservationItem update(Integer id, ReservationItem r);
	public ReservationItem create(Integer resId, Integer inventoryId, ReservationItem r);
	public Boolean destroy(Integer id);
}
