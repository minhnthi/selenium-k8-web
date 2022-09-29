package url;

public interface Urls {

    String baseUrl ="https://the-internet.herokuapp.com";

   // String demoBaseUrl ="http://demowebshop.tricentis.com";
    String demoBaseUrl = System.getProperty("baseUrl");
    String dropDownSlug ="/dropdown";
    String iframeSlug ="/iframe";
    String hoverSlug ="/hovers";
    String alertsSlug ="/javascript_alerts";
    String loginSlug ="/login";
    String dynamicControlSlug ="/dynamic_controls";
    String jsExecutorSlug ="/floating_menu";
}
