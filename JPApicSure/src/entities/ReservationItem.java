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

	private Date timeIn;
	
	private Date timeOut;
	
	
	@ManyToOne
	@JoinColumn(name="reservationId")
	private Reservation reservations;

	@ManyToOne
	@JoinColumn(name="inventoryItemId")
	private InventoryItem inventoryitems;

	
	//gets and sets
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

}
