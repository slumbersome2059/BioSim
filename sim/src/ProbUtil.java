import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public final class ProbUtil {
    private static final Random rand = new Random();
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
                if(!(i== 0 && j==0)){//checks if the new pos isn't the original one or one where somehting else is
                    possSquares.add(trialP);
                }
            }
        }
        return possSquares;
    }
}
