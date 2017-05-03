package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.ReservationItem;

public class ReservationItemTest {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private ReservationItem reservation;
	
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("picSure");
		em = emf.createEntityManager();
		reservation = em.find(ReservationItem.class, 1);		
	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	  @Test
	  public void test_address_map() {
		 assertEquals(1, reservation.getId());
	  }  
}