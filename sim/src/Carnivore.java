import java.util.ArrayList;

public class Carnivore extends Organism {
    private int energy;
    private boolean successfulReproduction;

    public Carnivore(Point p, Game game) {
        super(p, game, "C");
        energy = getGame().getInput().CARNIVORE_START_ENERGY;
        successfulReproduction = false;


    }
    @Override
    public void update() {
        killIfPossible();//kill anything on current square, eating takes no energy so no check
        reproduceIfPossible();
        if(energy - getGame().getInput().CARNIVORE_LOSE_ENERGY_IN_TURN <= 0){
            getGame().killNewOrganism(this, true);//carnivore dies
        }else{
            energy -= getGame().getInput().CARNIVORE_LOSE_ENERGY_IN_TURN;

            //Move
            getGame().removeOrganismFromSquare(this);
            super.setCoords(generateNextSquare());
            getGame().addOrganismToSquare(this);
            //Kill on move
            killIfPossible();
            if(!successfulReproduction) {// specification seems to imply only one reproduction per update
                reproduceIfPossible();
            }
        }


    }
    private Point generateRandAdjSquare(){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords());
        int l = possSquares.size();
        return possSquares.get(ProbUtil.rand.nextInt(0, l));
    }
    private Point generateNextSquare(){
        ArrayList<Point> possSquares = super.generateAdjSquares(super.getCoords());
        ArrayList<Organism> adjHerbivores =new ArrayList<>();
        for(Point p: possSquares){
            adjHerbivores.addAll(getGame().getOrganismsInSquare(p, "H"));
        }
        if(adjHerbivores.isEmpty()){
            return generateRandAdjSquare();
        }else{
            return adjHerbivores.get(ProbUtil.rand.nextInt(0, adjHerbivores.size())).getCoords();
        }
    }
    private void killIfPossible(){//kills one herbivore on the square it's on
        ArrayList<Organism> herbivoresInSquare = (getGame().getOrganismsInSquare(getCoords(), "H"));
        if(!herbivoresInSquare.isEmpty()){
            getGame().killNewOrganism(herbivoresInSquare.getFirst(), true);//should only be one plant per square
            energy += getGame().getInput().CARNIVORE_EAT_ENERGY;
        }
    }
    private void reproduceIfPossible(){
        if(energy > getGame().getInput().CARNIVORE_REPRODUCTION_THRESHOLD_ENERGY){
            getGame().createNewOrganism(new Carnivore(generateRandAdjSquare(), getGame()), true);
            energy -= getGame().getInput().CARNIVORE_LOSE_ENERGY_IN_REPRODUCTION;
            successfulReproduction = true;
        }else{
            successfulReproduction = false;
        }
    }
}
