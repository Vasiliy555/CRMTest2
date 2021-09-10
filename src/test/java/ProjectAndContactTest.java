
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;


public class ProjectAndContactTest extends WebDriverSettings{

    @Test
    public void testProject() {
        driver.get("https://crm.geekbrains.space/user/login");
        driver.findElement(By.name("_username")).sendKeys("Applanatest1");
        driver.findElement(By.name("_password")).sendKeys("Student2020!");
        Assertions.assertEquals(driver.findElement(By.cssSelector(".title")).getText(), ("Логин"));
        Assertions.assertEquals(driver.findElement(By.name("_username")).
                getAttribute("value"), "Applanatest1");
        Assertions.assertEquals(driver.findElement(By.name("_password")).
                getAttribute("value"), "Student2020!");
        Assertions.assertTrue(driver.findElement(By.id("_submit")).isDisplayed());
        driver.findElement(By.id("_submit")).click();

        WebElement element = driver.findElement(By.linkText("Проекты"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();

        driver.findElement(By.xpath("//span[contains(.,'Мои проекты')]")).click();
        driver.findElement(By.linkText("Создать проект")).click();
        Random random = new Random();
        int n = random.nextInt(100) + 1;
        driver.findElement(By.name("crm_project[name]")).sendKeys("HomeworkRe1" + n);
        driver.findElement(By.xpath("//span[contains(text(),'Укажите организацию')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Alya')]")).click();

        WebElement businessUnit = driver.findElement(By.name("crm_project[businessUnit]"));
        businessUnit.findElement(By.xpath("//option[. = 'Research & Development']")).click();

        WebElement curator = driver.findElement(By.name("crm_project[curator]"));
        curator.findElement(By.xpath("//option[. = 'Юзеров Юзер']")).click();

        WebElement rp = driver.findElement(By.name("crm_project[rp]"));
        rp.findElement(By.xpath("//option[. = 'Корыстин Василий']")).click();

        WebElement administrator = driver.findElement(By.name("crm_project[administrator]"));
        administrator.findElement(By.xpath("//option[. = 'Ямутина Вера']")).click();

        WebElement selectElement = driver.findElement(By.name("crm_project[manager]"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByVisibleText("Чернецкий Евгений");

        WebElement contact = driver.findElement(By.xpath("//select[@name=\"crm_project[contactMain]\"]"));
        Select selectContact = new Select(contact);
        selectContact.selectByVisibleText("Иванов Петр");

        driver.findElement(By.xpath("//button[contains(text(),\'Сохранить и закрыть\')]")).click();
    }
    @Test
    public void testContact() {
        WebElement element = driver.findElement(By.linkText("Контрагенты"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
        driver.findElement(By.linkText("Контактные лица")).click();
        driver.findElement(By.linkText("Создать контактное лицо")).click();
        driver.findElement(By.name("crm_contact[lastName]")).sendKeys("Alexeev");
        driver.findElement(By.name("crm_contact[firstName]")).sendKeys("Alex");
        driver.findElement(By.xpath("//span[contains(text(),'Укажите организацию')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Alya')]")).click();
        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("Director");
        Assertions.assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).isDisplayed());

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//*[text()='Контактное лицо сохранено']")).isDisplayed());

    }
}
