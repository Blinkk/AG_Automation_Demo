package com.ag_automation_demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom adapter for loan item list view
 */

public class LoanListAdapter extends ArrayAdapter<LoanItem>
{
    public LoanListAdapter(Context context, ArrayList<LoanItem> data)
    {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        LoanItem loanItem = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.loan_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvVehicleName = (TextView) convertView.findViewById(R.id.tv_vehicle_name);
            viewHolder.tvLoanInfo = (TextView) convertView.findViewById(R.id.tv_loan_info);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (loanItem != null)
        {
            // Populate the data into the template view using the data object
            viewHolder.tvVehicleName.setText(loanItem.vehicleName);
            viewHolder.tvLoanInfo.setText(loanItem.loanInfo);
        }

        return convertView;
    }

    private static class ViewHolder
    {
        TextView tvVehicleName;
        TextView tvLoanInfo;
    }
}
