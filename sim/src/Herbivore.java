import java.util.ArrayList;
import java.util.Optional;

public class Herbivore extends Organism{
    private int energy;
    private static final int START_ENERGY = 100;
    private static final int EAT_ENERGY = 10;
    private static final int REPRODUCTION_THRESHOLD_ENERGY = 120;
    private static final int LOSE_ENERGY_IN_REPRODUCTION = 10;
    private static final int LOSE_ENERGY_IN_TURN = 1;
    public Herbivore(Point p, Game game){
        super(p, game, "H");
        energy = START_ENERGY;
    }
    @Override
    public void update() {
        getGame().removeOrganismFromSquare(this);
        super.setCoords(generateRandAdjSquare());
        getGame().addOrganismToSquare(this);
        if(energy <= 0){
            getGame().killNewOrganism(this);//herbivore dies
        }else{
            ArrayList<Organism> plantsInSquare = (getGame().getOrganismsInSquare(getCoords(), "P"));
            if(!plantsInSquare.isEmpty()){
                getGame().killNewOrganism(plantsInSquare.getFirst());//should only be one plant per square
                energy += EAT_ENERGY;
            }
            if(energy > REPRODUCTION_THRESHOLD_ENERGY){
                getGame().createNewOrganism(new Herbivore(generateRandAdjSquare(), getGame()));
                energy -= LOSE_ENERGY_IN_REPRODUCTION;
            }
            energy -= LOSE_ENERGY_IN_TURN;
        }

    }
    private Point generateRandAdjSquare(){
        ArrayList<Point> possSquares = ProbUtil.generateAdjSquares(super.getCoords());
        int l = possSquares.toArray().length;
        return possSquares.get(super.getRand().nextInt(0, l));
    }
}
