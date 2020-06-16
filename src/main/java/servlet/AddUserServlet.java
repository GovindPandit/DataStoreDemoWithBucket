package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Transaction;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;


@WebServlet("/AddUserServlet")
@MultipartConfig(maxFileSize = 999999999L)
public class AddUserServlet extends HttpServlet 
{
	private Datastore datastore;
	private KeyFactory factory;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Transaction tx=null;
		
		try
		{
			Part part=req.getPart("image");
			InputStream is=part.getInputStream();
			byte[] bytes = IOUtils.toByteArray(is);
			
		    
			datastore=DatastoreOptions.getDefaultInstance().getService();
			factory=datastore.newKeyFactory().setKind("users");
			
			tx=datastore.newTransaction();
			
			Key taskKey=datastore.add(FullEntity.newBuilder(factory.newKey()).build()).getKey();
			Storage storage = StorageOptions.newBuilder().setProjectId("engine-niit").build().getService();
		    BlobId blobId = BlobId.of("niitgaebucket", taskKey.getId()+".jpg");
		    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		    storage.create(blobInfo, bytes);
		    
			Entity entity=Entity.newBuilder(taskKey)
					.set("username",req.getParameter("username"))
					.set("email",req.getParameter("email"))
					.set("password",req.getParameter("password"))
					.set("age",Long.parseLong(req.getParameter("age")))
					.build();
			
			
			datastore.put(entity);
			
			tx.commit();
		    resp.sendRedirect("DisplayUsers");
		}
		catch(Exception e)
		{
			PrintWriter out=resp.getWriter();
			out.println(e+"");
			tx.rollback();
		}
		
		
	}
}
