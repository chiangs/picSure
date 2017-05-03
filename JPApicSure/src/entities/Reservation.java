package entities;

import java.sql.Date;

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

	// gets and sets
	@OneToOne
	@JoinColumn(name="userId")
	private User user;

	@OneToMany(mappedBy="reservation")
	private Reservation reservation;

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

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", userId=" + userId + ", createdDate=" + createdDate + ", user=" + user
				+ ", reservation=" + reservation + "]";
	}
}
