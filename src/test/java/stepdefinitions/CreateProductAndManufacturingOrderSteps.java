package stepdefinitions;


import org.testng.Assert;
import pageobjects.BasePage;
import pageobjects.home.HomePage;
import pageobjects.home.LoginPage;
import pageobjects.inventory.*;
import pageobjects.manufacturing.CreateOrderPage;
import pageobjects.manufacturing.ListOrdersPage;


public class CreateProductAndManufacturingOrderSteps extends BasePage {
    private LoginPage loginPage;
    private HomePage homePage;
    private CreateProductPage createProductPage;
    private InventoryOverviewPage inventoryOverviewPage;
    private ListProductsPage listProductsPage;
    private UpdateProductQuantityPage updateProductQuantityPage;
    private ListOrdersPage listOrdersPage;
    private CreateOrderPage createOrderPage;
    private InventoryOrderListPage inventoryOrderListPage;

    public CreateProductAndManufacturingOrderSteps(LoginPage loginPage, HomePage homePage, CreateProductPage createProductPage, InventoryOverviewPage inventoryOverviewPage, ListProductsPage listProductsPage, UpdateProductQuantityPage updateProductQuantityPage, ListOrdersPage listOrdersPage, CreateOrderPage createOrderPage, InventoryOrderListPage inventoryOrderListPage) {
        this.loginPage = loginPage;
        this.homePage = homePage;
        this.createProductPage = createProductPage;
        this.inventoryOverviewPage = inventoryOverviewPage;
        this.listProductsPage = listProductsPage;
        this.updateProductQuantityPage = updateProductQuantityPage;
        this.listOrdersPage = listOrdersPage;
        this.createOrderPage = createOrderPage;
        this.inventoryOrderListPage = inventoryOrderListPage;
    }

    public void iLoginToHomePage(String email, String password) {
        loginPage.navigateToLoginPage();
        loginPage.inputEmailTextbox(email);
        loginPage.inputPasswordTextbox(password);
        loginPage.clickLoginButton();
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
