package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	// fields
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;

	  private String fName;

	  private String lName;

	  private int addressId;

	  private String username;

	  private String password;

	  private String phone;

	  private String email;

	  @OneToOne
	  @JoinColumn(name="addressId")
	  private Address address;

	  @OneToOne(mappedBy="user")
	  private Cart cart;

	  @OneToMany(mappedBy="user")
	  private List<Reservation> rservations;

	  @ManyToMany(mappedBy="user")
	  private List<Store> store;

	// gets and sets
	  public int getId() {
		return id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;

	public String getUserName() {
		return username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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


	// toString
	@Override
	public String toString() {

		return "User Id=" + id + ", fName=" + fName + ", lNAme=" + lName + ", addressId=" + addressId + ", username="
				+ username + ", password=" + password + ", phone=" + phone + ", email=" + email;

	}

}
