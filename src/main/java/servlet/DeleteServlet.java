package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet
{
	private Datastore datastore;
	private KeyFactory factory;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			datastore=DatastoreOptions.getDefaultInstance().getService();
			factory=datastore.newKeyFactory().setKind("users");
		
			Key key=factory.newKey(Long.parseLong(req.getParameter("userKey")));
			datastore.delete(key);
			resp.sendRedirect("/DisplayUsers");
		}
		catch (Exception e) 
		{
			System.out.println(e+"");
		}
		
		
		
		
	}
}
