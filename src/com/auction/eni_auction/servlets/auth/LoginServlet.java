package com.auction.eni_auction.servlets.auth;

import com.auction.eni_auction.bll.BusinessException;
import com.auction.eni_auction.bll.UtilisateurManager;
import com.auction.eni_auction.bo.Utilisateur;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String alert = request.getParameter("alert");

        if (alert != null) {
            request.setAttribute("sucess", "Vous êtes bien inscrit, vous pouvez à présent vous connecter");
        }
        this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Utilisateur user = null;
        try {
        	user = UtilisateurManager.getInstance().getUserByCredentials(email, password);
        	session.setAttribute("user", user);
        	response.sendRedirect( request.getContextPath() + "/");
        } catch (BusinessException e) {
        	e.printStackTrace();
        	request.setAttribute("error", e.getErrorList().get(0));
        	this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
        }
    }

    public void destroy() {
    }
}