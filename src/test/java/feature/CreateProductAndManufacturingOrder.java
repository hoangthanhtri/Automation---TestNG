package feature;

import basetest.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.home.HomePage;
import pageobjects.home.LoginPage;
import pageobjects.inventory.*;
import pageobjects.manufacturing.CreateOrderPage;
import pageobjects.manufacturing.ListOrdersPage;
import stepdefinitions.CreateProductAndManufacturingOrderSteps;

public class CreateProductAndManufacturingOrder extends BaseTest {
    private CreateProductAndManufacturingOrderSteps createProductAndManufacturingOrderSteps;

    public CreateProductAndManufacturingOrder() {
        this.createProductAndManufacturingOrderSteps = new CreateProductAndManufacturingOrderSteps(
                new LoginPage(),
                new HomePage(),
                new CreateProductPage(),
                new InventoryOverviewPage(),
                new ListProductsPage(),
                new UpdateProductQuantityPage(),
                new ListOrdersPage(),
                new CreateOrderPage(),
                new InventoryOrderListPage());

    }

    @Test
    public void LoginAccountSucceed() {
        Reporter.log("Login to Home page");
        createProductAndManufacturingOrderSteps.iLoginToHomePage("user@codechallenge.app", "123456");
        Reporter.log("Navigate to Create product page");
        createProductAndManufacturingOrderSteps.iNavigateToCreateProductPage();
        Reporter.log("Create Product");
        createProductAndManufacturingOrderSteps.iCreateProduct("[TESTDATA]ProductName" + getRoundId());
        Reporter.log("Update product quantity = 10");
        createProductAndManufacturingOrderSteps.iUpdateProductQuantity("10");
        Reporter.log("Navigate to Create manufacturing order page");
        createProductAndManufacturingOrderSteps.iNavigateToCreateManufacturingOrderPage();
        Reporter.log("Input product name on order");
        createProductAndManufacturingOrderSteps.iSelectProductNameOfOrder("[TESTDATA]ProductName" + getRoundId());
        Reporter.log("Add consume components and quantity");
        createProductAndManufacturingOrderSteps.iAddConsumeComponentQuantity("productname", "100");
        Reporter.log("Change order status to confirmed");
        createProductAndManufacturingOrderSteps.iChangeOrderStatusToConfirm();
        Reporter.log("Change order status to done");
        createProductAndManufacturingOrderSteps.iChangeOrderStatusToDoneAndSave();
        Reporter.log("Validate created order information");
        createProductAndManufacturingOrderSteps.iShouldBePresentedCreatedOrderOnManufacturingListOfInventory("[TESTDATA]ProductName" + getRoundId(), "Done");

    }


}
