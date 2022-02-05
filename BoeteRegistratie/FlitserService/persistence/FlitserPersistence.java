package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class FlitserPersistence {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Flitser");
    protected EntityManager manager;
    protected EntityTransaction transaction;
 
    public FlitserPersistence(){
    	this.manager = ENTITY_MANAGER_FACTORY.createEntityManager();
    	this.transaction = manager.getTransaction();
    	this.transaction.begin();
    }

    public void close(){
    	transaction.commit();
    	manager.close();
    }
    
    public void endFactory(){
		ENTITY_MANAGER_FACTORY.close();
    }   
    
    public void createFlitsRegistratie(FlitsRegistratie flitsRegistratie) {
    	manager.persist(flitsRegistratie);
    }
}
