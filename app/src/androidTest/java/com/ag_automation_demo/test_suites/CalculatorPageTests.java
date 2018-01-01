package com.ag_automation_demo.test_suites;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ag_automation_demo.Assert;
import com.ag_automation_demo.CalculatorActivity;
import com.ag_automation_demo.R;
import com.ag_automation_demo.page_objects.CalculatorPage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Calculator page specific tests
 */

@RunWith(AndroidJUnit4.class)
public class CalculatorPageTests
{
    @Rule
    public ActivityTestRule<CalculatorActivity> mActivityRule = new ActivityTestRule<>(
            CalculatorActivity.class);

    @Test
    public void editVehicleName()
    {
        String vehicleName = "Test";

        CalculatorPage calculatorPage = new CalculatorPage();
        calculatorPage
                .clickEditVehicleName()
                .modifyVehicleName(vehicleName)
                .clickSaveVehicleName();

        Assert.viewDisplayedWithIdAndText(R.id.tv_current_vehicle, vehicleName);
    }

    @Test
    public void verifyLoanAmountErrorDisplayed()
    {
        CalculatorPage calculatorPage = new CalculatorPage();

        calculatorPage
                .modifyCalculation(CalculatorPage.CalcField.MSRP, "50000")
                .modifyCalculation(CalculatorPage.CalcField.DOWN_PAYMENT, "51000")
                .clickSaveEntry();

        Assert.viewDisplayedWithId(R.id.tv_error_message);
    }

    @Test
    public void verifyCalcFieldErrorsDisplayed()
    {
        CalculatorPage calculatorPage = new CalculatorPage();

        calculatorPage
                .modifyCalculation(CalculatorPage.CalcField.MSRP, "")
                .modifyCalculation(CalculatorPage.CalcField.DOWN_PAYMENT, "")
                .modifyCalculation(CalculatorPage.CalcField.LOAN_TERM, "")
                .modifyCalculation(CalculatorPage.CalcField.APR, "")
                .clickSaveEntry();

        Assert.errorDisplayedWithTextOnInputWithId(R.id.et_msrp, R.string.value_required);
        Assert.errorDisplayedWithTextOnInputWithId(R.id.et_down_payment, R.string.value_required);
        Assert.errorDisplayedWithTextOnInputWithId(R.id.et_loan_term, R.string.value_required);
        Assert.errorDisplayedWithTextOnInputWithId(R.id.et_apr, R.string.value_required);
    }
}
