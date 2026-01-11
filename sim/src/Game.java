import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Vector;

public class Game {//contains state that all other classes of game needs
    private ArrayList<Organism> organisms;
    private HashMap<Point, Organism> occupied;
    private Plant tp;
    public Game(){
        organisms = new ArrayList<>();
        occupied = new HashMap<>();
    }
    public void createNewOrganism(Organism o){
        organisms.add(o);
        occupied.put(o.getCoords(), o);

    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public HashMap<Point, Organism> getOccupied() {
        return occupied;
    }
    public void play(){


    }
}
