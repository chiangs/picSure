package entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ReservationItem {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int inventoryItemId;
	
	private int reservationId;
	
	private Date timeIn;
	
	private Date timeOut;
	
	private double total;
	
	@ManyToOne
	@JoinColumn(name="reservationId")
	private Reservation reservations;

	@ManyToOne
	@JoinColumn(name="inventoryItemId")
	private InventoryItem inventoryitems;

	// gets and sets
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

	public Reservation getReservations() {
		return reservations;
	}

	public void setReservations(Reservation reservations) {
		this.reservations = reservations;
	}

	public InventoryItem getInventoryitems() {
		return inventoryitems;
	}

	public void setInventoryitems(InventoryItem inventoryitems) {
		this.inventoryitems = inventoryitems;
	}

	public int getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "ReservationItem [id=" + id + ", inventoryItemId=" + inventoryItemId + ", reservationId=" + reservationId
				+ ", timeIn=" + timeIn + ", timeOut=" + timeOut + ", total=" + total + ", reservations=" + reservations
				+ ", inventoryitems=" + inventoryitems + "]";
	}
	
	
}
