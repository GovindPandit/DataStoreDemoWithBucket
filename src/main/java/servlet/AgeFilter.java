package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

@WebServlet("/AgeFilter")
public class AgeFilter extends HttpServlet 
{
	private Datastore datastore;
	private KeyFactory factory;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		datastore=DatastoreOptions.getDefaultInstance().getService();
		factory=datastore.newKeyFactory().setKind("users");
		
		Query<Entity> query = Query.newEntityQueryBuilder()
			    .setKind("users")
			    .setFilter(CompositeFilter.and(
			         PropertyFilter.ge("age", 35)))
			    .setOrderBy(OrderBy.desc("age"))
			    .build();
		
		QueryResults<Entity> tasks = datastore.run(query);
		
		List<User> users=new ArrayList<>();
		
		PrintWriter out=resp.getWriter();
		while(tasks.hasNext())
		{
			Entity entity=tasks.next();
			
			User u=new User();
			u.setUserKey(entity.getKey().getId());
			u.setUsername(entity.getString("username"));
			u.setEmail(entity.getString("email"));
			u.setAge(entity.getLong("age"));
			u.setPassword(entity.getString("password"));
		
			users.add(u);
		}
		
		HttpSession hs=req.getSession();
		hs.setAttribute("users", users);
		resp.sendRedirect("users.jsp");
	}
}
