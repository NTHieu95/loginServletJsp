package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;
/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        dao = new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = dao.getUserByName(name);
		System.out.println(user);
		if (user == null) {
			request.setAttribute("message", "Wrong Name");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else if (user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			request.setAttribute("currentUser", user);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "Invalid Access");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}

}
