public abstract class Output {
    public static void printOut(String[][] arrToPrint){
        StringBuilder s = new StringBuilder();
        for(String[] i: arrToPrint){
            for(String j: i){

                s.append(String.format("%-10s", j));
            }
            s.append("\n");
        }
        System.out.println(s);
    }
}
