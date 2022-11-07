package kite.collin.valorantapi.Model;

public class MapModel extends ModelAbstract {

    private int numOfSites;


    public int getNumOfSites() {
        return numOfSites;
    }

    public void setNumOfSites(int numOfSites) {
        this.numOfSites = numOfSites;
    }

    public MapModel(int id, String name, int numOfSites) {
        setId(id);
        setName(name);
        this.numOfSites = numOfSites;
    }

    public MapModel() {
    }
}
