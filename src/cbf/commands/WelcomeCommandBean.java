
package cbf.commands;

import java.util.*;

public class WelcomeCommandBean extends PageCommandBean
{
  private String message;

  public void perform()
  {

   message="Welcome";

  }

  public void execute(Hashtable hashTable)
  {

    Enumeration enum = hashTable.keys();

    while(enum.hasMoreElements())
     {

	     System.out.println(enum.nextElement());

     }

    System.out.println(hashTable.get("ActionName"));
    System.out.println(hashTable.get("NextActionName"));


  }

  public String getMessage()
   {
     return message;

   }



}