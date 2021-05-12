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

import model.Items;

/**
 *
 * @author pc
 */
public class ItemDB {

    public static List<Items> getAllItems() throws Exception {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        String qString = "SELECT item FROM Items item ";
        TypedQuery<Items> q = entityManager.createQuery(qString, Items.class);

        try {
            List<Items> listItems = (List<Items>) q.getResultList();
            return listItems;

        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();

        }

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

    public static void deleteItem(Items item) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        eTrans.begin();
        entityManager.remove(item);
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

    public static List<Items> Search(String itemName) throws Exception {

        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        String qString = "SELECT item FROM Items item WHERE "
                //                + " item.itemName = :itemName"  ;
                + "item.itemName LIKE" + " CONCAT('%',:itemName,'%') ";
        TypedQuery<Items> q = entityManager.createQuery(qString, Items.class);
        q.setParameter("itemName", itemName);
        try {
            List<Items> listItems = (List<Items>) q.getResultList();
            return listItems;

        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();

        }
    }

    public static List<Items> SearchByPrice(int start, int end) throws Exception {

        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        String qString = "";

        qString = "SELECT item FROM Items item WHERE "
                + "item.itemPrice >= :start AND item.itemPrice <= :end ";

        TypedQuery<Items> q = entityManager.createQuery(qString, Items.class);
        q.setParameter("start", start);
        q.setParameter("end", end);
        try {
            List<Items> listItems = (List<Items>) q.getResultList();
            return listItems;
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();

        }
    }

    public static Items lookUpItem(String code) throws Exception {
        List<Items> listItems = ItemDB.getAllItems();
        for (Items item : listItems) {
            if (code.equals(item.getItemID())) {
                return item;
            }

        }
        return null;
    }
}
