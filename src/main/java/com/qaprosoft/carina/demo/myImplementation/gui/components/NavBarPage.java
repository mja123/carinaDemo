package com.qaprosoft.carina.demo.myImplementation.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.myImplementation.gui.pages.*;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NavBarPage extends AbstractUIObject {

    @FindBy(id = "#navbarDropdownMenuLink")
    private ExtendedWebElement products;
    @FindBy(xpath = "//li[contains(@class,'mega-dropdown')]")
    private List<ExtendedWebElement> categories;
    @FindBy(xpath = "//a[contains(@class, 'todo-en')]")
    private List<ExtendedWebElement> categoryProducts;

    public NavBarPage(WebDriver driver) {
        super(driver);
    }

    public ICategoryPage getCategory(String categoryTarget) {
        for (ExtendedWebElement category : categories) {
            if (category.getText().equalsIgnoreCase(categoryTarget)) {
                category.click();
                return getAllProducts(category);
            }
        }
        return null;
    }

    public ICategoryPage getAllProducts(ExtendedWebElement category) {

        for (ExtendedWebElement allProducts : categoryProducts) {
            if (allProducts.getText().contains(category.getText())) {
                allProducts.click();
                break;
            }
        }

        switch (category.getText()) {
            case "LIMPIEZA ELECTRONICA":
                return new ElectronicCleaningPage(driver);
            case "COMPONENTES DE HW":
                return new HardwarePage(driver);
            case "PERIFERICOS Y ACCESORIOS":
                return new PeripheralPage(driver);
            case "COMPUTACION":
                return new ComputingPage(driver);
            case "CELULARES Y TABLETS":
                return new CellphonesAndTabletsPage(driver);
            default:
                return new TvAudioVideoPage(driver);
        }
    }

}
