package com.ag_automation_demo.page_objects;

/**
 * Define common page object functionality
 */

interface IPageObject
{
    // This is the only function that should have an assertion within a page object
    // as a quick way to check that a page is loaded after a set of actions
    IPageObject isLoadSuccessful();
}
