package in.ac.dei.edrp.client.Shared;

import in.ac.dei.edrp.client.RPCFiles.GreetingService;
import in.ac.dei.edrp.client.RPCFiles.GreetingServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.core.EventObject;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class XlSheet implements EntryPoint {
  
  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
	
	VerticalPanel vpanel=new VerticalPanel();
	
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  Button upload=new Button("upload");
	  Hyperlink link=new Hyperlink();
	  final FileUpload fileUpload = new FileUpload(); 
	
	  fileUpload.ensureDebugId("cwFileUpload");
	  vpanel.add(fileUpload);
	  
	upload.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				String filename = fileUpload.getFilename();
				MessageBox.alert(fileUpload.getFilename());
				if (filename.length() == 0) {
					MessageBox.alert("Browse the file");
				} else {
					greetingService.uploadFile(filename,new AsyncCallback<String>() {
						public void onFailure(Throwable arg0) {
							MessageBox.alert("error");
						}
						public void onSuccess(String arg0) {
							vpanel.clear();
							Label label1 = new Label("File has been submitted successfully");
							label1.setStyleName("promptfillLabel");
							vpanel.add(label1);
						}
					});			    	        		
				}
			}
	  });
	vpanel.add(link);
	  vpanel.add(upload);
	  RootPanel.get().add(vpanel);
	 
	  link.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String copyURL = GWT.getModuleBaseURL() + "UploadQuestionsServlet?param=" + URL.encode("student.xls"); 
				new DownloadIFrame(copyURL); 
			}
		});
  }
}