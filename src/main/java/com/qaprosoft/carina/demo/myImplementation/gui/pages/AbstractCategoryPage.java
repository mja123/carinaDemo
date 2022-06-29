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
import java.util.Objects;

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

    public ProductPage selectProduct() throws ElementNotFoundException {
        //Select the first product in the page.
        if (productsLink.isEmpty()) {
            throw new ElementNotFoundException("There aren't products in the page");
        }
        LOGGER.info("" + productsLink.size());
        productsLink.get(0).click();
        return new ProductPage(driver);
    }

    public List<ExtendedWebElement> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<ExtendedWebElement> subCategories) {
        this.subCategories = subCategories;
    }

    public List<ExtendedWebElement> getProductsLink() {
        return productsLink;
    }

    public void setProductsLink(List<ExtendedWebElement> productsLink) {
        this.productsLink = productsLink;
    }

    public List<ExtendedWebElement> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<ExtendedWebElement> productsId) {
        this.productsId = productsId;
    }

    public HashMap<ExtendedWebElement, ExtendedWebElement> getProducts() {
        return products;
    }

    public void setProducts(HashMap<ExtendedWebElement, ExtendedWebElement> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCategoryPage that = (AbstractCategoryPage) o;
        return Objects.equals(subCategories, that.subCategories) && Objects.equals(productsLink, that.productsLink) && Objects.equals(productsId, that.productsId) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategories, productsLink, productsId, products);
    }
}
