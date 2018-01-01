package com.ag_automation_demo.page_objects;

import android.support.annotation.IdRes;

import com.ag_automation_demo.Assert;
import com.ag_automation_demo.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Calculator page object
 */

public class CalculatorPage implements IPageObject
{
    public enum CalcField { MSRP, DOWN_PAYMENT, LOAN_TERM, APR }

    public CalculatorPage()
    {

    }

    @Override
    public CalculatorPage isLoadSuccessful()
    {
        Assert.viewDisplayedWithId(R.id.tv_current_vehicle);

        return this;
    }

    public CalculatorPage clickEditVehicleName()
    {
        onView(withId(R.id.btn_edit_name))
                .perform(click());

        return this;
    }

    public CalculatorPage modifyVehicleName(String value)
    {
        onView(withId(R.id.et_current_vehicle))
                .perform(click(), clearText(), typeText(value));

        return this;
    }

    public CalculatorPage clickSaveVehicleName()
    {
        onView(withId(R.id.btn_save_name))
                .perform(click());

        return this;
    }

    public CalculatorPage modifyCalculation(CalcField field, String value)
    {
        @IdRes int resourceId;
        switch (field)
        {
            case MSRP:
                resourceId = R.id.et_msrp;
                break;
            case DOWN_PAYMENT:
                resourceId = R.id.et_down_payment;
                break;
            case LOAN_TERM:
                resourceId = R.id.et_loan_term;
                break;
            case APR:
                resourceId = R.id.et_apr;
                break;
            default:
                resourceId = -1;
                break;
        }

        onView(withId(resourceId))
                .perform(click(), clearText(), typeText(value));
        closeSoftKeyboard();

        return this;
    }

    public CalculatorPage clickSaveEntry()
    {
        onView(withId(R.id.btn_save_entry))
                .perform(click());

        return this;
    }
}
