/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CodeDB;
import dao.ItemDB;
import dao.orderedItemsDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Items;
import model.Itemsorder;
import model.ItemsorderPK;
import model.Promocodes;
import model.Users;
import model.cartItem;

/**
 *
 * @author LEQUANGHUY
 */
@WebServlet(urlPatterns = {"/add-to-cart", "/remove-item", "/update-item", "/buy-now", "/checkout", "/Usure", "/promo"})
public class shoppingCart extends HttpServlet {

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
            throws ServletException, IOException, MessagingException {
        String action = request.getServletPath();
        switch (action) {
            case "/add-to-cart":
                addToCart(request, response);
                break;
            case "/buy-now":
                addToCart(request, response);
                break;
            case "/remove-item":
                removeItem(request, response);
                break;
            case "/update-item":
                updateItem(request, response);
                break;
            case "/Usure":
                Usure(request, response);
                break;
            case "/promo":
                promo(request, response);
                break;
            case "/checkout":
                checkout(request, response);
                break;
            default:
                break;
        }
        request.setAttribute("action", action);

    }

    protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Items> listItems = (List<Items>) session.getAttribute("listItems");
        String productCode = request.getParameter("code");
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            cart.addProduct(Cart.lookUp(listItems, productCode));
            session.setAttribute("cart", cart);
        } else {
            cartItem item = Cart.lookUpItem(cart.getList(), productCode);
            if (item != null) {
                item.setQuantity(item.getQuantity() + 1);

            } else {
                cart.addProduct(Cart.lookUp(listItems, productCode));
            }
        }
        String action = (String) request.getAttribute("action");
        if ("/add-to-cart".equals(action)) {
            response.sendRedirect("homepage.jsp");
        } else {
            response.sendRedirect("shoppingCart.jsp");
        }

    }

    protected void removeItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productCode = request.getParameter("code");
        Cart cart = (Cart) session.getAttribute("cart");
        cart.removeProduct(cart.lookUpItem(cart.getList(), productCode));

    }

    protected void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String productCode = request.getParameter("code");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Cart cart = (Cart) session.getAttribute("cart");
        cart.updateQuantity(cart.lookUpItem(cart.getList(), productCode), quantity);
        response.sendRedirect("shoppingCart.jsp");
    }

    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        HttpSession session = request.getSession();
        Date date = new Date();

        Cart cart = (Cart) session.getAttribute("cart");
        Users user = (Users) session.getAttribute("User");
        int code=orderedItemsDB.findCode();
        if (user == null) {
            response.sendRedirect("signIn.jsp");
        } else {
            for (cartItem cartItem : cart.getList()) {
                Items item = cartItem.getItem();
                ItemsorderPK itemPK = new ItemsorderPK(code+1, user.getUserID(), item.getItemID());
                int quantity = cartItem.getQuantity();
                Itemsorder itemsOrder = new Itemsorder(itemPK, quantity, date, item, user);
                boolean check = orderedItemsDB.insertOderedItems(itemsOrder);
                if (!check) {

                }

            }
            //            sendMail(user.getUserEmail(), "abcdef");
            session.setAttribute("cart", null);
            response.sendRedirect("thank.jsp");

        }
    }

    public static void sendMail(String recepient, String promoCode) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        String myAccountEmail = "7colorsfishop@gmail.com";//change accordingly
        String password = "huytuhoangkhang123456";
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Your order confirmation");
            message.setContent(
                    "<p>The products are being prepared and delivered soon. Here is your promo code " + "<h2 style:color:#ec7532;font-weight: bold;>" + promoCode + "</h2>" + " for your next dropping in at our Fish shop.</p>",
                    "text/html");
//            message.setText("The products are being prepared and delivered soon. Here is your promo code " + "<h2>" + promoCode + "</h2>" + " for your next dropping in at our Fish shop");
            Transport.send(message);

        } catch (MessagingException ex) {
        }
    }

    protected void Usure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String productCode = request.getParameter("code");
        String check = request.getParameter(productCode);
        cartItem item = cart.lookUpItem(cart.getList(), productCode);
        if ("Nope".equals(check)) {
            item.setChecked(true);
        } else {
            item.setChecked(false);
        }
        response.sendRedirect("shoppingCart.jsp");

    }

    protected void promo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String promoCode = request.getParameter("promo");
        Promocodes code = CodeDB.checkCode(promoCode);
        if (code != null) {
            cart.setPromoValue(code.getCodeValue());
            session.setAttribute("promoError", null);
        } else {
            session.setAttribute("promoError", "Wrong Code");
        }
        response.sendRedirect("shoppingCart.jsp");

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
        } catch (MessagingException ex) {
            Logger.getLogger(shoppingCart.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (MessagingException ex) {
            Logger.getLogger(shoppingCart.class.getName()).log(Level.SEVERE, null, ex);
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

