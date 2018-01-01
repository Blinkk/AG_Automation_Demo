package com.ag_automation_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ag_automation_demo.utility.Extra;
import com.ag_automation_demo.utility.Utility;

import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity
{
    private String _vehicleName;

    // Views
    private ViewFlipper _vfVehicleName;
    private TextView _tvVehicleName;
    private EditText _etVehicleName;
    private EditText _etMSRP;
    private EditText _etDownPayment;
    private EditText _etLoanTerm;
    private EditText _etAPR;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        if (getIntent() != null)
        {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null)
            {
                _vehicleName = bundle.getString(Extra.VEHICLE_NAME, "");
            }
        }

        _vfVehicleName = (ViewFlipper) findViewById(R.id.vf_vehicle_name);
        _tvVehicleName = (TextView) findViewById(R.id.tv_current_vehicle);
        _etVehicleName = (EditText) findViewById(R.id.et_current_vehicle);
        _etMSRP = (EditText) findViewById(R.id.et_msrp);
        _etDownPayment = (EditText) findViewById(R.id.et_down_payment);
        _etLoanTerm = (EditText) findViewById(R.id.et_loan_term);
        _etAPR = (EditText) findViewById(R.id.et_apr);

        // Set initial values
        _tvVehicleName.setText(_vehicleName);
        String randomMSRP = Integer.toString(Utility.generateRandomInteger(10000, 100000));
        _etMSRP.setText(randomMSRP);

        // Set button click listeners
        Button btnEditName = (Button) _vfVehicleName.findViewById(R.id.btn_edit_name);
        Button btnSaveName = (Button) _vfVehicleName.findViewById(R.id.btn_save_name);
        btnEditName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _vfVehicleName.showNext();
                _etVehicleName.setText(_vehicleName);
                _etVehicleName.requestFocus();
            }
        });

        btnSaveName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _vfVehicleName.showPrevious();
                _vehicleName = _etVehicleName.getText().toString();
                _tvVehicleName.setText(_vehicleName);
            }
        });

        Button btnSaveEntry = (Button) findViewById(R.id.btn_save_entry);
        btnSaveEntry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ArrayList<String> dataList = new ArrayList<>(4);
                dataList.add(_etMSRP.getText().toString());
                dataList.add(_etDownPayment.getText().toString());
                dataList.add(_etLoanTerm.getText().toString());
                dataList.add(_etAPR.getText().toString());
                validateData(dataList);
            }
        });
    }

    private void validateData(ArrayList<String> dataList)
    {
        boolean success = true;
        for (int i = 0; i < dataList.size(); i++)
        {
            if (Utility.isNullOrEmpty(dataList.get(i)))
            {
                showError(i);
                success = false;
            }
        }

        if (success)
        {
            // Make calculations and return data to view
            int msrp = Integer.parseInt(dataList.get(0));
            int downPayment = Integer.parseInt(dataList.get(1));
            int loanTerm = Integer.parseInt(dataList.get(2));
            float apr = Float.parseFloat(dataList.get(3));

            int totalLoanAmount = msrp - downPayment;
            if (totalLoanAmount <= 0)
            {
                showLoanAmountError();
                return;
            }

            int numYears = loanTerm / 12;
            int totalInterest = Math.round(numYears * apr);

            totalLoanAmount += totalInterest;
            int monthlyPayment = (totalLoanAmount / loanTerm);

            showSuccessAndReturn(monthlyPayment, downPayment);
        }
    }

    private void showError(int index)
    {
        String errorMsg = getString(R.string.value_required);
        switch (index)
        {
            case 0:
                _etMSRP.setError(errorMsg);
                break;
            case 1:
                _etDownPayment.setError(errorMsg);
                break;
            case 2:
                _etLoanTerm.setError(errorMsg);
                break;
            case 3:
                _etAPR.setError(errorMsg);
                break;
            default:
                break;
        }
    }

    private void showLoanAmountError()
    {
        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.custom_error_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.error);
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.setView(dialogView);

        final TextView errorMsg = (TextView) dialogView
                .findViewById(R.id.tv_error_message);
        errorMsg.setText(getString(R.string.loan_amount_error));

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                dialog.cancel();
                            }
                        });

        // Create and show dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showSuccessAndReturn(int monthlyPayment, int downPayment)
    {
        Intent intent = new Intent(this, MainActivity.class);

        // Add data
        intent.putExtra(Extra.VEHICLE_NAME, _vehicleName);
        intent.putExtra(Extra.MONTHLY_PAYMENT, Integer.toString(monthlyPayment));
        intent.putExtra(Extra.DOWN_PAYMENT, Integer.toString(downPayment));

        // Return to main activity
        setResult(RESULT_OK, intent);
        finish();
    }
}
