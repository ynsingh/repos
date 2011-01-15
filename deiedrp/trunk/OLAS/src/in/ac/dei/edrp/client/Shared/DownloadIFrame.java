/** $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *********************************************************************************/
package in.ac.dei.edrp.client.Shared;

import com.google.gwt.event.dom.client.HasLoadHandlers; 
import com.google.gwt.event.dom.client.LoadEvent; 
import com.google.gwt.event.dom.client.LoadHandler; 
import com.google.gwt.event.shared.HandlerRegistration; 
import com.google.gwt.user.client.ui.Frame; 
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The <code>DownloadIFrame</code> class download excel sheet for question bank
 * input: download selection in question bank
 * output: excel sheet
 * @author On Demand Examination Team
*/

public class DownloadIFrame extends Frame implements LoadHandler, HasLoadHandlers {

	public static final String DOWNLOAD_FRAME = "__gwt_downloadFrame";

	public DownloadIFrame(String url){ 
		super(); 
		setSize("0px", "0px"); 
		setVisible(false); 
		RootPanel rp = RootPanel.get(DOWNLOAD_FRAME); 
		if (rp != null) {
			addLoadHandler(this); 
			rp.add(this); 
			setUrl(url); 
		} else openURLInNewWindow(url); 
	}

	native void openURLInNewWindow(String url) /*-{ $wnd.open(url); }-*/;

	public HandlerRegistration addLoadHandler(LoadHandler handler) {
		return addHandler(handler, LoadEvent.getType()); 
	}

	public void onLoad(LoadEvent event) { } 
} 
