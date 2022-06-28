package com.qaprosoft.carina.demo.myImplementation.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.myImplementation.gui.exceptions.ElementNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import static org.checkerframework.checker.nullness.Opt.isPresent;

public class ProductPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @FindBy(xpath = "//h5[@class='my-0']//b | //h5[@class='my-0']//small")
    private List<ExtendedWebElement> prices;
/*    @FindBy(xpath = "//h5[@class='my-0']//b")
    private List<ExtendedWebElement> installments;
    @FindBy(xpath = "//h5[@class='my-0']//small")
    private List<ExtendedWebElement> totalPrices;*/

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String pricePerInstallment(String installment) throws ElementNotFoundException {
        for (ExtendedWebElement price : prices) {
            if (price.getText().contains(installment)) {
                return price.getText();
            }
        }
        throw new ElementNotFoundException("There isn't " + installment + " installments");
        /*Optional<ExtendedWebElement> installmentData = installments.stream()
                .filter(i -> i.getText().contains(installment))
                .findFirst();

        assertElementPresent(installmentData.get());
        return installmentData.get().getText();*/
    }

    public String totalPrice(String installment) throws ElementNotFoundException {
        for (ExtendedWebElement price : prices) {
            if (price.getText().contains(installment)) {
                return prices.get(prices.indexOf(price)).getText();
            }
        }
        throw new ElementNotFoundException("There isn't " + installment + " installments");
        /*Optional<ExtendedWebElement> installmentData = installments.stream()
                .filter(i -> i.getText().contains(installment))
                .findFirst();
        assertElementPresent(installmentData.get());
        return installments.get(installments.indexOf(installmentData) + 1).getText();*/
    }


}
