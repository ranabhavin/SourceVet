import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LinksCheck {
	
	
	public static List findAllLinks(WebDriver driver)
	{
		
		
		List<WebElement> elementList = new ArrayList();
		
		elementList = driver.findElements(By.tagName("a"));
		
		elementList.addAll(driver.findElements(By.tagName("img")));
		
		List finalList = new ArrayList();
		
		for(WebElement element : elementList)
		{
			
			if(element.getAttribute("href") != null)
			{
				
				finalList.add(element);
			}
		}
		
		return finalList;
	}
	
	
	
	public static String isLinkBroken(URL url) throws IOException
	{
		String response = "";
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.connect();
		
		response = connection.getResponseMessage();
		connection.disconnect();
		
		return response;		
		
	}

	
	public static void main(String args[]) 
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\bhavinrana\\Downloads\\geckodriver\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();   // Change Browser Accroding to your need
		
		driver.get("http://stagingsrc.sourceprosonboard.webteamcorp.com/");   // Change URl According to your need
		List<WebElement> allImages = findAllLinks(driver);
		
		System.out.println("Total Number of elements found "+allImages.size());
		
		for(WebElement element : allImages)
		{
			
			try {
				System.out.println("URL : " +element.getAttribute("href") + " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
			} 
			catch (Exception e) {
				System.out.println("At "+element.getAttribute("innerHTML") + "Exception Occured " +e.getMessage());
			}
			
		}
		
		
	}

}
