import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    // "D:\2021-2022\Term 1\CPSC 422\results.csv"
    private static int numProblems = 50;
    private static double getMedian(ArrayList<Integer> sets) {
        Collections.sort(sets);
        int middle = sets.size() / 2;
        middle = middle > 0 && middle % 2 == 0 ? middle - 1 : middle;
        return sets.get(middle);
    }
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter("D:/2021-2022/Term 1/CPSC 422/results.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("C,Solves,SuccessRate,Median,\n");
            ArrayList<SATProblem> problems;
            ArrayList<Integer> cvalues = new ArrayList<>();
            for(int i = 1; i <= 10; i++) {
                cvalues.add(i * 20);
            }
            for(int i = 0; i < cvalues.size(); i++) {
                System.out.println("Currently working on C = " + cvalues.get(i) + ", Boss!");
                problems = new ArrayList<>();
                for (int j = 0; j < numProblems; j++) {
                    SATProblem k = new SATProblem(cvalues.get(i));
                    k.SATGenerate();
                    problems.add(k);
                }
                int counter = 1;
                int successcounter = 1;
                int flipsused;
                ArrayList<Integer> flips = new ArrayList<>();
                boolean solved;
                for (SATProblem e : problems) {
                    flipsused = e.solve_retInt();
                    solved = e.allSolved();
                    if (solved) {
                        System.out.println("Problem " + counter + " solved with " + flipsused + " flips.");
                        flips.add(flipsused);
                        successcounter++;
                    } else {
                        System.out.println("Problem " + counter + " failed.");
                    }
                    System.out.println();
                    counter++;
                }
                sb.append(cvalues.get(i));
                sb.append(",");
                sb.append(successcounter - 1);
                sb.append(",");
                double successRate = (double) (successcounter - 1) / (double)problems.size();
                sb.append(successRate);
                sb.append(",");
                sb.append(getMedian(flips));
                sb.append("\n");

            }
            writer.write(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
