import java.applet.Applet;
import java.net.*;
import java.awt.*;

public class Search extends Applet {
 TextField searchParameter;
 Choice    searchEngine;
 Button    searchButton;
 Label testLabel;
 // initialize the display
 public void init() {
   setBackground(Color.white);
   testLabel=new Label("Search The Web");
   testLabel.setFont(new Font("Sarif", Font.BOLD,10));
   searchParameter = new TextField(10);
   add(testLabel);
   add(searchParameter);
   searchEngine = new Choice(); 
   searchEngine.addItem("WebCrawler");
   searchEngine.addItem("Yahoo");
   searchEngine.addItem("Google");
   searchEngine.select(0);
   add(searchEngine);
   searchButton = new Button("Search");
   add(searchButton);
   }

 public boolean action(Event e, Object o) {
   if (e.target.equals(searchButton)) {
     try {
       sendSearch();
       }
     catch (Exception e1) {
       showStatus("Exception caught:" + e1.toString());
       }
     }
   return true;
   }

public void sendSearch() throws Exception {
  String searchString = searchParameter.getText();
  if (searchString.equals("")) {
    showStatus("Must enter a search string");
    return;
    }
  String url;
  switch (searchEngine.getSelectedIndex()) {
    // case 0: url = "http://webcrawler.com/cgi-bin/WebQuery?text=";
	case 0: url = "http://msxml.webcrawler.com/info.wbcrwl/search/web/";
             break;
     case 1: url = "http://search.yahoo.com/bin/search?p=";
             break;
     case 2: url="http://www.google.co.in/search?hl=en&q=";
	     break;
    default: showStatus("Invalid search engine selected.");
             return;
    }

  // encode the search data
  url += URLEncoder.encode(searchString);

  // launch the search engine
  showStatus("Connecting to search location " + url);
  getAppletContext().showDocument(new URL(url), "_top");
  }
}
