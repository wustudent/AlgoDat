import java.util.ArrayList;
/**
 * A steel factory (an implementation of an enterprise)
 * represented by its name and list of employees.
 * @author AlgoDat team
 *
 */
public class SteelFactory implements Enterprise {
  
    /** Name of the factory */
    private String name;
    /** List of workers */
    private ArrayList<Worker> workers;

    /**
     * Creates a new steel factory with a given name
     * @param name Name of the factory
     */
    public SteelFactory(String name) {
    	this.name=name;
    	workers=new ArrayList<Worker>();
        //throw new NotImplementedException("SteelFactory");//TODO
    }

    @Override
    public void addWorker(String firstName, String lastName) {
    	workers.add(new Worker(firstName,lastName));
      //throw new NotImplementedException("addWorker");//TODO
    }

    @Override
    public String getName() {
    	return this.name;
      //throw new NotImplementedException("getName");//TODO
    }

    @Override
    public int compareTo(Enterprise o) {
    	if(this.getWorkerCount()<o.getWorkerCount())
    		return -1;
    	else if(this.getWorkerCount()==o.getWorkerCount())
    		return 0;
    	else
    		return 1;
      //throw new NotImplementedException("compareTo");//TODO
    }

    @Override
    public int getWorkerCount() {
    	return workers.size();
      //throw new NotImplementedException("getWorkerCount");//TODO
    }
}

