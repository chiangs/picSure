package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Inventory;



public class InventoryTest {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private Inventory inventory;
	
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("picSure");
		em = emf.createEntityManager();
		inventory = em.find(Inventory.class, 1);		
	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	@Test
    public void test() {
      boolean pass = true;
      assertEquals(pass, true);
    }
	
	@Test
	public void test_cart_map() {
		assertEquals(1, inventory.getId());
	}
}

