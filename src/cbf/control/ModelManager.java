/*

   This class will contain all the accessors to the model

   (business logic entities and session beans)

*/


package cbf.control;

import javax.servlet.*;
import javax.servlet.http.*;

public class ModelManager implements HttpSessionBindingListener,java.io.Serializable
{

   private ServletContext context;
   private HttpSession session;

   public void init(ServletContext context,HttpSession session)
    {

	  this.context = context;
	  this.session = session;

    }

   public void setApplicationAttribute(String key,Object value)
    {

        context.setAttribute(key,value);

    }

   public void setSessionAttribute(String key,Object value)
    {

        session.setAttribute(key,value);

    }

    public Object getApplicationAttribute(String key)
     {

	     return context.getAttribute(key);

     }

    public Object getSessionAttribute(String key)
     {

         return session.getAttribute(key);

     }
   public void valueBound(HttpSessionBindingEvent event)
   {


   }

   public void valueUnbound(HttpSessionBindingEvent event)
   {



   }





}