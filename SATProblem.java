import java.util.ArrayList;
import java.util.HashMap;

public class SATProblem {
    private int flips;
    private int prob = 50; //probability of a random flip
    private static ArrayList<String> alphabet = new ArrayList<>();
    private void initalphabet() {
        alphabet.add("A");
        alphabet.add("B");
        alphabet.add("C");
        alphabet.add("D");
        alphabet.add("E");
        alphabet.add("F");
        alphabet.add("G");
        alphabet.add("H");
        alphabet.add("I");
        alphabet.add("J");
        alphabet.add("K");
        alphabet.add("L");
        alphabet.add("M");
        alphabet.add("N");
        alphabet.add("O");
        alphabet.add("P");
        alphabet.add("Q");
        alphabet.add("R");
        alphabet.add("S");
        alphabet.add("T");
    }
    private ArrayList<Clause> clauses;
    private int C = 20;
    private static HashMap<String, Integer> values = new HashMap<>();

    public SATProblem() {
        initalphabet();
        initValues();
        clauses = new ArrayList<>();
        flips = 0;
    }
    public SATProblem(int newC) {
        initalphabet();
        initValues();
        clauses = new ArrayList<>();
        flips = 0;
        C = newC;
    }

    private static void initValues() {
        int temp;
        for(int i = 0; i < 20; i++) {
            temp = ((int) (Math.random()* 1000) + 1) % 2;
            values.put(alphabet.get(i), temp);
        }

    }

    public boolean solved() {
        boolean tbr = true;
        for(Clause e: clauses) {
            if(!e.checkSatisfied(values)) {
                tbr = false;
            }
        }
        return tbr;
    }

    public ArrayList<Clause> solve() {
        long start_time = System.currentTimeMillis();
        long wait_time = 10000;
        long end_time = start_time + wait_time;
            int rand = (int) (Math.random() * 100) + 1;
            while (!solved() && System.currentTimeMillis() < end_time) {
                String f = checkFlips();
                if (f.equalsIgnoreCase("Z")) {
                    return clauses;
                } else {
                    if (rand <= prob) {
                        rand = (int) (Math.random() * 20);
                        f = alphabet.get(rand);
                    }
                    flip(f);
                }
            }
            return clauses;
    }

    public int solve_retInt() {
        long start_time = System.currentTimeMillis();
        long wait_time = 10000;
        long end_time = start_time + wait_time;
        int rand = (int) (Math.random() * 100) + 1;
        while (!solved() && System.currentTimeMillis() < end_time) {
            String f = checkFlips();
            if (f.equalsIgnoreCase("Z")) {
                return flips;
            } else {
                if (rand <= prob) {
                    rand = (int) (Math.random() * 20);
                    f = alphabet.get(rand);
                }
                flip(f);
                flips++;
            }
        }
        return flips;
    }

    public String checkFlips() {
        HashMap<String, Integer> newValues = (HashMap) values.clone();
        HashMap<String, Integer> stable = (HashMap) newValues.clone();
        ArrayList<Integer> flips = new ArrayList<>();
        for(int i = 0; i < alphabet.size(); i++) {
            if(newValues.get(alphabet.get(i)) == 1) { //change 1 character
                newValues.replace(alphabet.get(i), 0);
            } else {
                newValues.replace(alphabet.get(i), 1);
            }


            int current = 0;
            int changed = 0;

            for(int j = 0; j < clauses.size(); j++) {
                if(clauses.get(j).checkSatisfied(values)) {
                    current += 1;
                }
                if(clauses.get(j).checkSatisfied(newValues)) {
                    changed += 1;
                }
            }

            flips.add(changed - current); //amount of new true clauses for a switch

            newValues = (HashMap) stable.clone();

        }
        int max = 0;
        for (int i = 0; i < flips.size(); i++) {
            if(flips.get(i) > flips.get(max)) {
                max = i;
            }

        }
        if(flips.get(max) < 0) {
            return "Z";
        }

        return alphabet.get(max);
    }
    public void flip(String x) {
        if(values.get(x) == 1) {
            values.replace(x, 0);
        } else {
            values.replace(x, 1);
        }
    }
    public void SATGenerate() {
        Clause c;
        for(int i = 0; i < C; i++) {
            c = new Clause();
            c.scramble();
            clauses.add(c);
            //System.out.println(clauses.get(i).printClause());
        }
    }
    public boolean allSolved() {
        boolean allsolved = true;
        for(Clause c: clauses) {
            if(!c.checkSatisfied(values)) {
                allsolved = false;
                System.out.println(c + " is false.");
            }
        }
        return allsolved;
    }

    public void scrambleClauses() {
        for(Clause e: clauses) {
            e.scramble();
        }
    }

    public ArrayList<Clause> getClauses() {
        return clauses;
    }
    public HashMap<String, Integer> getValues() {
        return values;
    }

    @Override
    public String toString() {
        String tbr = "";
        for(Clause e: clauses) {
            tbr += e.toString() + "\n";
        }
        return tbr;
    }
}
