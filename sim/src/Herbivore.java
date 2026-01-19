import java.util.ArrayList;
import java.util.Optional;

public class Herbivore extends Organism{
    private int energy;
    public Herbivore(Point p, Game game){
        super(p, game, "H");
        energy = getGame().getInput().HERBIVORE_START_ENERGY;
    }
    @Override
    public void update() {
        //To kill any plants if it starts on a plant square
        kill();
        //Main
        getGame().removeOrganismFromSquare(this);
        super.setCoords(generateRandAdjSquare());
        getGame().addOrganismToSquare(this);
        if(energy <= 0){
            getGame().killNewOrganism(this);//herbivore dies
        }else{
            kill();//kills anything on a square it's going to, herbivore rendered before plant
            //if herbivore moves to a square and plant reproduces you will see both in top and then plant killed next cycle
            reproduce();
            energy -= getGame().getInput().HERBIVORE_LOSE_ENERGY_IN_TURN;
        }

    }
    private Point generateRandAdjSquare(){
        ArrayList<Point> possSquares = ProbUtil.generateAdjSquares(super.getCoords());
        int l = possSquares.toArray().length;
        return possSquares.get(super.getRand().nextInt(0, l));
    }
    private void kill(){
        ArrayList<Organism> plantsInSquare = (getGame().getOrganismsInSquare(getCoords(), "P"));
        if(!plantsInSquare.isEmpty()){
            getGame().killNewOrganism(plantsInSquare.getFirst());//should only be one plant per square
            energy += getGame().getInput().HERBIVORE_EAT_ENERGY;
        }
    }
    private void reproduce(){
        if(energy > getGame().getInput().HERBIVORE_REPRODUCTION_THRESHOLD_ENERGY){
            getGame().createNewOrganism(new Herbivore(generateRandAdjSquare(), getGame()));
            energy -= getGame().getInput().HERBIVORE_LOSE_ENERGY_IN_REPRODUCTION;
        }
    }
}
