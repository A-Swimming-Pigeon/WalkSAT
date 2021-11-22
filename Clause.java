import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Clause {
    private  String L;
    private  String M;
    private  String R;
    private  ArrayList<String> alphabet = new ArrayList<>();
    private  void initalphabet() {
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
    public Clause() {
        L = "";
        M = "";
        R = "";
    }
    public Clause(String l, String m, String r) {
        L = l;
        M = m;
        R = r;
    }
    public void setValues(String l, String m, String r) {
        L = l;
        M = m;
        R = r;
    }
    public void setL(String l) {
        L = l;
    }
    public void setM(String m) {
        M = m;
    }
    public void setR(String r) {
        R = r;
    }
    public String getL() {
        return L;
    }
    public String getM() {
        return M;
    }
    public String getR() {
        return R;
    }
    public void scramble(){
        initalphabet();
        Random rd = new Random();
        int negate, index1, index2, index3;
        String c1 = "" , c2 = "" ,c3 = "";
        negate = rd.nextInt() % 2;
        if(negate == 1){
            c1 += "!";
        }
        index1 = (int)(Math.random() * 20);
        c1 += alphabet.get(index1);

        negate = rd.nextInt() % 2;
        index2 = (int)(Math.random() * 20);
        while (index2 == index1) {
            index2 = (int)(Math.random() * 20);
        }
        if(negate == 1){
            c2 += "!";
        }
        c2 += alphabet.get(index2);

        negate = rd.nextInt() % 2;
        index3 = (int)(Math.random() * 20);
        if(negate == 1){
            c3 += "!";
        }
        while (index3 == index1 || index3 == index2) {
            index3 = (int)(Math.random() * 20);
        }
        c3 += alphabet.get(index3);
        L = c1;
        M = c2;
        R = c3;
    }
    public boolean checkSatisfied(HashMap<String, Integer> values) {
        boolean negateL = L.length() > 1;
        boolean negateM = M.length() > 1;
        boolean negateR = R.length() > 1;
        int Lval = values.get(L.substring(L.length()-1));
        int Mval = values.get(M.substring(M.length()-1));
        int Rval = values.get(R.substring(R.length()-1));
        boolean Ltrue = Lval == 1;
        boolean Mtrue = Mval == 1;
        boolean Rtrue = Rval == 1;
        if(negateL) {
            Ltrue = !Ltrue;
        }
        if(negateM) {
            Mtrue = !Mtrue;
        }
        if(negateR) {
            Rtrue = !Rtrue;
        }
        return Ltrue || Mtrue || Rtrue;
    }

    @Override
    public String toString() {
        return "(" + L + "," + M + "," + R + ")";
    }
}
