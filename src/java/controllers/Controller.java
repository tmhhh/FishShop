/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ItemDB;
import dao.feedbackDB;
import dao.feedbackReplyDB;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Feedback;
import model.Feedbackreply;
import model.Items;
import model.Users;

/**
 *
 * @author tmh
 */
@WebServlet(urlPatterns = {"/Classify", "/Search", "/selectProduct", "/commentSubmit", "/commentReplySubmit", "/price-range"})

public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        String action = request.getServletPath();
        switch (action) {
            case "/Classify":
                Classify(request, response);
                break;
            case "/Search":
                Search(request, response);
                break;
            case "/selectProduct":
                selectProduct(request, response);
                break;
            case "/commentSubmit":
                commentSubmit(request, response);
                break;
            case "/commentReplySubmit":
                commentReplySubmiṭ̣̣(request, response);
                break;
            case "/price-range":
                priceRange(request, response);
                break;
            default:
                break;
        }

    }

    protected void commentReplySubmiṭ̣̣(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("User") == null) {
            session.setAttribute("Message", "Please log in to submit");
            response.sendRedirect("signIn.jsp");

        } else {
            Date date = new Date();
            String replyComment = request.getParameter("replyComment");
            Users replyUser = (Users) session.getAttribute("User");
            int i = Integer.parseInt(request.getParameter("userCommentID"));
            List<Feedback> listFeedback = (List<Feedback>) session.getAttribute("listFeedback");
            Feedback fb = listFeedback.get(i);
            Items currentItem = (Items) session.getAttribute("Product");
            Feedbackreply reply = new Feedbackreply(replyComment, date, currentItem, fb, replyUser);
            boolean check = feedbackReplyDB.insertReplyComment(reply);
            if (check) {
                List<Feedbackreply> currentListFeedbackReply = feedbackReplyDB.getListFeedbackReply();
                session.setAttribute("listFeedbackReply", currentListFeedbackReply);
                response.sendRedirect("product.jsp");
            }

        }

    }

    protected void commentSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comment = request.getParameter("comment");
        HttpSession session = request.getSession();
        Date date = new Date();
        Users currentUser = (Users) session.getAttribute("User");
        if (currentUser != null) {
            Items currentItem = (Items) session.getAttribute("Product");
            Feedback newFeedback = new Feedback(comment, date, currentUser, currentItem);
            boolean check = feedbackDB.insertComment(newFeedback);
            if (check) {

                List<Feedback> currentListFeedback = feedbackDB.getListFeedback();
                session.setAttribute("listFeedback", feedbackDB.lookUp(currentListFeedback, currentItem.getItemID()));
                response.sendRedirect("product.jsp");

            }

        } else {
            session.setAttribute("Message", "Please log in to submit");
            response.sendRedirect("signIn.jsp");

        }

    }

    protected void selectProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String itemID = request.getParameter("product");
        List<Items> listItems = (List<Items>) session.getAttribute("listItems");
        Items item = new Items();
        item = Items.lookUp(listItems, itemID);
        List<Feedback> listProductFeedback = feedbackDB.lookUp(feedbackDB.getListFeedback(), itemID);
        session.setAttribute("Product", item);
//        session.setAttribute("User", listProductFeedback.get(0).getUserID());
        if (listProductFeedback == null) {
            session.setAttribute("listFeedback", null);

        } else {
            session.setAttribute("listFeedback", listProductFeedback);
        }
        List<Feedbackreply> currentListFeedbackReply = feedbackReplyDB.getListFeedbackReply();
        session.setAttribute("listFeedbackReply", currentListFeedbackReply);
        session.setAttribute("itemcode", itemID);

        response.sendRedirect("product.jsp");
    }

    protected void Search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        String itemName = request.getParameter("search-input");
        List<Items> listItems = ItemDB.Search(itemName);
        session.setAttribute("listItems", listItems);
        response.sendRedirect("homepage.jsp");

    }

    protected void Classify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        String itemName = request.getParameter("cate");
        List<Items> listItems = ItemDB.Search(itemName);
        session.setAttribute("listItems", listItems);
        response.sendRedirect("homepage.jsp");
    }

    protected void priceRange(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        String price = (String) request.getParameter("priceRange");
        int start = 0, end = 0;
        switch (price) {
            case "10":
                end = 10;
                break;
            case "10-25":
                start = 10;
                end = 25;
                break;
            case "25-50":
                start = 25;
                end = 50;
                break;
            case "50-100":
                start = 50;
                end = 100;
                break;
            case "100":
                start = 100;
                end = 10000;
                break;
        }
        List<Items> listItems = ItemDB.SearchByPrice(start, end);
        session.setAttribute("listItems", listItems);
        response.sendRedirect("homepage.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
