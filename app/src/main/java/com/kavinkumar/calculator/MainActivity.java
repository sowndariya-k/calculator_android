package com.kavinkumar.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView solutiontxt,resulttxt;
    MaterialButton allclearbtn,clearbtn,equalbtn,dividebtn,multiplybtn,addbtn,subtractbtn;
    MaterialButton openbracbtn,closebtn,dotbtn;
    MaterialButton num0btn,num1btn,num2btn,num3btn,num4btn,num5btn,num6btn,num7btn,num8btn,num9btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solutiontxt = findViewById(R.id.solution_text);
        resulttxt = findViewById(R.id.result_text);

        assignId(allclearbtn,R.id.allclear_btn);
        assignId(clearbtn,R.id.correct_btn);
        assignId(equalbtn,R.id.equal_btn);
        assignId(dividebtn,R.id.divide_btn);
        assignId(multiplybtn,R.id.multiply_btn);
        assignId(addbtn,R.id.add_btn);
        assignId(subtractbtn,R.id.subtract_btn);
        assignId(openbracbtn,R.id.openbrac_btn);
        assignId(closebtn,R.id.closebrac_btn);
        assignId(dotbtn,R.id.dot_btn);
        assignId(num0btn,R.id.number0_btn);
        assignId(num1btn,R.id.number1_btn);
        assignId(num2btn,R.id.number2_btn);
        assignId(num3btn,R.id.number3_btn);
        assignId(num4btn,R.id.number4_btn);
        assignId(num5btn,R.id.number5_btn);
        assignId(num6btn,R.id.number6_btn);
        assignId(num7btn,R.id.number7_btn);
        assignId(num8btn,R.id.number8_btn);
        assignId(num9btn,R.id.number9_btn);
    }
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        MaterialButton button =(MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutiontxt.getText().toString();

        if(buttonText.equals("AC")){
            solutiontxt.setText("");
            resulttxt.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutiontxt.setText(resulttxt.getText());
            return;
        }
        if(buttonText.equals("C")){
            if (dataToCalculate.length()==1){
                solutiontxt.setText("");
                resulttxt.setText("0");
                return;
            } else if (dataToCalculate.length()==0) {
                solutiontxt.setText("");
                resulttxt.setText("0");
                return;
            } else {
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            }
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutiontxt.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("404")){
            resulttxt.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "404";
        }
    }
}