import java.util.ArrayList;

public class Herbivore extends Organism {
    private int energy;
    private boolean successfulReproduction;
    private int initEnergy;
    public Herbivore(Point p, int initEnergy){
        super(p, "H");
        this.initEnergy = initEnergy;
        energy = initEnergy;
        successfulReproduction = false;
    }
    @Override
    public void update(SimulationContext simulationContext) {

        //To kill any plants if it starts on a plant square
        killIfPossible(simulationContext);
        reproduceIfPossible(simulationContext);
        if(energy - simulationContext.getInput().HERBIVORE_LOSE_ENERGY_IN_TURN <= 0){
            simulationContext.killNewOrganism(this, true);//herbivore dies
        }else{
            energy -= simulationContext.getInput().HERBIVORE_LOSE_ENERGY_IN_TURN;
            //Move
            simulationContext.removeOrganismFromSquare(this);
            super.setCoords(generateRandAdjSquare(simulationContext));
            simulationContext.addOrganismToSquare(this);
            killIfPossible(simulationContext);//kills plant on a square it's going to, herbivore rendered before plant
            //if herbivore moves to a square and plant reproduces you will see both in top and then plant killed next cycle
            if(!successfulReproduction) {// specification seems to imply only one reproduction per update
                reproduceIfPossible(simulationContext);
            }
        }





    }
    private Point generateRandAdjSquare(SimulationContext simulationContext){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords(), simulationContext);
        int l = possSquares.toArray().length;
        return possSquares.get(ProbUtil.rand.nextInt(0, l));
    }
    private void killIfPossible(SimulationContext simulationContext){
        ArrayList<Organism> plantsInSquare = (simulationContext.getOrganismsInSquare(getCoords(), "P"));
        if(!plantsInSquare.isEmpty()){
            simulationContext.killNewOrganism(plantsInSquare.getFirst(), true);//should only be one plant per square
            energy += simulationContext.getInput().HERBIVORE_EAT_ENERGY;
        }
    }
    private void reproduceIfPossible(SimulationContext simulationContext){
        if(energy > simulationContext.getInput().HERBIVORE_REPRODUCTION_THRESHOLD_ENERGY){//the herbivore will only produce one child per update, if it still has energy it will reproduce next round
            simulationContext.createNewOrganism(new Herbivore(generateRandAdjSquare(simulationContext), initEnergy), true);
            energy -= simulationContext.getInput().HERBIVORE_LOSE_ENERGY_IN_REPRODUCTION;
            successfulReproduction = true;
        }else{
            successfulReproduction = false;
        }
    }
}
