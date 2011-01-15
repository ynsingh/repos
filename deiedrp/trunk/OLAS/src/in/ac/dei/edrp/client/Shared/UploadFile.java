/** $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **********************************************************************************/
package in.ac.dei.edrp.client.Shared;

import in.ac.dei.edrp.client.RPCFiles.GreetingService;
import in.ac.dei.edrp.client.RPCFiles.GreetingServiceAsync;
import in.ac.dei.edrp.client.Shared.DownloadIFrame;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.MessageBox;

/**
 * This class uploads file to database
 * @author On Demand Examination Team
 */
public class UploadFile {
	
	GreetingServiceAsync questionBankObject = (GreetingServiceAsync) GWT.create(GreetingService.class);
    //upload a file:
    FileUpload fileUpload = new FileUpload();
    VerticalPanel vPanel = new VerticalPanel();
    Button uploadButton = new Button("Upload");
    String file;
    Button downloadButton = new Button("Download");
    
    public UploadFile(){
    	
    	fileUpload.ensureDebugId("cwFileUpload");
    	vPanel.add(fileUpload);
    	vPanel.add(uploadButton);
    	vPanel.add(downloadButton);
    	RootPanel.get().add(vPanel);
    	/**
    	 * uploads a file
    	 * @param 
    	 */
    	uploadButton.addClickHandler(new ClickHandler() {
    	      public void onClick(ClickEvent event) {
    	    	file = fileUpload.getFilename();
    	        System.out.println("uploadFile "+file);
    	        if (file.length() == 0) {
    	        		Window.alert("Upload a file");
    	        } else {
    	        	Window.alert(file);
    	        	System.out.println("uploadFile after uploading "+file);
    	        	questionBankObject.uploadFile(file,new AsyncCallback<String>() {
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert("error");
                                }
                                public void onSuccess(String arg0) {
                                    MessageBox.alert("Success");
                                }
                            });
    	        	}
    	      }
    	    });
    	downloadButton.addClickHandler(new ClickHandler() {
  	      public void onClick(ClickEvent event) {
  	    	String copyURL = GWT.getModuleBaseURL() + "UploadQuestionsServlet?param=" + URL.encode("question_Bank.xls"); 
  	    	new DownloadIFrame(copyURL); 
 	        		
  	      }
  	    });
    }
    
    public static final int STATUS_CODE_OK = 200;
    
    public static void doGet(String url) {
      RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

      try {
        @SuppressWarnings("unused")
		Request response = builder.sendRequest(null, new RequestCallback() {
          public void onError(Request request, Throwable exception) {
            // Code omitted for clarity
          }

          public void onResponseReceived(Request request, Response response) {
            // Code omitted for clarity
          }
        });
      } catch (RequestException e) {
        // Code omitted for clarity
      }
    }

    public void onModuleLoad() {
      doGet("/");
    }

}
