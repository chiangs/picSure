	package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Store {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	  
	private String name;
	  	  
	private String phone;
	  
	private String email;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="addressId")
	private Address address;
	  
	@OneToOne(mappedBy="store")
	private Inventory inventory; 
	  
	@ManyToMany
	@JoinTable(name = "lister", joinColumns = @JoinColumn(name = "storeId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	private List<User> users;

	// gets and sets
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	// fields
	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", address=" + address
				+ ", inventory=" + inventory + ", users=" + users + "]";
	}
}
