package feature;

import basetest.BaseTest;
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
        createProductAndManufacturingOrderSteps.iLoginToHomePage("user@codechallenge.app", "123456");
        createProductAndManufacturingOrderSteps.iNavigateToCreateProductPage();
        createProductAndManufacturingOrderSteps.iCreateProduct("[TESTDATA]ProductName" + getRoundId());
        createProductAndManufacturingOrderSteps.iUpdateProductQuantity("10");
        createProductAndManufacturingOrderSteps.iNavigateToCreateManufacturingOrderPage();
        createProductAndManufacturingOrderSteps.iSelectProductNameOfOrder("[TESTDATA]ProductName" + getRoundId());
        createProductAndManufacturingOrderSteps.iAddConsumeComponentQuantity("productname", "100");
        createProductAndManufacturingOrderSteps.iChangeOrderStatusToConfirm();
        createProductAndManufacturingOrderSteps.iChangeOrderStatusToDoneAndSave();
        createProductAndManufacturingOrderSteps.iShouldBePresentedCreatedOrderOnManufacturingListOfInventory("[TESTDATA]ProductName" + getRoundId(), "Done");
    }


}
