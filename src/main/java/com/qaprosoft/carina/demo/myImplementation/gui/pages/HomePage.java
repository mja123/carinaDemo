package com.qaprosoft.carina.demo.myImplementation.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

////div[contains(@class,"fafafa")]//small
public class HomePage extends AbstractPage {

    @FindBy(id="marcas-header-2")
    private ExtendedWebElement navBar;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
