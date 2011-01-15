package in.ac.dei.edrp.client.Shared;

//import java.io.StringWriter;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.*;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//
//import org.w3c.dom.Attr;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

@SuppressWarnings("deprecation")
public class CM_tryUpload {
	

	final FormPanel form = new FormPanel();
	HorizontalPanel hpanel = new HorizontalPanel();
	HorizontalPanel buttonpanel = new HorizontalPanel();
	Button cancel = new Button("Cancel");
	Button uploadbutton = new Button("Upload");
	// added for xml
	String xml;
	String filePath;

	public void tryupload() {
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		form.setWidth("275px");

		final VerticalPanel holder = new VerticalPanel();
		holder.add(new HTML("<hr />"));

		

		final FileUpload upload = new FileUpload();
		upload.setName("upload");

		holder.add(upload);

		buttonpanel.setSpacing(5);

		holder.add(new HTML("<hr />"));

		holder.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);

		buttonpanel.add(uploadbutton);
		buttonpanel.add(cancel);

		holder.add(buttonpanel);

		form.add(holder);

		// form.setAction("url");
		cancel.addListener(new ButtonListenerAdapter() {
			public void onClick(Button ProgrammeEditButton, EventObject e) {
				holder.clear();
				buttonpanel.clear();
				holder.removeFromParent();
			}
		});

		uploadbutton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button ProgrammeEditButton, EventObject e) {
				try {
					
					changexml();
				} catch (Exception e1) {
					System.out.println("XML error");
				}

				// if(upload.equals(""))
				// {
				// MessageBox.alert("empty path");
				// }else
				// {
				//		
				// }
			}
		});

		form.addFormHandler(new FormHandler() {
			public void onSubmit(FormSubmitEvent arg0) {
				// TODO Auto-generated method stub
			}
			public void onSubmitComplete(FormSubmitCompleteEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		hpanel.add(form);
	}

	public void changexml() throws Exception {
		//
		// DocumentBuilderFactory docFactory =
		// DocumentBuilderFactory.newInstance();
		// DocumentBuilder docBuilder = null;
		// Document doc = null;
		// try {
		// docBuilder = docFactory.newDocumentBuilder();
		// } catch (ParserConfigurationException e2) {
		//		
		// e2.printStackTrace();
		// }
		// try {
		// doc = docBuilder.parse("loginImage.xml");
		// } catch (Exception e1) {
		//
		// e1.printStackTrace();
		// }
		//
		// Node image = doc.getFirstChild();
		// NamedNodeMap imageAttributes = image.getAttributes();
		// Attr image1 = doc.createAttribute("image1");
		// image1.setValue("new Image");
		// imageAttributes.setNamedItem(image1);

		// DocumentBuilderFactory documentBuilderFactory =
		// DocumentBuilderFactory.newInstance();
		// DocumentBuilder documentBuilder =
		// documentBuilderFactory.newDocumentBuilder();
		// Document document = documentBuilder.newDocument();
		//
		// Element rootElement = document.createElement("root");
		// Element em = document.createElement("image");
		// em.appendChild(document.createTextNode("value"));
		// rootElement.appendChild(em);
		// TransformerFactory transformerFactory =
		// TransformerFactory.newInstance();
		// Transformer transformer = transformerFactory.newTransformer();
		// DOMSource source = new DOMSource(document);
		// StringWriter sw=new StringWriter();
		// StreamResult result = new StreamResult(sw);
		// transformer.transform(source, result);
		// xml=sw.toString();
		// System.out.println(xml);
	}
}
