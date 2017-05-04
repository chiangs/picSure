package entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Reservation {
	
	// fields 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	  
	private Date createdDate;

	@OneToOne
	@JoinColumn(name="userId")
	private User user;

	@OneToMany(mappedBy="reservations")
	private List<ReservationItem> reservationItems;


	// gets and set
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ReservationItem> getReservationItems() {
		return reservationItems;
	}

	public void setReservationItems(List<ReservationItem> reservationItems) {
		this.reservationItems = reservationItems;
	}

	public int getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "Reservation [id=" + id + ","+ ", createdDate=" + createdDate + ", user=" + user
				+ ", reservation=" + reservationItems + "]";
	}
}
