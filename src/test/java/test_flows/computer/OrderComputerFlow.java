package test_flows.computer;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
import org.openqa.selenium.WebDriver;
import test_data.computer.ComputerData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow <T extends ComputerEssentialComponent>{

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private  ComputerData computerData;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData =computerData;
    }

    public void buildCompSpecAndAddToCart(){
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        T computerEssentialComp = computerItemDetailPage.computerComponent(computerEssentialComponent);

       String processorFullStr=  computerEssentialComp.selectProcessorType(computerData.getProcessorType());
       double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);

        String ramFullStr= computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAdditionalPrices = extractAdditionalPrice(ramFullStr);

        String hddFullStr =computerEssentialComp.selectHDD(computerData.getHdd());
        double hddAdditionalPrices = extractAdditionalPrice(hddFullStr);

        double additionalOsPrice =0;
        if (computerData.getOs() != null){
           String fullOsStr= computerEssentialComp.selectOS(computerData.getOs());
           additionalOsPrice = extractAdditionalPrice(fullOsStr);
        }
        System.out.println(("additionalOsPrice: " + additionalOsPrice));
  }
  private  double extractAdditionalPrice(String itemStr){
        double price = 0;
      Pattern pattern = Pattern.compile("\\[(.*?)\\]");
      Matcher matcher = pattern.matcher(itemStr);
      if (matcher.find()){
          price = Double.parseDouble(matcher.group(1).replaceAll("[-+]",""));
      }

        return  price;
  }
}
