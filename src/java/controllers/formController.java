/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.adminPage.UPLOAD_DIR;
import dao.userDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Users;




/**
 *
 * @author tmh
 */
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*50)
@WebServlet(urlPatterns = {"/logIn", "/Register", "/logOut", "/changeProfile", "/changePassword"})
public class formController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
             try (PrintWriter out = response.getWriter()) {
            String action = request.getServletPath();
            switch (action) {
                case "/logIn":
                    logIn(request, response);
                    break;
                case "/Register":
                    Register(request, response);
                    break;
                case "/logOut":
                    logOut(request, response);
                    break;
                case "/changePassword":
                    changePassword(request, response);
                    break;
                case "/changeProfile":
                    changeProfile(request, response);
                    break;
                default:
                    break;
            }

        }
    }
public static final String UPLOAD_DIR ="";
public String dbFileName="";
    protected void changeProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String userEmail = request.getParameter("userEmail");
        String userAddress = request.getParameter("userAddress");
        Users user = (Users) session.getAttribute("User");
        String userNameID = user.getUserNameID();
        String userPassword = user.getUserPassword();
        
        
        
         Part part = request.getPart("file");
        String fileName = extractFileName(part);
        String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath + "\\img" + File.separator + UPLOAD_DIR;
        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String savePath = uploadPath + File.separator + fileName;
        System.out.println("savePath: " + savePath);
        String sRootPath = new File(savePath).getAbsolutePath();
        System.out.println("sRootPath: " + sRootPath);
        part.write(savePath + File.separator);
//        File fileSaveDir = new File(savePath);
        dbFileName = UPLOAD_DIR + fileName;
        part.write(savePath + File.separator);
        String userAvatar = "img/" + dbFileName;

        Users changeProfilePicture = new Users(user.getUserID(), userName, userNameID, userPassword, userEmail, userAddress, userPhone, userAvatar);
        boolean check = userDB.updateUser(changeProfilePicture);
        if (check) {
            Users newUser=userDB.getUserName(changeProfilePicture.getUserNameID(),changeProfilePicture.getUserPassword());
            session.setAttribute("User",newUser);
            response.sendRedirect("profile.jsp");

        } else {
            session.setAttribute("updateMessage", "Update unsuccessfully");
            response.sendRedirect("profile.jsp");

        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    protected void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("User");
        int userID = user.getUserID();
        String userName = user.getUserName();
        String userNameID = user.getUserNameID();
        String newPassword = request.getParameter("password");
        String newPasswordRepeat = request.getParameter("repeatPassword");
        String userEmail = user.getUserEmail();
        String userAddress = user.getUserAddress();
        String userPhone = user.getUserPhone();
        String userAvatar = user.getUserAvatar();
        if (newPassword.equals(newPasswordRepeat)) {
            Users newUserPassword = new Users(userID, userName, userNameID, newPassword, userEmail, userAddress, userPhone, userAvatar);
            boolean check = userDB.updateUser(newUserPassword);
            if (check) {
                response.sendRedirect("homepage.jsp");
            } else {
                session.setAttribute("passwordError", "Error");
            }
        } else {
            session.setAttribute("passwordError", "Password incorrect");

        }

    }

    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("User", null);
        response.sendRedirect("homepage.jsp");

    }

    protected void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String password = request.getParameter("Password");
        String id = request.getParameter("nameId");
        Users user = userDB.getUserName(id, password);
        if (user == null) {
            String message = "Input incorrect, please try again";
            session.setAttribute("Message", message);
            response.sendRedirect("signIn.jsp");

        } else {
            session.setAttribute("User", user);
            response.sendRedirect("homepage.jsp");
        }

    }

    protected void Register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userName = request.getParameter("fullname");
        String userNameID = request.getParameter("id");
        String userPassword = request.getParameter("password");
        String userAddress = request.getParameter("address");
        String userEmail = request.getParameter("email");
        String userPhone = request.getParameter("phone");

        Users user = new Users(1, userName, userNameID, userPassword, userEmail, userAddress, userPhone, "");
        boolean check = userDB.signUpAccount(user);
        if (check) {
//            session.setAttribute("Message", "Register successully ");
            response.sendRedirect("signIn.jsp");
        } else {
            session.setAttribute("Message", "ID already exist");
            response.sendRedirect("signUp.jsp");

        }
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
            Logger.getLogger(formController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(formController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
