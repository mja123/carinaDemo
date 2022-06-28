package com.qaprosoft.carina.demo.myImplementation.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.myImplementation.gui.components.NavBarPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

////div[contains(@class,"fafafa")]//small
public class HomePage extends AbstractPage {


    @FindBy(id="marcas-header-2")
    private NavBarPage navBar;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public NavBarPage getNavBar() {
        return navBar;
    }

    public void setNavBar(NavBarPage navBar) {
        this.navBar = navBar;
    }
}
