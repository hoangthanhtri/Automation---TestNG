package stepdefinitions;

import org.testng.Assert;
import pageobjects.BasePage;
import pageobjects.home.HomePage;
import pageobjects.home.LoginPage;
import pageobjects.inventory.*;
import pageobjects.manufacturing.CreateOrderPage;
import pageobjects.manufacturing.ListOrdersPage;


public class CreateProductAndManufacturingOrderSteps extends BasePage {

    private final LoginPage loginPage;
    private final HomePage homePage;
    private final CreateProductPage createProductPage;
    private final InventoryOverviewPage inventoryOverviewPage;
    private final ListProductsPage listProductsPage;
    private final UpdateProductQuantityPage updateProductQuantityPage;
    private final ListOrdersPage listOrdersPage;
    private final CreateOrderPage createOrderPage;
    private final InventoryOrderListPage inventoryOrderListPage;

    public CreateProductAndManufacturingOrderSteps() {
        this.loginPage = new LoginPage();
        this.homePage = new HomePage();
        this.createProductPage = new CreateProductPage();
        this.inventoryOverviewPage = new InventoryOverviewPage();
        this.listProductsPage = new ListProductsPage();
        this.listOrdersPage = new ListOrdersPage();
        this.createOrderPage = new CreateOrderPage();
        this.inventoryOrderListPage = new InventoryOrderListPage();
        this.updateProductQuantityPage = new UpdateProductQuantityPage();
    }

    public void iLoginToHomePage(String email, String password) {

        LoginPage.navigateToLoginPage();
        LoginPage.inputEmailTextbox(email);
        LoginPage.inputPasswordTextbox(password);
        LoginPage.clickLoginButton();
    }

    public void iNavigateToCreateProductPage() {
        homePage.clickInventoryButton();
        inventoryOverviewPage.selectDropdownListOption("Products");
        listProductsPage.clickCreateProductButton();
    }

    public void iCreateProduct(String productName) {
        createProductPage.inputProductNameTextbox(productName);
        createProductPage.clickSaveButton();
    }

    public void iUpdateProductQuantity(String inputValue) {
        createProductPage.clickUpdateQuantityButton();
        updateProductQuantityPage.clickCreateButton();
        updateProductQuantityPage.inputcountedQuantityTextbox(inputValue);
        updateProductQuantityPage.clickSaveButton();
    }

    public void iNavigateToCreateManufacturingOrderPage() {
        updateProductQuantityPage.clickBackToHomePageButton();
        homePage.clickManufacturingButton();
        listOrdersPage.clickCreateOrderButton();
    }

    public void iSelectProductNameOfOrder(String productName) {
        createOrderPage.searchAndSelectProductNameOrderByIndex(productName, 0);
    }

    public void iAddConsumeComponentQuantity(String componentName, String consumeValue) {
        createOrderPage.clickAddLineText();
        createOrderPage.searchAndSelectProductComponent(componentName);
        createOrderPage.inputConsumedComponent(consumeValue);
    }

    public void iChangeOrderStatusToConfirm() {
        createOrderPage.clickConfirmOrderButton();
    }

    public void iChangeOrderStatusToDoneAndSave() {
        createOrderPage.clickMarkAsDoneOrderButton();
        createOrderPage.clickApplyButton();
    }

    public void iShouldBePresentedCreatedOrderOnManufacturingListOfInventory(String expectedProductName, String expectedState) {
        createOrderPage.clickBackToHomePageButton();
        homePage.clickInventoryButton();
        inventoryOverviewPage.clickManufacturingLabel();
        String referenceId = createOrderPage.getManufacturingReferenceLabel();
        InventoryOrderListPage.Order inventoryOrder = inventoryOrderListPage.getOrderListDataByReference(referenceId);

        Assert.assertTrue(inventoryOrder.getProductName().equalsIgnoreCase(expectedProductName) && inventoryOrder.getState().equalsIgnoreCase(expectedState));
    }

}
