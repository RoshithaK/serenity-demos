package net.thucydides.showcase.junit.features.display_product;

import com.gargoylesoftware.htmlunit.javascript.host.Screen;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.photography.Photographer;
import net.serenitybdd.core.photography.ScreenshotPhoto;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.showcase.junit.model.ListingItem;
import net.thucydides.showcase.junit.steps.serenity.BuyerSteps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(SerenityRunner.class)
public class DisplayProductDetailsTest {
    @Managed
    WebDriver driver;

    @Steps
    BuyerSteps buyer;

    @Test
    @WithTags( {
            @WithTag(type="priority",name="medium"),
            @WithTag(type="component",name="product")

    })

    public void display_product_details_from_the_search_list() throws IOException, InterruptedException {
        Path path = Paths.get("/home/yamstranger/dev/src/serenity/serenity-all/serenity-demos/junit-webtests/images");
        Files.createDirectories(path);
        List<ScreenshotPhoto> photos = new ArrayList<>(3);

        Photographer photographer = new Photographer();
        buyer.opens_home_page();
        photos.add(photographer.takesAScreenshot()
            .with(driver)
            .andSaveToDirectory(path));

        buyer.searches_by_keyword("Docking station");
        photos.add(photographer.takesAScreenshot()
            .with(driver)
            .andSaveToDirectory(path));


        ListingItem selectedListingItem = buyer.selects_listing(1);

        buyer.should_see_product_details_for(selectedListingItem);
        photos.add(photographer.takesAScreenshot()
            .with(driver)
            .andSaveToDirectory(path));

        Thread.currentThread().sleep(5000); //screenshots are saved in different thread
        Assert.assertEquals(photos.size(), 3);
        for (ScreenshotPhoto photo : photos) {
            Assert.assertTrue(Files.exists(photo.getPathToScreenshot()));
        }
    }
}
