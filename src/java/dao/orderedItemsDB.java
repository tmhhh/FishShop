/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Cart;
import model.Feedback;
import model.Itemsorder;
import model.Users;

/**
 *
 * @author tmh
 */
public class orderedItemsDB {

//    public static boolean insertOderedItems(Itemsorder item) {
//        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
//        EntityManager entityManager = em.createEntityManager();
//        EntityTransaction eTrans = entityManager.getTransaction();
//        eTrans.begin();
//        String qString = "INSERT INTO itemsOrder i (i.orderID,i.dateOrder,i.userID,i.itemID) VALUES (?,?, ?,? )  ";
//        Query q = entityManager.createNativeQuery(qString);
//        q.setParameter(1, 1);
//        q.setParameter(2, date);
//        q.setParameter(3, user.getUserID());
//        q.setParameter(4, cart.getList().get(0).getItem().getItemID());
//        int result=q.getMaxResults();
//        eTrans.commit();
//        entityManager.close();
//return true;
//    }
    public static boolean insertOderedItems(Itemsorder item) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        try {
            eTrans.begin();
            entityManager.persist(item);
            eTrans.commit();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();
        }
    }
    public static List<Itemsorder> getListOrder()
    {
         EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        String sql = "SELECT o FROM Itemsorder o";
        Query query = entityManager.createQuery(sql);
        try {
            List<Itemsorder> result = query.getResultList();
            return result;

        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }
    public static int findCode() {
     EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        String sql = "SELECT count(o) FROM Itemsorder o";
        Query query = entityManager.createQuery(sql);
        try {
            long result = (long)query.getSingleResult();
            int findResult=(int)result;
            return findResult;

        } catch (NoResultException e) {
            return 0;
        } finally {
            entityManager.close();
        }
    }
}
