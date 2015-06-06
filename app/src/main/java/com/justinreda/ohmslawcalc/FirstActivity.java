package com.justinreda.ohmslawcalc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends ActionBarActivity {

    EditText wattageInET;
    EditText voltageInET;
    EditText resistanceInET;
    TextView wattageOutTV;
    TextView currentOutTV;
    TextView voltageOutTV;
    Button calculateBUT;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mContext = this;

        wattageInET = (EditText) findViewById(R.id.wattageET);
        voltageInET = (EditText) findViewById(R.id.voltageET);
        resistanceInET = (EditText) findViewById(R.id.resistanceET);

        wattageOutTV = (TextView) findViewById(R.id.wattageResultTV);
        currentOutTV = (TextView) findViewById(R.id.currentResultTV);
        voltageOutTV = (TextView) findViewById(R.id.voltageResultTV);

        calculateBUT = (Button) findViewById(R.id.calculateButton);

        calculateBUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double wattageIn;
                double voltageIn;
                double resistanceIn;

                resistanceIn = Double.parseDouble(resistanceInET.getText().toString());
                if (wattageInET.getText().length() > 0) {
                    wattageIn = Double.parseDouble(wattageInET.getText().toString());
                    calculateAndPublishResults(resistanceIn, wattageIn);

                } else if (voltageInET.getText().length() > 0) {
                    voltageIn = Double.parseDouble(voltageInET.getText().toString());
                    wattageIn = (voltageIn * voltageIn) / resistanceIn;
                    calculateAndPublishResults(resistanceIn, wattageIn);

                } else {
                    //error, one of them has to be filled in
                    Toast.makeText(mContext, "Error please enter wattage or voltage", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void calculateAndPublishResults(double resistance, double wattage) {

        double current = Math.sqrt((wattage / resistance));
        double voltage = current * resistance;


        wattageOutTV.setText(wattage + " watts");
        voltageOutTV.setText(voltage + " volts");
        currentOutTV.setText(current + " amps");

    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
