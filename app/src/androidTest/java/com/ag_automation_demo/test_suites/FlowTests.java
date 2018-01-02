package com.ag_automation_demo.test_suites;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ag_automation_demo.Assert;
import com.ag_automation_demo.MainActivity;
import com.ag_automation_demo.R;
import com.ag_automation_demo.page_objects.CalculatorPage;
import com.ag_automation_demo.page_objects.MainPage;
import com.ag_automation_demo.utility.Utility;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Tests for flow of application as opposed to specific page
 */

@RunWith(AndroidJUnit4.class)
public class FlowTests
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void addNewValidEntry()
    {
        String vehicleName = "Smoke Test - " +
                Utility.generateRandomInteger(0, 5000);

        MainPage mainPage = new MainPage();
        CalculatorPage calculatorPage = mainPage
                .clickNewEntryFab()
                .addNewEntryName(vehicleName)
                .confirmNewEntryDialog();

        // Save entry with default values (randomized msrp)
        calculatorPage
                .clickSaveEntry();

        Assert.viewDisplayedWithIdAndText(R.id.tv_vehicle_name, vehicleName);
        Assert.viewDisplayedWithId(R.id.tv_loan_info);
    }
}
