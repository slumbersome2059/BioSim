import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public final class ProbUtil {
    private static final Random rand = new Random();
    private static final Input input;

    static {
        try {
            input = new Input();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean randomSuccess(double successChance){
        return 0 == rand.nextInt((int)(1/successChance));
    }
    public static ArrayList<Point> generateAdjSquares(Point p){
        Random rand = new Random();
        boolean check;
        ArrayList<Point> possSquares = new ArrayList<>();
        int[] incs = new int[]{-1,0,1};//the moves you can make in each direction
        for(int i: incs){
            for(int j: incs){
                Point trialP = new Point(p.getX() + i, p.getY() + j);
                if(!(i== 0 && j==0) && (p.getX() + i < input.GRID_WIDTH) && (p.getY() + j < input.GRID_HEIGHT)
                && (p.getX() + i >= 0) && (p.getY() + j >= 0)
                ){//checks if the new pos isn't the original one or one where somehting else is
                    possSquares.add(trialP);
                }
            }
        }
        return possSquares;
    }
}
