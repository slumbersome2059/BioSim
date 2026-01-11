import java.util.ArrayList;
import java.util.Optional;

public class Herbivore extends Organism{
    private int energy;
    private static final int START_ENERGY = 10;
    private static final int EAT_ENERGY = 1;
    public Herbivore(Point p, Game game){
        super(p, game, "H");
        energy = START_ENERGY;
    }
    @Override
    public void update() {
        super.setCoords(generateRandAdjSquare());
        if(energy <= 0){
            // code for death
            getGame().getOrganisms().remove(this);
            // remove organism from occupied
        }else{

        }

    }
    private Point generateRandAdjSquare(){
        ArrayList<Point> possSquares = ProbUtil.generateAdjSquares(super.getCoords());
        int l = possSquares.toArray().length;
        return possSquares.get(super.getRand().nextInt(0, l));
    }
}
