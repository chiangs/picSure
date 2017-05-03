package entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ReservationItem {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	
	
	private int inventoryItemId;
	
	private int reservationId;
	
	private Date timeIn;
	
	private Date timeOut;
	
	private double total;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(int inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Date getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ReservationItems id=" + id + ", inventoryItemId=" + inventoryItemId + ", reservationId="
				+ reservationId + ", timeIn=" + timeIn + ", timeOut=" + timeOut + ", total=" + total;
	}
	
	
	
	
	
}
