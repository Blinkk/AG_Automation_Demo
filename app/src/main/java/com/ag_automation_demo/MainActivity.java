package com.ag_automation_demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ag_automation_demo.utility.Extra;
import com.ag_automation_demo.utility.Utility;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private final Context _context = this;
    private ArrayList<LoanItem> _loanItems;
    private LoanListAdapter _loanListAdapter;

    // Request codes
    public final int NEW_ENTRY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Inflate alert dialog xml
                LayoutInflater li = LayoutInflater.from(_context);
                View dialogView = li.inflate(R.layout.custom_dialog, null);
                final AlertDialog dialog = new AlertDialog.Builder(_context)
                        .setTitle(R.string.new_entry)
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(dialogView)
                        .setCancelable(false)
                        .setPositiveButton(R.string.ok, null)
                        .setNegativeButton(R.string.cancel, null)
                        .create();

                final TextInputEditText userInput = (TextInputEditText) dialogView
                        .findViewById(R.id.et_input);

                dialog.setOnShowListener(new DialogInterface.OnShowListener()
                {

                    @Override
                    public void onShow(DialogInterface dialogInterface)
                    {
                        Button buttonPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        buttonPositive.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                String userInputText = userInput.getText().toString();
                                if (Utility.isNullOrEmpty(userInputText))
                                {
                                    userInput.setError(getString(R.string.value_required));
                                    return;
                                }

                                Intent intent = new Intent(_context, CalculatorActivity.class);
                                intent.putExtra(Extra.VEHICLE_NAME, userInputText);
                                startActivityForResult(intent, NEW_ENTRY_REQUEST);

                                dialog.dismiss();
                            }
                        });

                        Button buttonNegative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        buttonNegative.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                dialog.cancel();
                            }
                        });
                    }
                });

                // Show dialog
                dialog.show();
            }
        });

        // Setup list view
        _loanItems = new ArrayList<>();
        ListView lvLoans = (ListView) findViewById(R.id.lv_loans);
        _loanListAdapter = new LoanListAdapter(this, _loanItems);
        lvLoans.setAdapter(_loanListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case NEW_ENTRY_REQUEST:
                    Bundle bundle = data.getExtras();
                    if (bundle != null)
                    {
                        String vehicleName = bundle.getString(Extra.VEHICLE_NAME);
                        String monthlyPayment = bundle.getString(Extra.MONTHLY_PAYMENT);
                        String downPayment = bundle.getString(Extra.DOWN_PAYMENT);
                        String loanInfo = String.format(getString(R.string.loan_info), monthlyPayment, downPayment);

                        _loanItems.add(new LoanItem(vehicleName, loanInfo));
                    }

                    _loanListAdapter.notifyDataSetChanged();

                    break;
            }
        }
    }
}
