package classes;

public class Equation{
    private float a1;
    private float a2;
    private int N0;
    public Equation(float a1, float a2, int N0){
        this.a1 = a1;
        this.a2 = a2;
        this.N0 = N0;
    }


    private float apply (float t, float N){
        return  (a1 + a2*N)*(N0-N);
    }
    private float apply0(int t){
        return a1*N0;
    }

    public float[] SolveEquationRungeKutta4(int[] t, float h){
        float[] N = new float[t.length];
        N[0] = apply0(0);
        System.out.println("N(0) = " + N[0]);
        for(int i = 0; i < t.length-1; i++){
            float k1 = apply(t[i], N[i]);
            float k2 = apply((t[i]+ h/2), N[i] + h/2*k1);
            float k3 = apply((t[i]+ h/2), N[i] + h/2*k2);
            float k4 = apply(t[i]+h, N[i] + h*k3);
            N[i+1] = N[i] + (h/6 *(k1+2*k2+2*k3 + k4));
        }
        return N;
    }
}
