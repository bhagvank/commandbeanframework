package cbf.commands;

import cbf.control.*;
import java.util.*;


public abstract class PageCommandBean implements CommandBean

{
   protected ModelManager modelManager;

   public void init(ModelManager modelManager )
   {

	   this.modelManager = modelManager;


   }

   public void perform() throws PageCommandException
   {


   }

   public void execute(Hashtable hashTable) throws PageCommandException

   {

   }


   public ModelManager getModelManager()
   {

       return modelManager;

   }


}