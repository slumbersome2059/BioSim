import java.io.IOException;
import java.util.*;

public class Game {//contains state that all other classes of game needs
    private ArrayList<Organism> organisms;
    private HashMap<Point, ArrayList<Organism>> occupied;
    private final Input input;
    private final Random rand;
    private ArrayList<int[]> unusedPlantSquares;
    private ArrayList<Organism> toAdd;
    private ArrayList<Organism> toRemove;

    public Game() throws IOException {

        input = new Input();
        organisms = new ArrayList<>();
        //ordered by carnivore, herbivore then plant
        occupied = new HashMap<>();
        unusedPlantSquares = genUnusedPlantSquares();
        rand = new Random();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
        for(int i = 0; i < input.NUM_CARNIVORES;i++){
            int x = rand.nextInt(0, input.GRID_WIDTH);
            int y = rand.nextInt(0, input.GRID_HEIGHT);
            createNewOrganism(new Carnivore(new Point(x, y), this), false);
        }
        for(int i = 0; i < input.NUM_HERBIVORES;i++){
            int x = rand.nextInt(0, input.GRID_WIDTH);
            int y = rand.nextInt(0, input.GRID_HEIGHT);
            createNewOrganism(new Herbivore(new Point(x, y), this), false);
        }
        for(int i = 0; i < input.NUM_PLANTS;i++){
            int ind = rand.nextInt(0, unusedPlantSquares.size());
            createNewOrganism(new Plant(new Point(unusedPlantSquares.get(ind)[0], unusedPlantSquares.get(ind)[1]), this), false);
            unusedPlantSquares.remove(ind);
        }


        //createNewOrganism(new Carnivore(new Point(0, 3), this), false);


        //createNewOrganism(new Herbivore(new Point(0, 1), this), false);
        //createNewOrganism(new Herbivore(new Point(0, 3), this), false);
        /*
        createNewOrganism(new Plant(new Point(12, 8), this));
        createNewOrganism(new Plant(new Point(12, 9), this));
        createNewOrganism(new Plant(new Point(12, 11), this));
        createNewOrganism(new Plant(new Point(12, 10), this));
        createNewOrganism(new Herbivore(new Point(13, 10), this));
        createNewOrganism(new Herbivore(new Point(11, 9), this));
        */
    }
    private void addInOrder(Organism o){
        int count = 0;
        while(count < organisms.size() && o.getSymbol().compareTo(organisms.get(count).getSymbol()) > 0){//o.getSymbol() > orgs.get(out).getSymbol
            count += 1;
        }
        organisms.add(count, o);
    }

    public void createNewOrganism(Organism o, boolean duringUpdate){
        addOrganismToSquare( o);//will ensure it looks like it's been added to the rest of the world
        if(duringUpdate){//when you are reproducing from an organism you will add to front of array and you don't want to come back to that organism again so you add by 1
            //you will also not update reproduced organism at the cycle it's reproduced on
            //but when you are not iterating and adding stuff at the start you don't want the count to move
            toAdd.add(o);
        }else{
            addInOrder(o);
        }
    }
    public void killNewOrganism(Organism o, boolean duringUpdate){
        // code for death
        removeOrganismFromSquare(o);
        if(duringUpdate){
            toRemove.add(o);
        }else{
            organisms.remove(o);//remove organism from organisms list
        }
    }

    public ArrayList<Organism> getOrganismsInSquare(Point p, String symbol){
        //gives an array list of the organisms in the square with that symbol
        if(occupied.containsKey(p)){
            return new ArrayList<>(occupied.get(p).stream().filter(o -> o.getSymbol().equals(symbol)).toList());
        }else{
            return new ArrayList<>();//no organisms in square because square unoccupied
        }

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
    }


    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public HashMap<Point, ArrayList<Organism>> getOccupied() {
        return occupied;
    }
    public void play() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Press space for next run\n");
        Output.printOut(genOutputArr());
        int iteratorCount = 0;
        while(true) {
            if(s.nextLine().contains(" ")){
                while (iteratorCount < organisms.size()){
                    //TURN ORDER -> Carnivore is updated first, then herbivore, then plant
                    //Ensures that a plant does not get killed by a herbivore which itself is being eaten by a carnivore
                    Organism o = organisms.get(iteratorCount);

                    // BUG FIX: Only update if the organism hasn't been killed this turn
                    if (!toRemove.contains(o)) {
                        o.update();
                    }
                    iteratorCount += 1;
                }
                for(Organism o:toRemove){
                    organisms.remove(o);
                }
                for(Organism o:toAdd){
                    addInOrder(o);
                }
                toRemove.clear();
                toAdd.clear();

                Output.printOut(genOutputArr());
                System.out.println("Press space for next run\n");
                iteratorCount = 0;
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
    public Input getInput(){
        return input;
    }
}
/*
TODO:
Create O classes
Test Herbivore and Plant classes
Create Carnivore class
Deal with having to display more than padding length by creating new line(just calc how many new lines you need)
Write Unit tests for generateAdjSquares and other units not relying on too much randomness
Only displays the state after 1 update and never displays initial state
Deal with an update being skipped in update for loop
Have move and kill as two separate methods making a output of array in between
 */