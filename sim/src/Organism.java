import java.util.ArrayList;
import java.util.Random;

public class Organism {
    private String symbol;
    private Point coords;
    private Game game;
    private Random rand;
    public Organism(Point p, Game game, String symbol){
        this.setGame(game);
        this.setSymbol(symbol);
        this.setCoords(p);
        rand = new Random();


    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }

    public void update(){};

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
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
