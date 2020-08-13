package com.fainberg.usermanagement.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fainberg.usermanagement.dao.UserDAO;
import com.fainberg.usermanagement.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;  
    private UserDAO userDAO;

    
    public void init() {
        userDAO = new UserDAO();
    }

    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        String action = request.getServletPath();

        try {
            switch (action) {
            	case "/list":
            		usersList(request, response);
            		break;
                default:
                    welcome(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        
        
	}
	
	
    private void welcome(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    
    
    private void usersList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException{
    	List <User> allUsersList = userDAO.selectAllUsers();
    	request.setAttribute("allUsersList", allUsersList);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("views/list.jsp");
    	dispatcher.forward(request, response);
    }
    
    
    
    
    
    
    
    
    
    
}
