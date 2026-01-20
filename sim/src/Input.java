import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Input {
    private InputStream input;
    private ArrayList<String> params;
    private HashMap<String, String> constants;
    public final int CARNIVORE_START_ENERGY;//public is used here because these are final constants
    public final int CARNIVORE_EAT_ENERGY;
    public final int CARNIVORE_REPRODUCTION_THRESHOLD_ENERGY;
    public final int CARNIVORE_LOSE_ENERGY_IN_REPRODUCTION;
    public final int CARNIVORE_LOSE_ENERGY_IN_TURN;
    public final int HERBIVORE_START_ENERGY;//public is used here because these are final constants
    public final int HERBIVORE_EAT_ENERGY;
    public final int HERBIVORE_REPRODUCTION_THRESHOLD_ENERGY;
    public final int HERBIVORE_LOSE_ENERGY_IN_REPRODUCTION;
    public final int HERBIVORE_LOSE_ENERGY_IN_TURN;
    public final int GRID_WIDTH;
    public final int GRID_HEIGHT;
    public final int NUM_CARNIVORES;
    public final int NUM_HERBIVORES;
    public final int NUM_PLANTS;
    public final float PLANT_REPRODUCTION_PROBABILITY;
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
        String msg = "CARNIVORE_START_ENERGY";
        try {
            CARNIVORE_START_ENERGY = Integer.parseInt(constants.get(msg));
            msg = "CARNIVORE_EAT_ENERGY";
            CARNIVORE_EAT_ENERGY = Integer.parseInt(constants.get(msg));
            msg = "CARNIVORE_REPRODUCTION_THRESHOLD_ENERGY";
            CARNIVORE_REPRODUCTION_THRESHOLD_ENERGY = Integer.parseInt(constants.get(msg));
            msg = "CARNIVORE_LOSE_ENERGY_IN_REPRODUCTION";
            CARNIVORE_LOSE_ENERGY_IN_REPRODUCTION = Integer.parseInt(constants.get(msg));
            msg = "CARNIVORE_LOSE_ENERGY_IN_TURN";
            CARNIVORE_LOSE_ENERGY_IN_TURN = Integer.parseInt(constants.get(msg));
            msg = "HERBIVORE_START_ENERGY";
            HERBIVORE_START_ENERGY = Integer.parseInt(constants.get(msg));
            msg = "HERBIVORE_EAT_ENERGY";
            HERBIVORE_EAT_ENERGY = Integer.parseInt(constants.get(msg));
            msg = "HERBIVORE_REPRODUCTION_THRESHOLD_ENERGY";
            HERBIVORE_REPRODUCTION_THRESHOLD_ENERGY = Integer.parseInt(constants.get(msg));
            msg = "HERBIVORE_LOSE_ENERGY_IN_REPRODUCTION";
            HERBIVORE_LOSE_ENERGY_IN_REPRODUCTION = Integer.parseInt(constants.get(msg));
            msg = "HERBIVORE_LOSE_ENERGY_IN_TURN";
            HERBIVORE_LOSE_ENERGY_IN_TURN = Integer.parseInt(constants.get(msg));
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
            msg = "PLANT_REPRODUCTION_PROBABILITY";
            PLANT_REPRODUCTION_PROBABILITY = Float.parseFloat(constants.get(msg));
        }catch (NumberFormatException e){
            throw new IOException("Problem with " + msg);
        }
    }

}
