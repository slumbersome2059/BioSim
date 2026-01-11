import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Plant extends Organism{
    public Plant(Point p, Game game){
        super(p, game, "P");
    }

    @Override
    public void update() {
        if(ProbUtil.randomSuccess(0.1)){//creates organism at random adjacent square and doesn't do anything if all squares occupied
            Optional<Point> newP = generateRandAdjSquare();
            newP.ifPresent(point -> super.getGame().createNewOrganism(new Plant(point, getGame())));
        }
    }
    private Optional<Point> generateRandAdjSquare(){
        ArrayList<Point> possSquares = ProbUtil.generateAdjSquares(super.getCoords());
        int l = possSquares.toArray().length;
        for(int i = 0; i< l;i++){
            if((getGame().getOccupied().get(possSquares.get(i)) instanceof Plant)){
               possSquares.remove(possSquares.get(i));//remove if plant going to plant square
               //Only one plant allowed per square
                l -= 1;
            }
        }
        if(l== 0){
            return Optional.empty();//no possible squares to move to
        }else{

            return Optional.of(possSquares.get(super.getRand().nextInt(0, l)));
        }
    }
}
