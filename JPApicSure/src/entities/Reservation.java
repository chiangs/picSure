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
	  
	private int userId;
	  
	private Date createdDate;


	@OneToOne
	@JoinColumn(name="userId")
	private User user;

	@OneToMany(mappedBy="reservation")
	private List<ReservationItem> reservation;
	// gets and set

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



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



	public List<ReservationItem> getReservation() {
		return reservation;
	}



	public void setReservation(List<ReservationItem> reservation) {
		this.reservation = reservation;
	}



	// toString
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", userId=" + userId + ", createdDate=" + createdDate + ", user=" + user
				+ ", reservation=" + reservation + "]";
	}
}
