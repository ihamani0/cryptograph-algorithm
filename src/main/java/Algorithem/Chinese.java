package Algorithem;

import java.util.ArrayList;
import java.util.List;

public class Chinese {

    public static  int FindX(ArrayList<Integer> num , ArrayList<Integer> rem){
        //for test co prime with each elements
        for (int i = 0 ; i < num.size() ; i++){
            for (int j = i+1 ; j < num.size() ; j++){
                if(GCD.FindGcd(num.get(i),num.get(j)) != 1 ){
                   return -1;
                }
            }
        }

        //find X
        int M= 1;
        for (int n : num) {
            M *= n;
        }



        int X = 0;

        for (int i = 0; i < num.size(); i++) {
            int m = M / num.get(i);

            X += rem.get(i) * m * GCD.MultInverse(m, num.get(i));
        }
        return (X % M );
    }

    public static List<Integer> MakeNumInList(String numTextField){
        List<Integer> numList = new ArrayList<>();

        String[] numStrings = numTextField.split(",");

        for (String numstring : numStrings) {
            numList.add(Integer.parseInt(numstring));
        }
        return numList;
    }

    public static List<Integer> MakeRemInList(String remTextField){
        List<Integer> remList = new ArrayList<>();

        String[] remStrings = remTextField.split(",");

        for (String remstring : remStrings) {
            remList.add(Integer.parseInt(remstring));
        }
        return remList;
    }

}
