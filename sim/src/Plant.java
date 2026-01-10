import java.util.Optional;

public class Plant extends Organism{
    public Plant(Point p, Game game){
        this.setGame(game);
        this.setSymbol("P");
        this.setCoords(p);


    }

    @Override
    public void update() {
        if(ProbUtil.randomSuccess(0.1)){//creates organism at random adjacent square and doesn't do anything if all squares occupied
            Optional<Point> newP = ProbUtil.generateRandAdjSquare(this.getCoords(),super.getGame().getOccupied());
            newP.ifPresent(point -> super.getGame().createNewOrganism(new Plant(point, getGame())));
        }
    }
}
