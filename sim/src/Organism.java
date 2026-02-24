import java.util.ArrayList;
import java.util.Random;

public abstract class Organism {
    private final String symbol;
    private Point coords;


    public Organism(Point p, String symbol){

        this.symbol = symbol;
        this.setCoords(p);
    }

    public String getSymbol() {
        return symbol;
    }



    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }

    public abstract void update(SimulationContext simulationContext);





    public ArrayList<Point> generateAdjSquares(Point p, SimulationContext simulationContext){
        Random rand = new Random();
        boolean check;
        ArrayList<Point> possSquares = new ArrayList<>();
        int[] incs = new int[]{-1,0,1};//the moves you can make in each direction
        for(int i: incs){
            for(int j: incs){
                Point trialP = new Point(p.getX() + i, p.getY() + j);
                if(!(i== 0 && j==0) && (p.getX() + i < simulationContext.getInput().GRID_WIDTH) && (p.getY() + j < simulationContext.getInput().GRID_HEIGHT)
                        && (p.getX() + i >= 0) && (p.getY() + j >= 0)
                ){//checks if the new pos isn't the original one or one outside grid
                    possSquares.add(trialP);
                }
            }
        }
        return possSquares;
    }
}
