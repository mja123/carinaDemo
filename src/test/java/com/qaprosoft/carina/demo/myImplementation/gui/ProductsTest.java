package com.qaprosoft.carina.demo.myImplementation.gui;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.myImplementation.gui.components.NavBarPage;
import com.qaprosoft.carina.demo.myImplementation.gui.exceptions.ElementNotFoundException;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.AbstractCategoryPage;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.HomePage;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.ProductPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

import static com.qaprosoft.carina.demo.myImplementation.gui.components.ECategories.*;

public class ProductsTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private HomePage homePage = null;

    @BeforeMethod
    public void openPage() {
        homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page isn't opened");
    }
    //initElement
    @Test
    @MethodOwner(owner = "mja123")
    public void compareInstallmentsTest() {
        NavBarPage navBar = homePage.getNavBar();

        try {
            AbstractCategoryPage categoryPage = navBar.getCategory(COMPUTING);
            ProductPage productPage = categoryPage.selectProduct("20646");
            Assert.assertEquals(productPage.pricePerInstallment("12"), "12 cuotas de $4870");

        } catch (ElementNotFoundException error) {
            LOGGER.error(error.getLocalizedMessage());
        }
    }
}
