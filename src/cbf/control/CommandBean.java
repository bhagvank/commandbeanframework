package cbf.control;

import cbf.commands.*;

public interface CommandBean
{

 public void perform() throws PageCommandException;


 public void execute(java.util.Hashtable hashTable) throws PageCommandException;


}