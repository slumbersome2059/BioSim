import java.util.ArrayList;

public class Carnivore extends Organism{
    private int energy;

    public Carnivore(Point p, Game game) {
        super(p, game, "C");
        energy = getGame().getInput().CARNIVORE_START_ENERGY;


    }
    @Override
    public void update() {
        kill();//kill anything on current square
        //Main
        getGame().removeOrganismFromSquare(this);
        super.setCoords(generateNextSquare());
        getGame().addOrganismToSquare(this);
        kill();
        reproduce();
        energy -= getGame().getInput().CARNIVORE_LOSE_ENERGY_IN_TURN;
        if(energy <= 0){
            getGame().killNewOrganism(this);//carnivore dies
        }
    }
    private Point generateRandAdjSquare(){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords());
        int l = possSquares.size();
        return possSquares.get(super.getRand().nextInt(0, l));
    }
    private Point generateNextSquare(){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords());
        ArrayList<Organism> adjHerbivores =new ArrayList<>();
        for(Point p: possSquares){
            adjHerbivores.addAll(getGame().getOrganismsInSquare(p, "H"));
        }
        if(adjHerbivores.isEmpty()){
            return generateRandAdjSquare();
        }else{
            return adjHerbivores.get(super.getRand().nextInt(0, adjHerbivores.size())).getCoords();
        }
    }
    private void kill(){//kills any herbivores on the square it's on
        ArrayList<Organism> herbivoresInSquare = (getGame().getOrganismsInSquare(getCoords(), "H"));
        if(!herbivoresInSquare.isEmpty()){
            getGame().killNewOrganism(herbivoresInSquare.getFirst());//should only be one plant per square
            energy += getGame().getInput().CARNIVORE_EAT_ENERGY;
        }
    }
    private void reproduce(){
        if(energy > getGame().getInput().CARNIVORE_REPRODUCTION_THRESHOLD_ENERGY){
            getGame().createNewOrganism(new Carnivore(generateRandAdjSquare(), getGame()), true);
            energy -= getGame().getInput().CARNIVORE_LOSE_ENERGY_IN_REPRODUCTION;
        }
    }
}
