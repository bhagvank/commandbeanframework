
package cbf.control;


import javax.servlet.http.*;
import javax.servlet.*;
import cbf.commands.*;

public class CommandCenterServlet extends HttpServlet
{
    private String jspLocation="";
    private ModelManager modelManager;


  public void init(ServletConfig config) throws ServletException
   {
	   super.init(config);
     System.out.println("initializing");


   }

   public void service(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException,ServletException
    {


       if(modelManager == null)
       {
         modelManager = new ModelManager();

         modelManager.init(getServletContext(),req.getSession(true));
	   }

       System.out.println("checking");
      try
       {
       String command = req.getParameter("ActionName");

       String nextCommand = req.getParameter("NextActionName");

       if(command == null)
        {

          nextCommand = "Welcome";

        } else

        {

         try
          {

PageCommandBean commandBean =(PageCommandBean) Class.forName("cbf.commands."+command+"CommandBean").newInstance();

          commandBean.init(modelManager);
          commandBean.perform();

          System.out.println("commandBean"+commandBean);

          if(req.getMethod().equals("GET"))
          {
			  System.out.println(req.getQueryString());
          commandBean.execute(HttpUtils.parseQueryString(req.getQueryString()));
	      }

	      else
	       {

		       ServletInputStream in = req.getInputStream();

		       int len =  in.available();

               System.out.println("length of"+in+"="+len);
		       java.util.Hashtable hashTable = HttpUtils.parsePostData(len,in);

		       commandBean.execute(hashTable);


		   }


	      } catch(Exception e)
	       {

		     throw new PageCommandException("unable to instantiate");
		   }
        }

        try
        {

			System.out.println("loading the command bean"+nextCommand);
       PageCommandBean nextCommandBean =(PageCommandBean) Class.forName("cbf.commands."+nextCommand+"CommandBean").newInstance();

       System.out.println("performing");

       nextCommandBean.init(modelManager);
       nextCommandBean.perform();

       System.out.println("performed");
       req.setAttribute("command",nextCommandBean);

       String jspFile = jspLocation+nextCommand+".jsp";

       RequestDispatcher rd = getServletContext().getRequestDispatcher(jspFile);

       System.out.println("forwarding using"+ rd);
       rd.forward(req,res);
	    }
	    catch(InstantiationException e)
	     {
			 System.out.println("unable to instantiate");
	       throw new PageCommandException("unable to instantiate");
	     }
	     	    catch(IllegalAccessException e)
		 	     {
		 			 System.out.println("unable to access");
		 	       throw new PageCommandException("unable to instantiat");
		 	     }
		 	     catch(ClassNotFoundException e)
				 		 	     {
				 		 			 System.out.println("unable to find the class");
				 		 	       throw new PageCommandException("unable to instantiate");
		 	     }

         catch(java.io.IOException e)
         {

	      System.out.println("unable to forward the file IO");
	     }
	     catch(Exception e)
	     {
			 System.out.println("some other error");
		 }
       }
       catch(PageCommandException pce)
              {

                 RequestDispatcher rdf = getServletContext().getRequestDispatcher(jspLocation+"Error.jsp");
                 try
                 {
                 rdf.forward(req,res);
			     } catch(Exception e) { System.out.println("Error in Forwarding Request to Error page");}
              }



       /*       res.setContentType("text/html");
              java.io.PrintWriter pw = res.getWriter();

              pw.println("<HTML> <BODY> <B> Test </B> </BODY> </HTML>");
       */
    }



}