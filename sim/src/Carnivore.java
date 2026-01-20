import java.util.ArrayList;

public class Carnivore extends Organism{
    private int energy;

    public Carnivore(Point p, Game game) {
        super(p, game, "C");
        energy = getGame().getInput().CARNIVORE_START_ENERGY;


    }
    @Override
    public void update() {
        kill();
        //Main
        getGame().removeOrganismFromSquare(this);
        super.setCoords(generateNextSquare());
        getGame().addOrganismToSquare(this);
        if(energy <= 0){
            getGame().killNewOrganism(this);//herbivore dies
        }else{
            kill();
            reproduce();
            energy -= getGame().getInput().HERBIVORE_LOSE_ENERGY_IN_TURN;
        }
    }
    private Point generateRandAdjSquare(){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords());
        int l = possSquares.size();
        return possSquares.get(super.getRand().nextInt(0, l));
    }
    private Point generateNextSquare(){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords());
        ArrayList<Organism> adjHerbivores = getGame().getOrganismsInSquare(getCoords(), "H");
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
            energy -= getGame().getInput().HERBIVORE_LOSE_ENERGY_IN_REPRODUCTION;
        }
    }
}
