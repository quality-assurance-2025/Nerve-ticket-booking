package org.steps;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Fulldaynonbillable {

	public static WebDriver driver;

	@Given("the user is logged into the application")
	public void theUserIsLoggedIntoTheApplication() throws InterruptedException, IOException{

		ConfigReader.loadProperties(); 

		WebDriverManager.chromedriver().setup();  
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Live Server 
		driver.get("https://nerve.clsslabs.com/#/ticket");
		
		//Login 
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(ConfigReader.get("username"));
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(ConfigReader.get("password"), Keys.ENTER);

		Thread.sleep(2000);

		WebElement forceLogin = driver.findElement(By.xpath("//span[text()=\" Force Login? \"]"));
		if(forceLogin.isDisplayed()) {
			forceLogin.click();
		}
		else {
			System.out.println("No ForceLogin");
		}

	}

	@Given("the user navigates to Ticket Management")
	public void theUserNavigatesToTicketManagement() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		
		String title = driver.getTitle();
		System.out.println(title);
		
		
		 if(title.equals("CLSS Nerve - Tickets Management System")) {
			 System.out.println("Welcome TO CLSS Nerve - Tickets Management System");
		  
		  }
		 

		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-main-layout/app-header/nav/div/div[2]/ul[1]/li/button/span[1]/i")));
		button.click();
		System.out.println("Side bar opened");

		WebElement transactions = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"Transactions \"]")));
		transactions.click();
		System.out.println("Transaction is clicked");

		WebElement ticketManagement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[text()=' Ticket Management ']")));
		ticketManagement.click();
		System.out.println("Page redirected to Ticket Management");
	}


	@When("the user searches for the ticket and opens it")
	public void theUserSearchesForTheTicketAndOpensIt() throws InterruptedException  {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement searchBox =wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("/html/body/app-root/app-main-layout/app-ticket/section/div/div/div/div/div/div/div[3]/dx-data-grid/div/div[5]/div[1]/table/tbody/tr[2]/td[3]/div/div[2]/div/div/div[1]/input"))); 
		searchBox.sendKeys("CLSNBSDAA10000717");

						/*
						 * WebElement searchBox =
						 * wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath(
						 * "/html/body/app-root/app-main-layout/app-ticket/section/div/div/div/div/div/div/div[3]/dx-data-grid/div/div[5]/div[1]/table/tbody/tr[2]/td[3]/div/div[2]/div/div/div[1]/input"
						 * ))); searchBox.sendKeys("HAPALASAA10000031");
						 */


		Thread.sleep(2000);	
		WebElement ticket1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-main-layout/app-ticket/section/div/div/div/div/div/div/div[3]/dx-data-grid/div/div[6]/div[2]/table/tbody/tr[1]/td[3]")));
				
		Actions actions = new Actions(driver);
		actions.doubleClick(ticket1).perform();
				
		System.out.println("Action performed");
	}

	@When("the user clicks on Time Booking")
	public void theUserClicksOnTimeBooking() throws InterruptedException { 
		Thread.sleep(20000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement timeBooking = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Time Booking']")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", timeBooking);
	}


	@When("the user fills in the details and clicks Add")
	public void theUserFillsInTheDetailsAndClicksAdd()  throws InterruptedException {
		Thread.sleep(5000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement date1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label=\"Open calendar\"]")));
		date1.click();

		WebElement date2 = driver.findElement(By.xpath("//div[text()=' "+ConfigReader.get("date")+" ']"));
		date2.click();

		/*
		 * WebElement hours = driver.findElement(By.id("//mat-select[@id=\"mat-select-12\"]")); 
		 * Select select = new Select(hours); 
		 * select.selectByValue("8");
		 */

		WebElement hours = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-select-value-13")));
		hours.click();

		WebElement hour = driver.findElement(By.xpath("//span[text()="+ConfigReader.get("hours")+"]"));
		hour.click();

		Thread.sleep(5000);

		WebElement remarks = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-21")));
		remarks.sendKeys("Regression testing and automation testing practice");

	}


	@Then("the non-billable time should be added successfully")
	public void theNonBillableTimeShouldBeAddedSuccessfully() throws InterruptedException {
		//do by manual
		
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		
		 * WebElement addButton =
		 * driver.findElement(By.xpath("//span[text()=' Add ']")); addButton.click();
		 */
	}


	@When("the user logs out of the application")
	public void theUserLogsOutOfTheApplication() throws InterruptedException {
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@aria-label=\"Close dialog\"]")));
		closeButton.click();

		WebElement closeButton2 = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//mat-icon[text()=\"close\"]")));
		closeButton2.click();

		WebElement profileClick = driver.findElement(By.xpath("/html/body/app-root/app-main-layout/app-header/nav/div/div[2]/ul[2]/li[8]/a/img"));
		profileClick.click();

		WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=\"Logout \"]")));
		logout.click();

	}


	@Then("the login page should be displayed")
	public void theLoginPageShouldBeDisplayed() {
		driver.quit();
	}







}

