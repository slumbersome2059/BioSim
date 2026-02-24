import java.util.ArrayList;

public class Carnivore extends Organism {
    private int energy;
    private boolean successfulReproduction;
    private int initEnergy;

    public Carnivore(Point p, int initEnergy) {
        super(p, "C");
        this.initEnergy = initEnergy;
        energy = initEnergy;
        successfulReproduction = false;


    }
    @Override
    public void update(SimulationContext simulationContext) {
        killIfPossible(simulationContext);//kill anything on current square, eating takes no energy so no check
        reproduceIfPossible(simulationContext);
        if(energy - simulationContext.getInput().CARNIVORE_LOSE_ENERGY_IN_TURN <= 0){
            simulationContext.killNewOrganism(this, true);//carnivore dies
        }else{
            energy -= simulationContext.getInput().CARNIVORE_LOSE_ENERGY_IN_TURN;

            //Move
            simulationContext.removeOrganismFromSquare(this);
            super.setCoords(generateNextSquare(simulationContext));
            simulationContext.addOrganismToSquare(this);
            //Kill on move
            killIfPossible(simulationContext);
            if(!successfulReproduction) {// specification seems to imply only one reproduction per update
                reproduceIfPossible(simulationContext);
            }
        }


    }
    private Point generateRandAdjSquare(SimulationContext g){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords(), g);
        int l = possSquares.size();
        return possSquares.get(ProbUtil.rand.nextInt(0, l));
    }
    private Point generateNextSquare(SimulationContext g){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords(), g);
        ArrayList<Organism> adjHerbivores =new ArrayList<>();
        for(Point p: possSquares){
            adjHerbivores.addAll(g.getOrganismsInSquare(p, "H"));
        }
        if(adjHerbivores.isEmpty()){
            return generateRandAdjSquare(g);
        }else{
            return adjHerbivores.get(ProbUtil.rand.nextInt(0, adjHerbivores.size())).getCoords();
        }
    }
    private void killIfPossible(SimulationContext g){//kills one herbivore on the square it's on
        ArrayList<Organism> herbivoresInSquare = (g.getOrganismsInSquare(getCoords(), "H"));
        if(!herbivoresInSquare.isEmpty()){
            g.killNewOrganism(herbivoresInSquare.getFirst(), true);//should only be one plant per square
            energy += g.getInput().CARNIVORE_EAT_ENERGY;
        }
    }
    private void reproduceIfPossible(SimulationContext g){
        if(energy > g.getInput().CARNIVORE_REPRODUCTION_THRESHOLD_ENERGY){
            g.createNewOrganism(new Carnivore(generateRandAdjSquare(g), initEnergy), true);
            energy -= g.getInput().CARNIVORE_LOSE_ENERGY_IN_REPRODUCTION;
            successfulReproduction = true;
        }else{
            successfulReproduction = false;
        }
    }
}
