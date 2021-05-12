/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Items;
import model.Promocodes;

/**
 *
 * @author LEQUANGHUY
 */
public class CodeDB {

    public static Promocodes checkCode(String code) {

        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        String qString = "SELECT p FROM Promocodes p WHERE p.code = :code";

        TypedQuery<Promocodes> q = entityManager.createQuery(qString, Promocodes.class);
        q.setParameter("code", code);
        try {
            Promocodes promoCode = q.getSingleResult();
            return promoCode;

        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();

        }
    }
    public static Promocodes getRandomCode(){
           EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        String qString = "SELECT promo FROM Promocodes promo";
        TypedQuery<Promocodes> q = entityManager.createQuery(qString, Promocodes.class);
        try {
           List<Promocodes> promoCodes = q.getResultList();
           if(promoCodes == null){
           int n = promoCodes.size();
            Random rd = new Random();
            int code = rd.nextInt(n);
            return promoCodes.get(code);
           }else
               return null;
            

        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();

        }

    }
}
