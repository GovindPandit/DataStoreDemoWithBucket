package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet 
{
	private Datastore datastore;
	private KeyFactory factory;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			datastore=DatastoreOptions.getDefaultInstance().getService();
			factory = datastore.newKeyFactory().setKind("users");
		
			Key key=factory.newKey(Long.parseLong(req.getParameter("userKey")));
			Entity entity=datastore.get(key);
		
			User u=new User();
			u.setUserKey(entity.getKey().getId());
			u.setUsername(entity.getString("username"));
			u.setEmail(entity.getString("email"));
			u.setPassword(entity.getString("password"));
			
			HttpSession hs=req.getSession();
			hs.setAttribute("user", u);
			resp.sendRedirect("edit.jsp");
			
//			PrintWriter out=resp.getWriter();
//			out.println(u.getUsername());
//			out.println(u.getEmail());
		}
		catch(Exception e)
		{
			PrintWriter out=resp.getWriter();
			out.println(e+"");
		}
	}
}
