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
}
