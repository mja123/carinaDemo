package com.qaprosoft.carina.demo.myImplementation.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.myImplementation.gui.exceptions.ElementNotFoundException;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.AbstractCategoryPage;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.ProductPage;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.SearchPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class SearchInputPage extends AbstractUIObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @FindBy(xpath = "//input[@id='busqueda']")
    private ExtendedWebElement searchBar;
    @FindBy(xpath = "//button[contains(@class, 'btn-buscar')]")
    private ExtendedWebElement sendButton;
    @FindBy(xpath = "//div[@id='resultado']")
    private ExtendedWebElement resultNotFound;

    public SearchInputPage(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public SearchPage searchProduct(String productName) throws ElementNotFoundException {
        LOGGER.info("Searching '" + productName + "'...");
        searchBar.type(productName, 5);

        if (resultNotFound.getText().contains("No se han encontrado resultados para")) {
                throw new ElementNotFoundException("Product not found");
        }

        LOGGER.info("Product found and redirecting to the SearchPage");
        sendButton.click(5);

        return new SearchPage(driver);
    }
}
