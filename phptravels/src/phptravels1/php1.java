package phptravels1;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class php1 {
	public static WebDriver driver;
    public static String workingDir;
    
    static int targetDay = 0, targetMonth = 0, targetYear = 0;
    static int currenttDate = 0, currenttMonth = 0, currenttYear = 0;
    static int jumMonthBy = 0;
    static boolean increment = true;
	 
	public static void main(String[] args) throws InterruptedException {
	
		String dateToSet = "18/10/2017";

	    getCurrentDayMonth();
	    System.out.println(currenttDate);
	    System.out.println(currenttMonth);
	    System.out.println(currenttYear);

	    getTargetDayMonthYear(dateToSet);
	    System.out.println(targetDay);
	    System.out.println(targetMonth);
	    System.out.println(targetYear);

	    calculateToHowManyMonthToJump();
	    System.out.println(jumMonthBy);
	    System.out.println(increment);
		    
		    workingDir = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver",workingDir+ "\\source\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get("http://www.phptravels.net/");
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.xpath(".//*[@id='s2id_autogen3']/a/span[2]")).click();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("mal");
	        Thread.sleep(1000);
	        
	        Actions action = new Actions(driver);
	        WebElement menu=driver.findElement(By.xpath(".//*[@id='select2-drop']/ul"));
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        action.moveToElement(menu);
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        action.click().build().perform();
	         
	       /* driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.xpath(".//*[@id='dpd1']/div/input")).clear();
	        WebElement checkin = driver.findElement(By.xpath(".//*[@id='dpd1']/div/input"));
	        checkin.sendKeys("17/10/2017");
	        checkin.click();
	       
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.xpath(".//*[@id='dpd2']/div/input")).clear();
	        WebElement checkout = driver.findElement(By.xpath(".//*[@id='dpd2']/div/input"));
	        checkout .sendKeys("18/10/2017");
	        checkout .click();*/
	        
	        driver.findElement(By.xpath(".//*[@id='dpd1']/div/input")).click();

	        for (int i = 0; i < jumMonthBy; i++) {
	            if (increment) {
	                driver.findElement(By.cssSelector(".next")).click();
	            } else {
	                driver.findElement(By.cssSelector(".prev")).click();
	                       
	            }
	            Thread.sleep(1000);

	        }

	        driver.findElement(By.linkText(Integer.toString(targetDay))).click();

	    }

	    public static void getCurrentDayMonth() {

	        Calendar cal = Calendar.getInstance();
	        currenttDate = cal.get(Calendar.DAY_OF_MONTH);
	        currenttMonth = cal.get(Calendar.MONTH) + 1;
	        currenttYear = cal.get(Calendar.YEAR);
	    }

	    public static void getTargetDayMonthYear(String dateString) {
	        int firstIndex = dateString.indexOf("/");
	        int lastIndex = dateString.lastIndexOf("/");

	        String day = dateString.substring(0, firstIndex);
	        targetDay = Integer.parseInt(day);

	        String month = dateString.substring(firstIndex + 1, lastIndex);
	        targetMonth = Integer.parseInt(month);

	        String year = dateString.substring(lastIndex + 1, dateString.length());
	        targetYear = Integer.parseInt(year);

	    }

	    public static void calculateToHowManyMonthToJump() {

	        if ((targetMonth - currenttMonth) > 0) {
	            jumMonthBy = targetMonth - currenttMonth;

	        } else {
	            jumMonthBy = currenttMonth - targetMonth;
	            increment = false;
	        }
			
	       
	    /*  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	       Select Select1 = new Select(driver.findElement(By.xpath(".//*[@id='adults']")));
		   Select1.selectByIndex(2);
		   
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	       Select Select2 = new Select(driver.findElement(By.xpath(".//*[@id='child']")));
		   Select2.selectByIndex(2);
		   
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   driver.findElement(By.xpath(".//*[@id='HOTELS']/div/form/div[6]/div/button")).click();   */
	       
	}
}
