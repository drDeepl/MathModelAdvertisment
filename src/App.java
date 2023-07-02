import classes.Equation;
import classes.Utils;
public class App {
    public static void main(String[] args) throws Exception {
        Equation equation = new Equation((float)0.2,(float)0.0000001, 1000000);
        Utils utils = new Utils();
        int[] t = new int[100];
        for(int i = 0; i < t.length; i++){
            t[i] = i;
        }
        float[] N = equation.SolveEquationRungeKutta4(t, (float)1);
        utils.printArrayFloat(N);

        

    }
}
