import java.util.ArrayList;
import java.util.Optional;

public class Plant extends Organism{
    public Plant(Point p, Game game){
        super(p, game, "P");
    }
    @Override
    public void update() {
        if(randomSuccess()){//creates organism at random adjacent square and doesn't do anything if all squares occupied
            Optional<Point> newP = generateRandAdjSquare();
            newP.ifPresent(point -> super.getGame().createNewOrganism(new Plant(point, getGame()), false));
        }
    }
    private Optional<Point> generateRandAdjSquare(){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords());
        for(int i = 0; i< possSquares.size();i++){
            if((getGame().getOccupied().containsKey(possSquares.get(i)))){

               if(!getGame().getOrganismsInSquare(possSquares.get(i), "P").isEmpty()) {
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
    private boolean randomSuccess(){
        return 0 == ProbUtil.rand.nextInt((int)(1/ getGame().getInput().PLANT_REPRODUCTION_PROBABILITY));
    }
}
