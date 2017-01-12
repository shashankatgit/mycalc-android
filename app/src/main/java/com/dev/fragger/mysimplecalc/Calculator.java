package com.dev.fragger.mysimplecalc;

import android.net.Uri;
import android.renderscript.Float2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Calculator extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,btnDot;
    Button btnDiv, btnAdd, btnSub, btnMul, btnEquals;
    Button btnClear, btnDel;

    TextView lastOperand;
    TextView operatorText;
    TextView curOperand;
    String lastOpText;
    String curOpText;
    String operator;

    float lastOpValue;
    float curOpValue;

    ControlListener controlListener;
    NumericListener numericListener;
    OperatorListener operatorListener;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        findViewById(R.id.btnBOpen).setVisibility(View.GONE);
        findViewById(R.id.btnBClose).setVisibility(View.GONE);

        //First Init
        findViews();
        setListeners();
        clearValues();
        //

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void findViews() {

        curOperand = (TextView) findViewById(R.id.curOperand);
        lastOperand = (TextView) findViewById(R.id.lastOperand);
        operatorText = (TextView) findViewById(R.id.operatorText);
        btn0 = (Button) findViewById(R.id.btnZero);
        btn1 = (Button) findViewById(R.id.btnOne);
        btn2 = (Button) findViewById(R.id.btnTwo);
        btn3 = (Button) findViewById(R.id.btnThree);
        btn4 = (Button) findViewById(R.id.btnFour);
        btn5 = (Button) findViewById(R.id.btnFive);
        btn6 = (Button) findViewById(R.id.btnSix);
        btn7 = (Button) findViewById(R.id.btnSeven);
        btn8 = (Button) findViewById(R.id.btnEight);
        btn9 = (Button) findViewById(R.id.btnNine);
        btnDot = (Button) findViewById(R.id.btnDot);

        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEquals = (Button) findViewById(R.id.btnEqual);


        btnClear = (Button) findViewById(R.id.btnClear);
        btnDel = (Button) findViewById(R.id.btnDel);

    }

    void setListeners() {
        controlListener = new ControlListener();
        numericListener = new NumericListener();
        operatorListener = new OperatorListener();

        btn0.setOnClickListener(numericListener);
        btn1.setOnClickListener(numericListener);
        btn2.setOnClickListener(numericListener);
        btn3.setOnClickListener(numericListener);
        btn4.setOnClickListener(numericListener);
        btn5.setOnClickListener(numericListener);
        btn6.setOnClickListener(numericListener);
        btn7.setOnClickListener(numericListener);
        btn8.setOnClickListener(numericListener);
        btn9.setOnClickListener(numericListener);
        btnDot.setOnClickListener(numericListener);

        btnAdd.setOnClickListener(operatorListener);
        btnSub.setOnClickListener(operatorListener);
        btnMul.setOnClickListener(operatorListener);
        btnDiv.setOnClickListener(operatorListener);

        btnEquals.setOnClickListener(controlListener);
        btnClear.setOnClickListener(controlListener);
        btnDel.setOnClickListener(controlListener);

    }

    void clearValues() {
        curOpText = "";
        lastOpText = "";
        operator = "";
        parseValues();
        refreshView();

    }

    final void refreshView() {
        String tmpCurOpText = curOpText;
        String tmpLastOpText = lastOpText;
        String tmpOperator;

        //if (tmpCurOpText.equals("")) tmpCurOpText = "...";
        if (tmpLastOpText.equals("")) tmpLastOpText = "...";

        //Toast.makeText(Calculator.this, "oper value = " + tmpCurOpText, Toast.LENGTH_SHORT).show();

        if(operator.equals("")) tmpOperator="    ";
        else tmpOperator=" "+operator+" ";

        operatorText.setText(tmpOperator);
        curOperand.setText(tmpCurOpText.trim());
        lastOperand.setText(tmpLastOpText.trim());
    }

    void parseValues() {
        if (curOpText.equals("")) curOpValue = 0;
        else if (curOpText.equals("-")) curOpValue = 0;
        else curOpValue = Float.parseFloat(curOpText);

        if (lastOpText.equals("")) lastOpValue = 0;
        else lastOpValue = Float.parseFloat(lastOpText);
    }

    boolean performOperation() {

        float temp = 0;
        parseValues();

        if (operator.equals("+")) {
            temp = curOpValue + lastOpValue;

        } else if (operator.equals("-")) {
            temp = lastOpValue - curOpValue;

        } else if (operator.equals("*")) {
            temp = curOpValue*lastOpValue;
            //Toast.makeText(Calculator.this, "Multiply operation done", Toast.LENGTH_SHORT).show();

        } else if (operator.equals("/")) {
            if (curOpValue == 0) {
                Toast.makeText(Calculator.this, "Divide by Zero not possible", Toast.LENGTH_SHORT).show();
                clearValues();
                lastOperand.setText("DIV BY 0");
                return false;
            } else temp = lastOpValue / curOpValue;

        } else if(operator.equals("")) return true;

        lastOpValue = temp;
        lastOpText = new Float(lastOpValue).toString().trim();
        curOpText = "";
        return true;
    }




    class NumericListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String append = "a";

            //Toast.makeText(Calculator.this, "Numeric Keypad", Toast.LENGTH_SHORT).show();

            if (v == btn0) {
                if (!curOpText.equals("0"))
                    append = "0";
                else return;
            } else if (v == btn1) {
                append = "1";
            } else if (v == btn2) append = "2";
            else if (v == btn3) append = "3";
            else if (v == btn4) append = "4";
            else if (v == btn5) append = "5";
            else if (v == btn6) append = "6";
            else if (v == btn7) append = "7";
            else if (v == btn8) append = "8";
            else if (v == btn9) append = "9";
            else if (v == btnDot) {
                if(curOpText.indexOf('.')==-1) append = ".";
            }


            curOpText = curOpText.concat(append);
            refreshView();
        }
    }

    class OperatorListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
        String tempOperator="+";
        boolean performRefresh = true;

            if (v == btnAdd) {
                tempOperator = "+";
            }

            else if (v == btnSub) {

                if(curOpText.equals("")) { //in case user attempts to enter a negative value
                    curOpText="-";
                    refreshView();
                    return;
                }

                else tempOperator = "-";
            }

            else if (v == btnMul) {
                tempOperator = "*";
                //Toast.makeText(Calculator.this, "Multiply selected", Toast.LENGTH_SHORT).show();
            }

            else if (v == btnDiv) {
                tempOperator = "/";
            }

            if (lastOpText.equals("")) {
                lastOpText = curOpText;
                curOpText = "";
                operator=tempOperator;
                //Toast.makeText(Calculator.this, "just refresh", Toast.LENGTH_SHORT).show();
            }

            else {
                if(curOpText.equals("")) {
                    operator=tempOperator;
                }
                else {
                    performRefresh=performOperation();
                    operator = tempOperator;
                    //Toast.makeText(Calculator.this, "calling performOp()", Toast.LENGTH_SHORT).show();
                }
            }

            if(performRefresh) refreshView();
        }
    }

    class ControlListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        boolean performRefresh=true;
            if(v==btnClear) {
                clearValues();
            }

            if(v==btnDel) {
                if(curOpText.length()>0)
                    curOpText=curOpText.substring(0,curOpText.length()-1);
            }

            if(v==btnEquals) {
                performRefresh=performOperation();
                operator="";

            }
            if(performRefresh) refreshView();
        }
    }



    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Calculator Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.dev.fragger.mysimplecalc/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Calculator Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.dev.fragger.mysimplecalc/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}