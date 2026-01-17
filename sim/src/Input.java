import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Input {
    private InputStream input;
    private ArrayList<String> params;
    private HashMap<String, String> constants;
    public final int START_ENERGY;//public is used here because these are final constants
    public final int EAT_ENERGY;
    public final int REPRODUCTION_THRESHOLD_ENERGY;
    public final int LOSE_ENERGY_IN_REPRODUCTION;
    public final int LOSE_ENERGY_IN_TURN;
    public final int GRID_WIDTH;
    public final int GRID_HEIGHT;
    public final int NUM_CARNIVORES;
    public final int NUM_HERBIVORES;
    public final int NUM_PLANTS;
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
            msg = "GRID_WIDTH";
            GRID_WIDTH = Integer.parseInt(constants.get(msg));
            msg = "GRID_HEIGHT";
            GRID_HEIGHT = Integer.parseInt(constants.get(msg));
            msg = "NUM_CARNIVORES";
            NUM_CARNIVORES = Integer.parseInt(constants.get(msg));
            msg = "NUM_HERBIVORES";
            NUM_HERBIVORES = Integer.parseInt(constants.get(msg));
            msg = "NUM_PLANTS";
            NUM_PLANTS = Integer.parseInt(constants.get(msg));
        }catch (NumberFormatException e){
            throw new IOException("Problem with " + msg);
        }
    }

}
