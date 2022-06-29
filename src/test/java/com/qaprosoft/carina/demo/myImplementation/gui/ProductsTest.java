package com.qaprosoft.carina.demo.myImplementation.gui;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.myImplementation.gui.components.NavBarPage;
import com.qaprosoft.carina.demo.myImplementation.gui.components.SearchInputPage;
import com.qaprosoft.carina.demo.myImplementation.gui.exceptions.ElementNotFoundException;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.AbstractCategoryPage;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.HomePage;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.ProductPage;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.SearchPage;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

import static com.qaprosoft.carina.demo.myImplementation.gui.components.ECategories.*;

public class ProductsTest implements IAbstractTest {
    private HomePage homePage = null;

    @BeforeClass
    public void openPage() {
        homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page isn't opened");
    }

    @Test
    @MethodOwner(owner = "mja123")
    public void compareInstallmentsTest() throws ElementNotFoundException {
        NavBarPage navBar = homePage.getNavBar();

        AbstractCategoryPage categoryPage = navBar.getCategory(COMPUTING);
        ProductPage productPage = categoryPage.selectProduct("20646");
        Assert.assertEquals(productPage.pricePerInstallment("12"), "12 cuotas de $4.870");

    }

    @Test(expectedExceptions = ElementNotFoundException.class)
    @MethodOwner(owner = "mja123")
    public void searchInputProductNotFoundTest() throws ElementNotFoundException {
        SearchInputPage search = homePage.getSearchInput();
        search.searchProduct("asdf");
        Assert.fail();

    }

    @Test
    @MethodOwner(owner = "mja123")
    public void searchInputProductTest() throws ElementNotFoundException {
        SearchInputPage search = homePage.getSearchInput();
        SearchPage searchPage = search.searchProduct("cable");
        Assert.assertFalse(searchPage.getProductsId().isEmpty());
        //Assert.assertTrue(searchPage.isPageOpened(), "Search page is not opened");
    }

    @Test
    @MethodOwner(owner = "mja123")
    public void selectProductFromSearchInputTest() throws ElementNotFoundException {
        SearchInputPage search = homePage.getSearchInput();
        SearchPage searchPage = search.searchProduct("funda");

        ProductPage productPage = searchPage.selectProduct();
        productPage.open();
        Assert.assertTrue(productPage.isPageOpened(), "Product page is not opened");
    }

    @Test
    @MethodOwner(owner = "mja123")
    public void compareInstallmentsFromSearchInputTest() throws ElementNotFoundException {
        SearchInputPage search = homePage.getSearchInput();
        SearchPage searchProduct = search.searchProduct("notebook");

        ProductPage products = searchProduct.selectProduct();
        System.out.println("asdfas");
        Assert.assertEquals(products.pricePerInstallment("18"),"18 cuotas de $26.67");
    }
}
