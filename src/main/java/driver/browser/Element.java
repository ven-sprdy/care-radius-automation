package driver.browser;

import enums.LocatorType;
import org.openqa.selenium.By;

public class Element {

    private LocatorType locatorType;
    private By elementProperty;
    private String propertyName;

    public Element(LocatorType locatorType, By elementProperty, String propertyName) {
        setElementProperty(elementProperty);
        setLocatorType(locatorType);
        setPropertyName(propertyName);
    }

    public LocatorType getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(LocatorType locatorType) {
        this.locatorType = locatorType;
    }

    public By getElementProperty() {
        return elementProperty;
    }

    public void setElementProperty(By elementProperty) {
        this.elementProperty = elementProperty;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

//    public WebElement findElement() {
//        WebElement element = null;
//        switch(locatorType) {
//            case ID:
//                element = driver.findElement(By.id(propertyName));
//                break;
//            case NAME:
//                element = driver.findElement(By.name(propertyName));
//                break;
//            case LINKTEXT:
//                element = driver.findElement(By.linkText(propertyName));
//                break;
//            case PARTIALLINKTEXT:
//                element = driver.findElement(By.partialLinkText(propertyName));
//                break;
//            case TAGNAME:
//                element = driver.findElement(By.tagName(propertyName));
//                break;
//            case CLASSNAME:
//                element = driver.findElement(By.className(propertyName));
//                break;
//            case CSS:
//                element = driver.findElement(By.cssSelector(propertyName));
//                break;
//            case XPATH:
//                element = driver.findElement(By.xpath(propertyName));
//                break;
//            default:
//                element = null;
//                break;
//        }
//        return element;
//    }

}
