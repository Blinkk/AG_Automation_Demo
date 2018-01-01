package com.ag_automation_demo;

/**
 * Wrapper for loan item for placement in list view
 */

public class LoanItem
{
    public String vehicleName;
    public String loanInfo;

    public LoanItem(String vehicleName, String loanInfo)
    {
        this.vehicleName = vehicleName;
        this.loanInfo = loanInfo;
    }
}
