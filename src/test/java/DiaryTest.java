import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

public class DiaryTest extends WebDriverSettings {
    private String url = "https://diary.ru/";

    @Test
    void DiaryNewRegistrationTest() {
        driver.get(url);
        driver.findElement(By.linkText("Регистрация")).click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("registration"));

        Random random = new Random();
        int n = random.nextInt(100) + 1;
        driver.findElement(By.id("signupform-username")).sendKeys("Qwelollipop" + n);
        driver.findElement(By.id("signupform-email")).sendKeys("Qwelollipop" + n + "@mail.com");
        driver.findElement(By.id("chk_box_user_confirm")).click();
        Assertions.assertTrue(driver.findElement(By.id("signup_btn")).isDisplayed());
        driver.findElement(By.id("signup_btn")).click();
        driver.findElement(By.id("newblogform-title")).click();
        driver.findElement(By.id("newblogform-title")).sendKeys("NewBlog");
        driver.findElement(By.name("new-blogs-button")).click();
    }

    @Test
    void DiaryEmptyRegistration() {
        driver.get(url);
        driver.findElement(By.linkText("Регистрация")).click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("registration"));
        driver.findElement(By.id("signup_btn")).click();

        WebElement userName = driver.findElement(By.id("signupform-username"));
        WebElement userNameParent = userName.findElement(By.xpath(".."));
        Assertions.assertEquals(userNameParent.findElement(By.cssSelector("p")).getText(),
                "Необходимо заполнить «Логин».");

        WebElement emailName = driver.findElement(By.id("signupform-email"));
        WebElement emailNameParent = emailName.findElement(By.xpath(".."));
        Assertions.assertEquals(emailNameParent.findElement(By.cssSelector("p")).getText(),
                "Необходимо заполнить «E-mail».");


    }
}
