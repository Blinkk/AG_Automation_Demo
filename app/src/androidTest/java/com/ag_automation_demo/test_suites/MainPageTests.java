package com.ag_automation_demo.test_suites;

import android.support.test.rule.ActivityTestRule;

import com.ag_automation_demo.Assert;
import com.ag_automation_demo.MainActivity;
import com.ag_automation_demo.R;
import com.ag_automation_demo.page_objects.CalculatorPage;
import com.ag_automation_demo.page_objects.MainPage;

import org.junit.Rule;
import org.junit.Test;

/**
 * Main page specific tests
 */

public class MainPageTests
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void openNewEntryDialog()
    {
        MainPage mainPage = new MainPage();
        mainPage.clickNewEntryFab();

        Assert.viewDisplayedWithId(R.id.ll_dialog);
        Assert.viewDisplayedWithId(R.id.et_input);
    }

    @Test
    public void cancelNewEntry()
    {
        MainPage mainPage = new MainPage();
        mainPage
                .clickNewEntryFab()
                .cancelNewEntryDialog();

        Assert.viewDoesNotExistWithId(R.id.ll_dialog);
    }

    @Test
    public void confirmNewValidEntry()
    {
        MainPage mainPage = new MainPage();
        CalculatorPage calculatorPage = mainPage
                .clickNewEntryFab()
                .addNewEntryName("Test")
                .confirmNewEntryDialog();

        calculatorPage.isLoadSuccessful();
    }

    @Test
    public void confirmNewInvalidEntry()
    {
        MainPage mainPage = new MainPage();
        mainPage
                .clickNewEntryFab()
                .confirmNewEntryDialog();

        Assert.errorDisplayedWithTextOnInputWithId(R.id.et_input, R.string.value_required);
    }
}
