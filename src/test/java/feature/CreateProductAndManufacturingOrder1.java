package feature;

import basetest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stepdefinitions.CreateProductAndManufacturingOrderSteps;

public class CreateProductAndManufacturingOrder1 extends BaseTest {
    private CreateProductAndManufacturingOrderSteps createProductAndManufacturingOrderSteps;

    // Have to
    // Implement Log4j
    // Implement DB query and compare data
    // Add Plugin for Jenkins
    // Add emualation, size of browser
    // Data provide from Enum & CSV file
    @BeforeClass
    public void setUp() {
        createProductAndManufacturingOrderSteps = new CreateProductAndManufacturingOrderSteps();
    }

    @Test
    public void LoginAccountSucceed2() {
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
    public void LoginAccountSucceed3() {
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
