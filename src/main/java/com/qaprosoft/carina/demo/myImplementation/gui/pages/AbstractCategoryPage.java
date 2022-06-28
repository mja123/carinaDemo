package com.qaprosoft.carina.demo.myImplementation.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.myImplementation.gui.exceptions.ElementNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractCategoryPage extends AbstractPage {

    private final static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @FindBy(xpath = "//div[contains(@class, 'panel-body')]//li[@class='']")
    protected List<ExtendedWebElement> subCategories;
    @FindBy(xpath = "//div[contains(@class, 'view')]//a")
    protected List<ExtendedWebElement> productsLink;
    @FindBy(xpath = "//span[contains(@class, 'articulo_field')]")
    protected List<ExtendedWebElement> productsId;


    //Key: prodcutId, value: productLink
    //(try to simulate //div[contains(@class, 'view')]//a | //span[contains(@class, 'articulo_field')])
    protected HashMap<ExtendedWebElement, ExtendedWebElement> products;

    public AbstractCategoryPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage selectProduct(String id) throws ElementNotFoundException {
        LOGGER.info("Selecting product with id: " + id);
        int counter = 0;
        for (ExtendedWebElement productId : productsId) {
            if (productId.getText().equals(id)) {
                productsLink.get(counter).click();
                return new ProductPage(driver);
            }
            counter++;
        }
        throw new ElementNotFoundException("Product " + id + " doesn't exist");
    }

}
