
package cbf.commands;

public class CheckCommandBean extends PageCommandBean
{

   private String message;

   public String getMessage()
   {

      return message;

   }

   public void perform()
   {

     message = "Check";

   }

   public void execute(java.util.Hashtable hashTable)
    {

    System.out.println(hashTable.get("ActionName"));
	    System.out.println(hashTable.get("NextActionName"));


    }




}