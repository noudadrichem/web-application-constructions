package noud.app.calcu;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/CalcuServlet.do")
public class CalcuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("username");
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("  <title>Dynamic Example</title>");
		out.println("  <title>Dynamic Example</title>");
		out.println("  <body>");
		out.println("    <h2>Calculator</h2>");
		out.println("    <form action='BerekenServlet.do'>");
		out.println("    	<input name='een' type='number' id='een'/>");
		out.println("    	<input name='twee' type='number' id='twee' />");
		out.println("    	<select name='methode'>");
		out.println("    		<option selected>Kies methode</option>");
		out.println("    		<option value='+'>+</option>");
		out.println("    		<option value='-'>-</option>");
		out.println("    		<option value='/'>/</option>");
		out.println("    		<option value='*'>*</option>");
		out.println("    	</select>");
		out.println("    	<button type='submit'>bereken</button>");
		out.println("    </form>");
		out.println("  </body>");
		out.println("</html>");
		
	}

}


