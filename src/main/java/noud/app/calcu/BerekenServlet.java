package noud.app.calcu;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/BerekenServlet.do")
public class BerekenServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer een = Integer.parseInt(req.getParameter("een"));
		Integer twee = Integer.parseInt(req.getParameter("twee"));
		String methode = req.getParameter("methode");
		
		Integer output = 0;
		
		System.out.println(een);
		System.out.println(twee);
		System.out.println(methode);
		
		switch(methode) {
			case "+":
				output = een + twee;
				break;
			case "-":
				output = een - twee;
				break;
			case "/":
				output = een / twee;
				break;
			case "*":
				output = een * twee;
				break;
			default:
				output = null;
				
				
		}
		
		System.out.println(output);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("  <title>Dynamic Example</title>");
		out.println("  <title>Dynamic Example</title>");
		out.println("  <body>");
		out.println("    <h2>Calculator</h2>");
		out.println("    <p>" + output + "</p>");
		out.println("  </body>");
		out.println("</html>");
		
	}

}


