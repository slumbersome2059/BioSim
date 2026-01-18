import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public abstract class Output {
    public static void printOut(String[][] arrToPrint){
        StringBuilder s = new StringBuilder();
        final int LENGTH_OCCUPIED_BY_STRING = 10;//Maximum length that the string can take
        final int PAD_LENGTH = 5;//max extra padding that can be don

        for(String[] i: arrToPrint){
            ArrayList<StringBuilder> lines = new ArrayList<>();
            int maxLength = Arrays.stream(i).max(Comparator.comparingInt(String::length)).get().length();
            for(int lineCount = 0; lineCount <= (maxLength / LENGTH_OCCUPIED_BY_STRING) ; lineCount ++){
                lines.add(new StringBuilder());
            }
            for(String j: i){
                int lineCount = 0;
                while (!j.isEmpty()){
                    lines.get(lineCount).append(String.format("%-" + (LENGTH_OCCUPIED_BY_STRING + PAD_LENGTH) + "s", j.substring(0,Math.min(j.length(), LENGTH_OCCUPIED_BY_STRING))));
                    j = j.substring(Math.min(j.length(), LENGTH_OCCUPIED_BY_STRING));
                    lineCount += 1;
                }
                while(lineCount <= (maxLength/LENGTH_OCCUPIED_BY_STRING)){
                    lines.get(lineCount).append(String.format("%-" + (LENGTH_OCCUPIED_BY_STRING + PAD_LENGTH) + "s", " "));
                    lineCount += 1;
                }


            }
            for(StringBuilder build: lines){
                s.append(build.append("\n"));
                //s.append(build.append(("-".repeat((arrToPrint[0].length)*10))+"\n"));
            }
        }
        System.out.println(s);
    }
}
