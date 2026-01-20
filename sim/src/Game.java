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
    private int iteratorCount;
    public Game() throws IOException {
        input = new Input();
        organisms = new ArrayList<>();
        //ordered by carnivore, herbivore then plant
        occupied = new HashMap<>();
        iteratorCount = 0;
        unusedPlantSquares = genUnusedPlantSquares();
        rand = new Random();
        /*
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

         */
        createNewOrganism(new Carnivore(new Point(4, 1), this), false);


        createNewOrganism(new Herbivore(new Point(4, 1), this), false);
        createNewOrganism(new Herbivore(new Point(3, 0), this), false);
        /*
        createNewOrganism(new Plant(new Point(12, 8), this));
        createNewOrganism(new Plant(new Point(12, 9), this));
        createNewOrganism(new Plant(new Point(12, 11), this));
        createNewOrganism(new Plant(new Point(12, 10), this));
        createNewOrganism(new Herbivore(new Point(13, 10), this));
        createNewOrganism(new Herbivore(new Point(11, 9), this));
        */
    }

    public void createNewOrganism(Organism o, boolean duringUpdate){
        int count = 0;
        while(count < organisms.size() && o.getSymbol().compareTo(organisms.get(count).getSymbol()) > 0){//o.getSymbol() > orgs.get(out).getSymbol
            count += 1;
        }
        organisms.add(count, o);
        addOrganismToSquare( o);
        if(duringUpdate){//when you are reproducing from an organism you will add to front of array and you don't want to come back to that organism again so you add by 1
            //you will also not update reproduced organism at the cycle it's reproduced on
            //but when you are not iterating and adding stuff at the start you don't want the count to move
            iteratorCount += 1;
        }
    }
    public void killNewOrganism(Organism o){
        // code for death
        organisms.remove(o);//remove organism from organisms list
        iteratorCount -= 1;
        removeOrganismFromSquare(o);
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
        //CHECK THAT CODE ABOVE REMOVES ELEMENT FROM ACTUAL ARRAYLIST
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
        while(true) {
            if(s.nextLine().contains(" ")){
                while (iteratorCount < organisms.size()){
                    organisms.get(iteratorCount).update();
                    iteratorCount += 1;
                }

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