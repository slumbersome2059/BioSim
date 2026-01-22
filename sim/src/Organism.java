import java.util.ArrayList;
import java.util.Random;

public abstract class Organism {
    private final String symbol;
    private Point coords;
    private final Game game;

    public Organism(Point p, Game game, String symbol){
        this.game = game;
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

    public abstract void update();

    public Game getGame() {
        return game;
    }



    public ArrayList<Point> generateAdjSquares(Point p){
        Random rand = new Random();
        boolean check;
        ArrayList<Point> possSquares = new ArrayList<>();
        int[] incs = new int[]{-1,0,1};//the moves you can make in each direction
        for(int i: incs){
            for(int j: incs){
                Point trialP = new Point(p.getX() + i, p.getY() + j);
                if(!(i== 0 && j==0) && (p.getX() + i < getGame().getInput().GRID_WIDTH) && (p.getY() + j < getGame().getInput().GRID_HEIGHT)
                        && (p.getX() + i >= 0) && (p.getY() + j >= 0)
                ){//checks if the new pos isn't the original one or one outside grid
                    possSquares.add(trialP);
                }
            }
        }
        return possSquares;
    }
}
