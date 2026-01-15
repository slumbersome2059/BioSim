import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

public class Game {//contains state that all other classes of game needs
    private ArrayList<Organism> organisms;
    private HashMap<Point, ArrayList<Organism>> occupied;
    public Game(){
        organisms = new ArrayList<>();
        occupied = new HashMap<>();
        organisms.add(new Plant(new Point(12, 1), this));
    }
    public void createNewOrganism(Organism o){
        organisms.add(o);
        addOrganismToSquare( o);
    }
    public void killNewOrganism(Organism o){
        // code for death
        organisms.remove(o);//remove organism from organisms list
        removeOrganismFromSquare(o);
    }

    public ArrayList<Organism> getOrganismsInSquare(Point p, String symbol){
        //gives an array list of the organisms in the square with that symbol
        return (ArrayList<Organism>) occupied.get(p).stream().filter(o -> o.getSymbol().equals(symbol)).toList();
    }
    public void addOrganismToSquare(Organism o){//adds organism to occupied hashmap
        ArrayList<Organism> os;
        if(occupied.containsKey((o.getCoords()))){
            os = occupied.get(o.getCoords());
        }else{
            os = new ArrayList<>();
        }
        os.add(o);
        occupied.put(o.getCoords(),os);
    }
    public void removeOrganismFromSquare(Organism o){//removes organism from point it's on
        occupied.get(o.getCoords()).remove(o);// remove organism from occupied
        //CHECK THAT CODE ABOVE REMOVES ELEMENT FROM ACTUAL ARRAYLIST
    }


    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public HashMap<Point, ArrayList<Organism>> getOccupied() {
        return occupied;
    }
    public void play(){
        while(true){
            for(int i = 0; i < organisms.size();i++){
                organisms.get(i).update();
            }
        }

    }
}
