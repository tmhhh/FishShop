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
 * @author tmh
 */
public class feedbackReplyDB {

    public static boolean insertReplyComment(Feedbackreply reply) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        try {
            eTrans.begin();
            entityManager.persist(reply);
            eTrans.commit();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    public static List<Feedbackreply> getListFeedbackReply() {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        String qString = "SELECT r FROM Feedbackreply r";
        TypedQuery<Feedbackreply> q = entityManager.createQuery(qString, Feedbackreply.class);
        try {
            List<Feedbackreply> Feedbackreply = q.getResultList();
            return Feedbackreply;

        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static void deleteFeedbackreply(Feedbackreply feedbackreplyID) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        eTrans.begin();

        String qString = "DELETE FROM Feedbackreply r WHERE r.feedbackReplyID =:feedbackreplyID ";
        Query q = entityManager.createQuery(qString);
        q.setParameter("feedbackreplyID", feedbackreplyID.getFeedbackReplyID());
        q.executeUpdate();
        eTrans.commit();
        entityManager.close();
    }

    public static List<Feedbackreply> getListByItemID(Items itemID) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        String qString = "SELECT fb FROM Feedbackreply fb WHERE fb.itemID=:itemID ";
        TypedQuery<Feedbackreply> q = entityManager.createQuery(qString, Feedbackreply.class);
        q.setParameter("itemID", itemID);
        try {
            List<Feedbackreply> reply = q.getResultList();
            return reply;

        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }
}
