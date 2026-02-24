import java.util.ArrayList;
import java.util.HashMap;

public interface SimulationContext {
/*
//This interface is implemented by Game and it will mean other classes that need methods from Game will only be able to see
the methods they need rather than the whole Game class because you will pass SimulationContext into these other classes
so they can only see the methods defined here.
*/
    void killNewOrganism(Organism o, boolean duringUpdate);
    ArrayList<Organism> getOrganismsInSquare(Point p, String symbol);
    void createNewOrganism(Organism o, boolean duringUpdate);
    void removeOrganismFromSquare(Organism o);
    Input getInput();

    void addOrganismToSquare(Organism o);
    HashMap<Point, ArrayList<Organism>> getOccupied();
}
