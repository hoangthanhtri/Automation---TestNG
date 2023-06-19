package feature;

import basetest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stepdefinitions.CreateProductAndManufacturingOrderSteps;

public class CreateProductAndManufacturingOrder extends BaseTest {
    private CreateProductAndManufacturingOrderSteps createProductAndManufacturingOrderSteps;
    @DataProvider(name = "testData")
    public Object[][] testData() {
        return new Object[][]{
                {"John"}
        };
    }

    @BeforeClass
    public void setUp() {
        createProductAndManufacturingOrderSteps = new CreateProductAndManufacturingOrderSteps();
    }

    @Test(dataProvider = "testData", threadPoolSize = 1, invocationCount = 1, timeOut = 10000)
    public void LoginAccountSucceed(String name) {
//        createProductAndManufacturingOrderSteps.iLoginToHomePage("user@codechallenge.a", "123456");
//        createProductAndManufacturingOrderSteps.iNavigateToCreateProductPage();
//        createProductAndManufacturingOrderSteps.iCreateProduct("[TESTDATA]ProductName" + getRoundId());
//        createProductAndManufacturingOrderSteps.iUpdateProductQuantity("10");
//        createProductAndManufacturingOrderSteps.iNavigateToCreateManufacturingOrderPage();
//        createProductAndManufacturingOrderSteps.iSelectProductNameOfOrder("[TESTDATA]ProductName" + getRoundId());
//        createProductAndManufacturingOrderSteps.iAddConsumeComponentQuantity("productname", "100");
//        createProductAndManufacturingOrderSteps.iChangeOrderStatusToConfirm();
//        createProductAndManufacturingOrderSteps.iChangeOrderStatusToDoneAndSave();
//        createProductAndManufacturingOrderSteps.iShouldBePresentedCreatedOrderOnManufacturingListOfInventory("[TESTDATA]ProductName" + getRoundId(), "Done");

    }

    @Test
    public void LoginAccountSucceed1() {
//        createProductAndManufacturingOrderSteps.iLoginToHomePage("user@codechallenge.a", "123456");
//        createProductAndManufacturingOrderSteps.iNavigateToCreateProductPage();
//        createProductAndManufacturingOrderSteps.iCreateProduct("[TESTDATA]ProductName" + getRoundId());
//        createProductAndManufacturingOrderSteps.iUpdateProductQuantity("10");
//        createProductAndManufacturingOrderSteps.iNavigateToCreateManufacturingOrderPage();
//        createProductAndManufacturingOrderSteps.iSelectProductNameOfOrder("[TESTDATA]ProductName" + getRoundId());
//        createProductAndManufacturingOrderSteps.iAddConsumeComponentQuantity("productname", "100");
//        createProductAndManufacturingOrderSteps.iChangeOrderStatusToConfirm();
//        createProductAndManufacturingOrderSteps.iChangeOrderStatusToDoneAndSave();
//        createProductAndManufacturingOrderSteps.iShouldBePresentedCreatedOrderOnManufacturingListOfInventory("[TESTDATA]ProductName" + getRoundId(), "Done");

    }


}
