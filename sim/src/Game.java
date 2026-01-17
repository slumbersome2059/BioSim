import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Game {//contains state that all other classes of game needs
    private ArrayList<Organism> organisms;
    private HashMap<Point, ArrayList<Organism>> occupied;
    private Input input;
    private Random rand;
    private ArrayList<int[]> unusedPlantSquares;
    public Game() throws IOException {
        input = new Input();
        organisms = new ArrayList<>();
        occupied = new HashMap<>();
        unusedPlantSquares = genUnusedPlantSquares();
        rand = new Random();
        for(int i = 0; i < input.NUM_PLANTS;i++){
            int ind = rand.nextInt(0, unusedPlantSquares.size());
            createNewOrganism(new Plant(new Point(unusedPlantSquares.get(ind)[0], unusedPlantSquares.get(ind)[1]), this));
            unusedPlantSquares.remove(ind);
        }
        for(int i = 0; i < input.NUM_HERBIVORES;i++){
            int x = rand.nextInt(0, input.GRID_WIDTH);
            int y = rand.nextInt(0, input.GRID_HEIGHT);
            createNewOrganism(new Herbivore(new Point(x, y), this));
        }
        /*
        createNewOrganism(new Plant(new Point(12, 8), this));
        createNewOrganism(new Plant(new Point(12, 9), this));
        createNewOrganism(new Plant(new Point(12, 11), this));
        createNewOrganism(new Plant(new Point(12, 10), this));
        createNewOrganism(new Herbivore(new Point(13, 10), this));
        createNewOrganism(new Herbivore(new Point(11, 9), this));
        */
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
        return new ArrayList<>(occupied.get(p).stream().filter(o -> o.getSymbol().equals(symbol)).toList());
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
    public void play() throws IOException {
        String[][] sA = new String[5][2];
        Scanner s = new Scanner(System.in);
        System.out.println("Press space for next run\n");
        while(true) {
            if(s.nextLine().contains(" ")){
                for (int i = 0; i < organisms.size(); i++) {
                    organisms.get(i).update();
                }
                Output.printOut(genOutputArr());
                System.out.println("Press space for next run\n");
            }
        }

    }
    private String[][] genOutputArr(){
        String[][] sArr = new String[input.GRID_HEIGHT][input.GRID_WIDTH];
        for(int x=0; x < input.GRID_WIDTH;x++){
            for(int y=0; y < input.GRID_HEIGHT;y++){
                StringBuilder s = new StringBuilder("[");
                Point p = new Point(x, y);
                int added = 0;
                if(occupied.containsKey(p)){
                    for(Organism o : occupied.get(p)){
                        if(added > 0){
                            s.append(" ").append(o.getSymbol());
                        }else{
                            s.append(o.getSymbol());
                            added += 1;
                        }
                    }
                }
                s.append("]");
                sArr[y][x] = s.toString();
            }
        }
        return sArr;
    }
    private ArrayList<int[]> genUnusedPlantSquares(){
        ArrayList<int[]> squares = new ArrayList<>();
        for(int x = 0; x < input.GRID_WIDTH;x++){
            for(int y = 0;y < input.GRID_HEIGHT;y++){
                squares.add(new int[]{x,y});
            }
        }
        return squares;
    }
}
/*
TODO:
Create O classes
Test Herbivore and Plant classes
Create Carnivore class
Deal with having to display more than padding length by creating new line(just calc how many new lines you need)
Write Unit tests for generateAdjSquares and other units not relying on too much randomness
 */