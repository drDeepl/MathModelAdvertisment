package com;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;


import java.util.regex.Pattern;


import com.service.Equation;
import com.service.Utils;

public class PrimaryController {
    @FXML TextField N0;
    @FXML TextField a1;
    @FXML TextField a2;
    @FXML TextField h;
    @FXML TextField tn;
    @FXML Label msg;
    @FXML Label timeForEndAdvertCompaign;
    @FXML LineChart<Number,Number> lineChart;
    

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void calculateAdvertCampany() throws IOException {
        // System.out.println(N0.getText() + "\n" + a1.getText() + "\n" + a2.getText());
        
        lineChart.getData().clear();
        lineChart.visibleProperty().set(false);
        msg.setText("");
        timeForEndAdvertCompaign.setText("");
        String message = "Не является числом поле:\n";
        boolean dataNotValid = false;
        String checkNumber = "-?\\d+(\\.\\d+)?";
        System.out.println(Pattern.matches(checkNumber, N0.getText()));
        if(!Pattern.matches(checkNumber, N0.getText())){
            message += "\tОбщее число покупателей\n";
            dataNotValid = true;

        }
        if(!Pattern.matches(checkNumber, a1.getText())){
            message += "\tПоказатель эффективности основной рекламы\n";
            dataNotValid = true;
        }
        if(!Pattern.matches(checkNumber, a2.getText())){
            message += "\tПоказатель эффективности косвенной рекламы\n";
            dataNotValid = true;
        }
        if(!Pattern.matches(checkNumber, h.getText())){
            message += "\tШаг для решения дифференциального уравнения\n";
            dataNotValid = true;
        }
        if(!Pattern.matches(checkNumber, tn.getText())){
            message += "\tДлительность рекламной кампании\n";
            dataNotValid = true;
        }
        if(dataNotValid){
            msg.setText(message);
        }
        else{
            int N0Int = Integer.parseInt(N0.getText());
            float a1Float = Float.parseFloat(a1.getText());
            float a2Float = Float.parseFloat(a2.getText());
            float hFloat = Float.parseFloat(h.getText());
            int tnInt = Integer.parseInt(tn.getText());
            Equation equation = new Equation(a1Float, a2Float, N0Int);
            int[] t = equation.createArrayT(tnInt);
            float[] N = equation.SolveEquationRungeKutta4(t, hFloat);
            Utils utils = new Utils();
            utils.printArrayFloat(N);
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            String msgResult = "";
            int endTime = 0;
            for(int i = 0; i < N.length; i++){
               int NValue = Math.round(N[i]) +1;
            //    if(NValue < 0){
            //     break;
            //    }
               if(i!= 0){
                if(NValue == Math.round(N[i-1] +1)){
                    break;
                   }
               }
               System.out.println(NValue);
               if(NValue == N0Int){
                msgResult += "Рекламная кампания потеряет свою эффективность через " + i + " дней";
                
                break;
                
               }
               
               series.getData().add(new XYChart.Data<>(t[i], NValue));
               endTime ++;
            }
            
            if(msgResult.isEmpty()){
                
                msgResult += "\nЗа " + endTime + " дней " + "рекламная кампания охватит " + Math.round(N[N.length-1]) + " из " + N0.getText() + " платежеспособных покупателей";
            }
            System.out.println(equation.analyticSolver(endTime));
            timeForEndAdvertCompaign.setText(msgResult);
            series.setName("Осведомленные о товаре");

            lineChart.visibleProperty().set(true);
            lineChart.getData().add(series);
            
        }
    }

    @FXML
    private void clearData() throws Exception{
        a1.setText("");
        a2.setText("");
        h.setText("");
        tn.setText("");
        N0.setText("");
        lineChart.visibleProperty().set(false);
        msg.setText("");
        timeForEndAdvertCompaign.setText("");

    }

    @FXML
    private void drawChart() throws Exception{
        System.out.println("PRINT");
        
    }
}
