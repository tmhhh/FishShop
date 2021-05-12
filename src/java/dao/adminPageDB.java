/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Feedback;
import model.Feedbackreply;
import model.Items;

/**
 *
 * @author pc
 */
public class adminPageDB {

    public static List<Items> getAllItems() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        String sql = "SELECT item  FROM Items item";
        Query query = entityManager.createQuery(sql);
        List<Items> listItems = (List<Items>) query.getResultList();
        entityManager.close();
        return listItems;

    }

    public static void addItem(Items item) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        eTrans.begin();
        entityManager.persist(item);
        eTrans.commit();
        entityManager.close();
    }

//    public static void deleteItem(Items item, List<Feedbackreply> listReply, List<Feedback> listFB) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
//        EntityManager entityManager = factory.createEntityManager();
//        EntityTransaction eTrans = entityManager.getTransaction();
//        eTrans.begin();
//        for (int i = 0; i < listReply.size(); i++) {
//            entityManager.remove(listReply.get(i));
//
//        }
//        for (int i = 0; i < listFB.size(); i++) {
//            entityManager.remove(listFB.get(i));
//
//        }
//
//        entityManager.remove(item);
//        eTrans.commit();
//        entityManager.close();
//    }
    public static void deleteItem(Items item) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        eTrans.begin();    
        String qString = "DELETE FROM Items r WHERE r.itemID =:itemID ";
        Query  q = entityManager.createQuery(qString);

        q.setParameter("itemID", item.getItemID());
        q.executeUpdate();
        eTrans.commit();
        entityManager.close();
    }

    public static void updateItem(Items item) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        eTrans.begin();
        entityManager.merge(item);
        eTrans.commit();
        entityManager.close();
    }
}
