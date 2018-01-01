package com.ag_automation_demo.test_suites;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ag_automation_demo.Assert;
import com.ag_automation_demo.MainActivity;
import com.ag_automation_demo.R;
import com.ag_automation_demo.page_objects.CalculatorPage;
import com.ag_automation_demo.page_objects.MainPage;

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

//    @Before
//    public void initValidString() {
//        // Specify a valid string.
//        mStringToBetyped = "Espresso";
//    }

    @Test
    public void addNewValidEntry()
    {
        MainPage mainPage = new MainPage();
        CalculatorPage calculatorPage = mainPage
                .addNewEntry();

        calculatorPage
                .clickSaveEntry();

        Assert.viewDisplayedWithId(R.id.tv_vehicle_name);
    }
}
