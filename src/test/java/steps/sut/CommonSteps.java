package steps.sut;

import helpers.SoftAssert;
import io.cucumber.java.en.Then;
import objects.frame.Context;
import objects.sut.CommonSutPage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonSteps {

    /*
    Again, lazy-instantiate the page object, and share it throughout
     */
    private CommonSutPage myPage = null;
    private CommonSutPage getMyPage() {
        if (myPage == null)
            myPage = new CommonSutPage(Context.defaultActor.getDriver());
        return myPage;
    }

    @Then("the page is fully drawn")
    public void thePageIsFullyDrawn() {
        // It may be desirable to enhance this if this is used in all seriousness.
        // Currently, it is relying on the object instantiation being proved by the existence of the diagnostic element
        Assert.assertNotNull(getMyPage());
    }

    @Then("all images are well formed")
    public void allImagesAreWellFormed() {
        helpers.SoftAssert sa = new SoftAssert("Set of all images on the page");
        List<WebElement> imgList = getMyPage().listImgTags();

        final int numImgs = imgList.size();

        for (int i = 0; i < numImgs; i++) {
            sa.assertTrue(
                    getMyPage().browserShowsImage(imgList.get(i)),
                    "Image number " + i + "does not appear to be well formed");
        }
        sa.assertAll();

    }

}
