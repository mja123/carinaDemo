package com.qaprosoft.carina.demo.myImplementation.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.myImplementation.gui.exceptions.ElementNotFoundException;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.*;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Locale;

import static com.qaprosoft.carina.demo.myImplementation.gui.components.ECategories.*;

public class NavBarPage extends AbstractUIObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @FindBy(id = "#navbarDropdownMenuLink")
    private ExtendedWebElement products;
    @FindBy(xpath = "//li[contains(@class,'mega-dropdown')]/a")
    private List<ExtendedWebElement> categories;
    @FindBy(xpath = "//a[contains(@class, 'todo-en')]")
    private List<ExtendedWebElement> categoryProducts;

    public NavBarPage(WebDriver driver, SearchContext searchContext) {
        super(driver);
    }

    public AbstractCategoryPage getCategory(ECategories categoryTarget) throws ElementNotFoundException {

        int counter = 0;
        for (ExtendedWebElement category : categories) {
            LOGGER.info(category.getText());
            if (category.getText().equalsIgnoreCase(categoryTarget.getValue())) {
                category.click();
                return getAllProducts(category.getText(), counter);
            }
            counter++;
        }
        throw new ElementNotFoundException("Category " + categoryTarget + " doesn't exist");
    }

    private AbstractCategoryPage getAllProducts(String category, int categoryIndex) {
        ECategories categoryTarget = null;

        LOGGER.info(categoryProducts.get(categoryIndex).getText());
        categoryProducts.get(categoryIndex).click();
        /*for (ExtendedWebElement allProducts : categoryProducts) {
            LOGGER.info("Selecting allProducts: " + allProducts.toString() + ", " + category);

            if (allProducts.getText().contains(category.getText())) {
                LOGGER.info("Category found");
                allProducts.click();
                break;
            }
        }*/
        LOGGER.info(category);
        for (ECategories categoryValue : ECategories.values()) {
            if (category.equalsIgnoreCase(categoryValue.getValue())) {
                categoryTarget = categoryValue;
                LOGGER.info("ECategory: " + categoryValue);
            }
        }

        LOGGER.info("Selecting category page " + categoryTarget);
        switch (categoryTarget) {
            case ELECTRONIC_CLEANING:
                return new ElectronicCleaningPage(driver);
            case HARDWARE:
                return new HardwarePage(driver);
            case PERIPHERAL:
                return new PeripheralPage(driver);
            case COMPUTING:
                return new ComputingPage(driver);
            case CELLPHONES_TABLETS:
                return new CellphonesAndTabletsPage(driver);
            default:
                return new TvAudioVideoPage(driver);
        }
    }
}
