package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Store;

public class StoreTest {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private Store store;
	
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("picSure");
		em = emf.createEntityManager();
		store = em.find(Store.class, 1);		
	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	  @Test
	  public void test_store_map() {
		 assertEquals("Bob", store.getName());
	  }
	  

}
