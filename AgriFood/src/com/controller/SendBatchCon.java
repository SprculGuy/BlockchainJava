package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ProductDao;

/**
 * Servlet implementation class SendBatchCon
 */
@WebServlet("/SendBatchCon")
public class SendBatchCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendBatchCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		String status=request.getParameter("status");
		
		ProductDao dao=new ProductDao();
		
		try {
			if(dao.UpdateBatchStatus(id, status))
			{
				ResultSet rs=dao.SelectBatch();
				if(rs!= null)
				{
					HttpSession session = request.getSession();
					session.setAttribute("listUsers", rs);
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Send Batch successfully');");
					out.println("location='ViewBatch.jsp';");
					out.println("</script>");
				}
				
				else
				{
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Not Send..Try Again...');");
					out.println("location='ViewBatch.jsp';");
					out.println("</script>");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}