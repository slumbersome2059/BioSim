import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
/*
public abstract class  InputTests {
    public static void Norm() throws IOException{
        //Normal case

        Input input = new Input();
        PrintWriter writer = new PrintWriter("sim/src/config", StandardCharsets.UTF_8);

        writer.println("""
                START_ENERGY = 1
                EAT_ENERGY = 100
                REPRODUCTION_THRESHOLD_ENERGY = 120
                LOSE_ENERGY_IN_REPRODUCTION = 10
                LOSE_ENERGY_IN_TURN = 1""");
        writer.close();
        assert input.START_ENERGY == 1;
        assert input.EAT_ENERGY == 100;
        assert input.REPRODUCTION_THRESHOLD_ENERGY == 120;
        assert input.LOSE_ENERGY_IN_REPRODUCTION == 10;
        assert input.LOSE_ENERGY_IN_TURN == 1;
    }
    public static void Spaces() throws IOException {
        //Spaces
        Input input = new Input();
        PrintWriter writer = new PrintWriter("sim/src/config", StandardCharsets.UTF_8);
        writer.println("""
                START_ENERGY =                   1
                EAT_ENERGY         = 100
                REPRODUCTION_THRESHOLD_ENERGY= 120
                LOSE_ENERGY_IN_REPRODUCTION =10
                LOSE_ENERGY_IN_TURN = 1""");
        writer.close();
        assert input.START_ENERGY == 1;
        assert input.EAT_ENERGY == 100;
        assert input.REPRODUCTION_THRESHOLD_ENERGY == 120;
        assert input.LOSE_ENERGY_IN_REPRODUCTION == 10;
        assert input.LOSE_ENERGY_IN_TURN == 1;
    }
    public static void ExtraFields() throws IOException {
        //Addimg extra fields
        Input input = new Input();
        PrintWriter writer = new PrintWriter("sim/src/config", StandardCharsets.UTF_8);
        writer.println("""
                START_ENERGY =                   1
                EAT_ENERGY         = 100
                REPRODUCTION_THRESHOLD_ENERGY= 120
                LOSE_ENERGY_IN_REPRODUCTION =10
                LOSE_ENERGY_IN_TURN = 1
                A = 2
                B = 1""");
        writer.close();
        assert input.START_ENERGY == 1;
        assert input.EAT_ENERGY == 100;
        assert input.REPRODUCTION_THRESHOLD_ENERGY == 120;
        assert input.LOSE_ENERGY_IN_REPRODUCTION == 10;
        assert input.LOSE_ENERGY_IN_TURN == 1;
    }
    public static void missingFields() throws IOException{
        //Missing fields -> should see exception
        Input input = new Input();
        PrintWriter writer = new PrintWriter("sim/src/config", StandardCharsets.UTF_8);
        writer.println("""
                START_ENERGY = 1
                EAT_ENERGY = 100
                REPRODUCTION_THRESHOLD_ENERGY= 120
                LOSE_ENERGY_IN_REPRODUCTION =10""");
        writer.close();
        assert input.START_ENERGY == 1;
        assert input.EAT_ENERGY == 100;
        assert input.REPRODUCTION_THRESHOLD_ENERGY == 120;
        assert input.LOSE_ENERGY_IN_REPRODUCTION == 10;
        assert input.LOSE_ENERGY_IN_TURN == 1;
    }

}
 */
