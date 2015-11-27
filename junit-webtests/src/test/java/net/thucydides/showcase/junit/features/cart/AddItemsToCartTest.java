package net.thucydides.showcase.junit.features.cart;

import net.serenitybdd.core.photography.Darkroom;
import net.serenitybdd.core.photography.Photographer;
import net.serenitybdd.core.photography.ScreenshotPhoto;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.showcase.junit.model.ListingItem;
import net.thucydides.showcase.junit.steps.serenity.BuyerSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SerenityRunner.class)
public class AddItemsToCartTest {
    @Managed
    WebDriver driver;

    @Steps
    BuyerSteps buyer;

    @Test
    public void add_item_to_cart() throws IOException{
        // GIVEN
        buyer.opens_home_page();
//        Darkroom.isOpenForBusiness();
        Path path= Paths.get("/home/yamstranger/dev/src/serenity/serenity-all/serenity-demos/junit-webtests/images");
        Files.createDirectories(path);
/*        Photographer photographer = new Photographer();

        ScreenshotPhoto photo = photographer.takesAScreenshot()
            .with(driver)
            .andSaveToDirectory(path);*/
//        Darkroom.waitUntilClose();

        buyer.searches_by_keyword("docking station");
/*         photo = photographer.takesAScreenshot()
            .with(driver)
            .andSaveToDirectory(path);*/
        buyer.filters_by_local_region();
/*         photo = photographer.takesAScreenshot()
            .with(driver)
            .andSaveToDirectory(path);*/

        // WHEN
        ListingItem selectedItem = buyer.selects_listing(2);
        buyer.adds_current_listing_to_cart();

        // THEN
        buyer.should_see_item_in_cart(selectedItem);
        buyer.should_see_total_including_shipping_for(selectedItem);
    }
}

