package com.fca.calidad.funcionales;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;

public class TestFacebook {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  JavascriptExecutor js;
	  @Before
	  public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    js = (JavascriptExecutor) driver;
	  }

	  @Test
	  public void testFacebook() throws Exception {
		    driver.get("https://www.facebook.com/");
		    driver.findElement(By.id("email")).click();
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("gkiju.kaec@gmail.com");
		    driver.findElement(By.id("pass")).click();
		    driver.findElement(By.id("pass")).clear();
		    driver.findElement(By.id("pass")).sendKeys("hola1234");
		    driver.findElement(By.name("login")).click();
		    //si se traba ponerle pausas, bueno si no pasa
		    WebElement error = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/form/div/div[1]/div[2]"));
		    assertEquals("El correo electrónico que ingresaste no está conectado a una cuenta. Encuentra tu cuenta e inicia sesión.", error.getText());
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}