import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public final class ProbUtil {

    public static boolean randomSuccess(double successChance){
        Random rand = new Random();
        return 1 == rand.nextInt((int)(1/successChance));
    }
    public static Optional<Point> generateRandAdjSquare(Point p, HashMap<Point, Organism> occupied){
        Random rand = new Random();
        ArrayList<Point> possSquares = new ArrayList<>();
        int[] incs = new int[]{-1,0,1};
        for(int i: incs){
            for(int j: incs){
                Point trialP = new Point(p.getX() + i, p.getY() + j);
                if(!(i== 0 && j==0) && !(occupied.containsKey(trialP))){
                    possSquares.add(trialP);
                }
            }
        }
        int l = possSquares.toArray().length;
        if(l== 0){
            return Optional.empty();
        }else{
            return Optional.of(possSquares.get(rand.nextInt(0, l)));
        }
        //Deal with 0 and 0
        //take in occupied points to generate unoccupied ones or not anything
    }
}
