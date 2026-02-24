import java.util.ArrayList;
import java.util.Optional;

public class Plant extends Organism {
    public Plant(Point p){
        super(p, "P");
    }
    @Override
    public void update(SimulationContext simulationContext) {
        if(randomSuccess(simulationContext)){//creates organism at random adjacent square and doesn't do anything if all squares occupied
            Optional<Point> newP = generateRandAdjSquare(simulationContext);
            newP.ifPresent(point -> simulationContext.createNewOrganism(new Plant(point), false));
        }
    }
    private Optional<Point> generateRandAdjSquare(SimulationContext simulationContext){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords(), simulationContext);
        for(int i = 0; i< possSquares.size();i++){
            if((simulationContext.getOccupied().containsKey(possSquares.get(i)))){

               if(simulationContext.getOrganismsInSquare(possSquares.get(i), "P").isEmpty()) {
                   possSquares.remove(possSquares.get(i));//remove if plant going to plant square
                   i-= 1;
               }
               //Only one plant allowed per square
            }
        }
        if(possSquares.isEmpty()){
            return Optional.empty();//no possible squares to move to
        }else{

            return Optional.of(possSquares.get(ProbUtil.rand.nextInt(0, possSquares.size())));
        }
    }
    private boolean randomSuccess(SimulationContext simulationContext){
        return 0 == ProbUtil.rand.nextInt((int)(1/ simulationContext.getInput().PLANT_REPRODUCTION_PROBABILITY));
    }
}
