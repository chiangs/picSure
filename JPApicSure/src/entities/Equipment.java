package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipment {
	 
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;

	  private String model;
	  
	  private String type;
	  
	  private String description;
	  
	  private String image;
	  
	  private double rate;
	  
	  private String make;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	@Override
	public String toString() {
		return "Equipment id=" + id + ", model=" + model + ", type=" + type + ", description=" + description
				+ ", image=" + image + ", rate=" + rate + ", make=" + make;
	}
	  
	  
	  
	  
	  
	  
	  
	  
}
