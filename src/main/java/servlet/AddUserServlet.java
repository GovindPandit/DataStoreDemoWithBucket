package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;


@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet 
{
	private Datastore datastore;
	private KeyFactory factory;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		datastore=DatastoreOptions.getDefaultInstance().getService();
		factory=datastore.newKeyFactory().setKind("users");
		
		Key taskKey=datastore.add(FullEntity.newBuilder(factory.newKey()).build()).getKey();
		Entity entity=Entity.newBuilder(taskKey)
				.set("username",req.getParameter("username"))
				.set("email",req.getParameter("email"))
				.set("password",req.getParameter("password"))
				.build();
		
		datastore.put(entity);
		resp.sendRedirect("DisplayUsers");
		
		
	}
}
