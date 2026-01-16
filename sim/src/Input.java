import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Input {
    private InputStream input;
    private ArrayList<String> params;
    private HashMap<String, String> constants;
    public final int START_ENERGY;
    public final int EAT_ENERGY;
    public final int REPRODUCTION_THRESHOLD_ENERGY;
    public final int LOSE_ENERGY_IN_REPRODUCTION;
    public final int LOSE_ENERGY_IN_TURN;
    public Input() throws IOException{
        constants = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("sim/src/config"));
        String line = br.readLine();

        while(line != null){
            line = line.replace(" ", "");
            if(!line.contains("=")){throw new IOException("No equals");}
            String[] splitLines = line.trim().split("=");
            constants.put(splitLines[0], splitLines[1]);
            line = br.readLine();
        }
        String msg = "START_ENERGY";
        try {
            START_ENERGY = Integer.parseInt(constants.get(msg));
            msg = "EAT_ENERGY";
            EAT_ENERGY = Integer.parseInt(constants.get(msg));
            msg = "REPRODUCTION_THRESHOLD_ENERGY";
            REPRODUCTION_THRESHOLD_ENERGY = Integer.parseInt(constants.get(msg));
            msg = "LOSE_ENERGY_IN_REPRODUCTION";
            LOSE_ENERGY_IN_REPRODUCTION = Integer.parseInt(constants.get(msg));
            msg = "LOSE_ENERGY_IN_TURN";
            LOSE_ENERGY_IN_TURN = Integer.parseInt(constants.get(msg));
        }catch (NumberFormatException e){
            throw new IOException("Problem with " + msg);
        }
    }

}
