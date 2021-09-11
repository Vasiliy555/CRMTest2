import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class DiaryTest extends WebDriverSettings {

    @BeforeEach
    void setUrl() {
        driver.get("https://diary.ru/");
        driver.findElement(By.linkText("Регистрация")).click();
    }

    @Test
    void DiaryNewRegistrationTest() {

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
        driver.findElement(By.id("drop_right_menu")).click();
        driver.findElement(By.linkText("Выход")).click();
    }

    @Test
    void DiaryEmptyRegistration() {

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

    @Test
    void incorrectUserName() {
        driver.findElement(By.id("signupform-username")).sendKeys("QweЛоЛ");
        driver.findElement(By.id("signupform-email")).sendKeys(("asd@gmail.com"));
        driver.findElement(By.id("signup_btn")).click();
        WebElement userName = driver.findElement(By.id("signupform-username"));
        WebElement userNameParent = userName.findElement(By.xpath(".."));
        Assertions.assertEquals(userNameParent.findElement(By.cssSelector("p")).getText(),
                "Использование одновременно русских и латинских символов недопустимо");
    }

    @Test
    void incorrectEmail() {
        driver.findElement(By.id("signupform-username")).sendKeys("Qwetyunonl");
        driver.findElement(By.id("signupform-email")).sendKeys(("asd@gmail"));
        driver.findElement(By.id("signup_btn")).click();
        WebElement emailName = driver.findElement(By.id("signupform-email"));
        WebElement emailNameParent = emailName.findElement(By.xpath(".."));
        Assertions.assertEquals(emailNameParent.findElement(By.cssSelector("p")).getText(),
                "Значение «E-mail» не является правильным email адресом.");
    }
}
