package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	private int inventoryId;


	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	@OneToMany(mappedBy="cart")
	private List<CartItem> cartItems;
	
	


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + "," + ", " + ", user=" + user
				+ "]";
	}	
}
