import java.util.ArrayList;
import java.util.Optional;

public class Herbivore extends Organism{
    private int energy;
    private boolean successfulReproduction;
    public Herbivore(Point p, Game game){
        super(p, game, "H");
        energy = getGame().getInput().HERBIVORE_START_ENERGY;
        successfulReproduction = false;
    }
    @Override
    public void update() {

        //To kill any plants if it starts on a plant square
        kill();
        reproduce();
        if(energy - getGame().getInput().HERBIVORE_LOSE_ENERGY_IN_TURN <= 0){
            getGame().killNewOrganism(this);//herbivore dies
        }else{
            energy -= getGame().getInput().HERBIVORE_LOSE_ENERGY_IN_TURN;
            //Move
            getGame().removeOrganismFromSquare(this);
            super.setCoords(generateRandAdjSquare());
            getGame().addOrganismToSquare(this);
            kill();//kills plant on a square it's going to, herbivore rendered before plant
            //if herbivore moves to a square and plant reproduces you will see both in top and then plant killed next cycle
            if(!successfulReproduction) {// specification seems to imply only one reproduction per update
                reproduce();
            }
        }





    }
    private Point generateRandAdjSquare(){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords());
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
        if(energy > getGame().getInput().HERBIVORE_REPRODUCTION_THRESHOLD_ENERGY){//the herbivore will only produce one child per update, if it still has energy it will reproduce next round
            getGame().createNewOrganism(new Herbivore(generateRandAdjSquare(), getGame()), true);
            energy -= getGame().getInput().HERBIVORE_LOSE_ENERGY_IN_REPRODUCTION;
            successfulReproduction = true;
        }else{
            successfulReproduction = false;
        }
    }
}
