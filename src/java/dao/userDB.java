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
import model.Users;

/**
 *
 * @author Administrator
 */
public class userDB {

    public static List<Users> getAllUsers() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        String sql = "SELECT user  FROM Users user";
        Query query = entityManager.createQuery(sql);
        List<Users> listUsers = (List<Users>) query.getResultList();
        entityManager.close();

        return listUsers;

    }

    public static void addUser(Users user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        eTrans.begin();
        entityManager.persist(user);
        eTrans.commit();
        entityManager.close();
    }

    public static void deleteUser(Users user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        eTrans.begin();
        entityManager.remove(user);
        eTrans.commit();
        entityManager.close();
    }

    public static boolean updateUser(Users user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        try {
            eTrans.begin();
            entityManager.merge(user);
            eTrans.commit();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();

        }

    }

    public static long accountLogIn(String id, String password) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        String qString = "SELECT COUNT(u) FROM Users u WHERE u.userNameID = :id AND u.userPassword =:password";
//        String query="Select * from Users u Where u.userNameID=':admin' AND u.userPassword ='1'";
        Query q = entityManager.createQuery(qString, long.class);
//        TypedQuery<int> q = entityManager.createQuery(qString,int.cl);
        q.setParameter("id", id);
        q.setParameter("password", password);
        try {
//              List<Users> user = (List<Users>)q.getResultList();
            long user = (long) q.getSingleResult();
            return user;
//            int data = q.getFirstResult();
//            
//            if (data != 0) {
//                return data;
//            } else {
//                return 0;
//            }

        } catch (NoResultException e) {
            return 4;
        } finally {
            entityManager.close();

        }
    }

    public static Users getUserName(String id, String password) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = em.createEntityManager();
        String qString = "SELECT u FROM Users u WHERE u.userNameID = :id AND u.userPassword =:password";

        TypedQuery<Users> q = entityManager.createQuery(qString, Users.class);
        q.setParameter("id", id);
        q.setParameter("password", password);
        try {
            Users user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();

        }

    }

    public static boolean signUpAccount(Users user) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction eTrans = entityManager.getTransaction();
        String userNameID = user.getUserNameID();
        String qString = "SELECT COUNT(u) FROM Users u WHERE u.userNameID = :usernameID ";
        Query q = entityManager.createQuery(qString, long.class);
        q.setParameter("usernameID", userNameID);
        long result = (long) q.getSingleResult();
        if (result != 0) {
            return false;
        } else {
            try {
                eTrans.begin();
                entityManager.persist(user);
                eTrans.commit();
                return true;
            } catch (NoResultException e) {
                return false;
            } finally {
                entityManager.close();
            }
        }

    }
}
//    public static boolean updatePasswordByNameID(String userNameID, String userNewPassword) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("item");
//        EntityManager entityManager = factory.createEntityManager();
//        String qString = "UPDATE USERS u SET u.userPassword='2' WHERE u.userNameID='admin' AND u.userID=1";
//        Query q = entityManager.createQuery(qString);
////        q.setParameter("userNewPassword", userNewPassword);
////        q.setParameter("userNameID", userNameID);
//        try {
//            int check = q.executeUpdate();
//            if (check != 0) {
//                return true;
//            }
//            else
//                return false;
//        } catch (NoResultException e) {
//            return false;
//        } finally {
//            entityManager.close();
//        }
//
//    }
//}
//        EntityManagerFactory em = Persistence.createEntityManagerFactory("item");
//        EntityManager entityManager = em.createEntityManager();
//        String qString = "INSERT INTO USERS  (userName,userNameID,userPassword,date_created,userEmail,userAddress,userPhone) VALUES (:userName,:userNameID,:userPassword,:date_created,:userEmail,:userAddress,:userPhone) ";
//
//        Query q = entityManager.createQuery(qString);
//        q.setParameter("userName", user.getUserName());
//        q.setParameter("userNameID", user.getUserID());
//        q.setParameter("date_created", " ");
//        q.setParameter("userPassword", user.getUserPassword());
//        q.setParameter("userEmail", user.getUserEmail());
//        q.setParameter("userAddress", user.getUserAddress());
//        q.setParameter("userPhone", user.getUserPhone());
//
//        try {
//            int check = q.executeUpdate();
//            return check;
//        } catch (NoResultException e) {
//            return 0;
//        } finally {
//            entityManager.close();
//
//        }
