package classes;

public class Utils {
    public void printArrayInt(int[] array){
        String result = "";
        for(int i = 0; i < array.length; i++) {
            if(i ==0){
                result += array[i];
            }
            else{
                result += ", " + array[i];
            }
        }
        System.out.println(result);
    }

    public void printArrayFloat(float[] array){
        String result = "";
        for(int i = 0; i < array.length; i++) {
            if(i ==0){
                result += array[i];
            }
            else{
                result += ", " + array[i];
            }
        }
        System.out.println(result);
    }
    
}
