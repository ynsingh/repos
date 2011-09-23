package com.myapp.struts.utility;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
 import com.myapp.struts.LoginAction;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import org.w3c.dom.*;

public class writeLog  {


 
static Document doc;
public static void writelog(String path,String dateTime,String ipaddress,String url,String username,String library_id,String sublibrary_id,String userid,String role)
{
System.out.println(path);

path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
System.out.println(path);
File docFile = new File(path+"/logs/list.xml");

Document doc = null;
try
{
DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
DocumentBuilder db = dbf.newDocumentBuilder();
doc = db.parse(docFile);
}
catch (java.io.IOException e)
{
//System.out.println("Can't find the file");
}
catch (Exception e)
{
//System.out.print("Problem parsing the file.");
}

Element root = doc.getDocumentElement();

//System.out.println("The root element is " + root.getNodeName() + ".\n");

NodeList children = root.getChildNodes();
//System.out.print("There are "+children.getLength()+" child elements.\n");
//System.out.print("They are: \n");

//Print the file
for (Node child = root.getFirstChild();child != null;child = child.getNextSibling())
{
if (child.getNodeType() == child.TEXT_NODE)
{
//System.out.println("Text: "+child.getNodeValue());
}
else if (child.getNodeType() == child.ELEMENT_NODE)
{
//System.out.println(child.getNodeName()+" = "+child.getFirstChild().getNodeValue());
}
}


//NodeList deleteElement = root.getElementsByTagName("userinfo");

//Node deleteNode= deleteElement.item(0);

//root.removeChild(deleteNode);

Node newCoffeeNode=doc.createElement("userinfo");
Node newNameNode=doc.createElement("dateTime");
Text txtnNode=doc.createTextNode(dateTime);
newNameNode.appendChild(txtnNode);

Node newpriceNode=doc.createElement("username");
Text txtpNode=doc.createTextNode(username);
newpriceNode.appendChild(txtpNode);

Node newpriceNode1=doc.createElement("library_id");
Text txtpNode1=doc.createTextNode(library_id);
newpriceNode1.appendChild(txtpNode1);

Node newpriceNode2=doc.createElement("sublibrary_id");
Text txtpNode2=doc.createTextNode(sublibrary_id);
newpriceNode2.appendChild(txtpNode2);

Node newpriceNode3=doc.createElement("userid");
Text txtpNode3=doc.createTextNode(userid);
newpriceNode3.appendChild(txtpNode3);

Node newpriceNode4=doc.createElement("url");
Text txtpNode4=doc.createTextNode(url);
newpriceNode4.appendChild(txtpNode4);

Node newpriceNode5=doc.createElement("role");
Text txtpNode5=doc.createTextNode(role);
newpriceNode5.appendChild(txtpNode5);


newCoffeeNode.appendChild(newNameNode);
newCoffeeNode.appendChild(newpriceNode);
newCoffeeNode.appendChild(newpriceNode1);
newCoffeeNode.appendChild(newpriceNode2);
newCoffeeNode.appendChild(newpriceNode3);
newCoffeeNode.appendChild(newpriceNode4);
newCoffeeNode.appendChild(newpriceNode5);

root.appendChild(newCoffeeNode);



try{
String outputURL = path+"/logs/list.xml";

DOMSource source = new DOMSource(doc);
StreamResult result = new StreamResult(new FileOutputStream(outputURL));

TransformerFactory transFactory = TransformerFactory.newInstance();
Transformer transformer = transFactory.newTransformer();

transformer.transform(source, result);

} catch (Exception e) {
e.printStackTrace();
}


//DocumentBuilderFactory dbfactory=DocumentBuilderFactory.newInstance();
//try
//{
//DocumentBuilder builder=dbfactory.newDocumentBuilder();
//File f=new File(path+"/web/logs/list.xml");
//System.out.println(path+"   "+doc+f);
//
//doc=builder.parse(f);
//Node rootNode=doc.getDocumentElement();
//NodeList list=doc.getElementsByTagName("userinfo");
//for(int i=0;i<list.getLength();i++)
//{
//Node thisCoffeeNode=list.item(i);
//Node thisNameNode=thisCoffeeNode.getFirstChild();
//if(thisNameNode==null)
//continue;
//
//if(!(thisNameNode instanceof org.w3c.dom.Text))
//continue;
//
//String data=thisNameNode.getNodeValue();
//Node newpriceNode=doc.createElement("username");
//Text txtpNode=doc.createTextNode(username);
//newpriceNode.appendChild(txtpNode);
//
////Node newpriceNode1=doc.createElement("library_id");
////Text txtpNode1=doc.createTextNode(library_id);
////newpriceNode1.appendChild(txtpNode1);
////
////Node newpriceNode2=doc.createElement("sublibrary_id");
////Text txtpNode2=doc.createTextNode(sublibrary_id);
////newpriceNode2.appendChild(txtpNode2);
////
////Node newpriceNode3=doc.createElement("userid");
////Text txtpNode3=doc.createTextNode(userid);
////newpriceNode3.appendChild(txtpNode3);
////
////Node newpriceNode4=doc.createElement("url");
////Text txtpNode4=doc.createTextNode(url);
////newpriceNode4.appendChild(txtpNode4);
////
////Node newpriceNode5=doc.createElement("role");
////Text txtpNode5=doc.createTextNode(role);
////newpriceNode5.appendChild(txtpNode5);
//
//newCoffeeNode.appendChild(newNameNode);
//newCoffeeNode.appendChild(newpriceNode);
////newCoffeeNode.appendChild(newpriceNode1);
////newCoffeeNode.appendChild(newpriceNode2);
////newCoffeeNode.appendChild(newpriceNode3);
////newCoffeeNode.appendChild(newpriceNode4);
////newCoffeeNode.appendChild(newpriceNode5);
//
//rootNode.insertBefore(newCoffeeNode,thisCoffeeNode);
//break;
//}
//TransformerFactory transFact=TransformerFactory.newInstance();
//Transformer trans=transFact.newTransformer();
//DOMSource source=new DOMSource(doc);
//File newXML=new File(path+"/web/logs/list.xml");
//FileOutputStream os=new FileOutputStream(newXML);
//StreamResult result=new StreamResult(os);
//trans.transform(source,result);
//}
//catch(Exception e)
//{
//System.out.println(e);
//}
//
//////DocumentBuilderFactory dbfactory=DocumentBuilderFactory.newInstance();
//////try
//////{
//////DocumentBuilder builder=dbfactory.newDocumentBuilder();
//////File f=new File(path+"/web/logs/list.xml");
//////doc=builder.parse(f);
//////System.out.println(path+"   "+doc+f);
//////Node rootNode=doc.getDocumentElement();
//////NodeList list=doc.getElementsByTagName("userinfo");
//////System.out.println(list.getLength());
//////for(int i=0;i<list.getLength();i++)
//////{
//////Node thisUserInfoNode=list.item(i);
//////Node thisNameNode=thisUserInfoNode.getFirstChild();
//////if(thisNameNode==null)
//////continue;
//////
//////if(!(thisNameNode instanceof org.w3c.dom.Text))
//////continue;
//////
//////String data=thisNameNode.getNodeValue();
//////Node newUserInfoNode=doc.createElement("userinfo");
//////
//////
//////Node newDateNode=doc.createElement("Date");
//////Text txtDateNode=doc.createTextNode(dateTime);
//////
//////newDateNode.appendChild(txtDateNode);
//////
//////Node newIPaddressNode=doc.createElement("ipaddress");
//////Text txtipNode=doc.createTextNode(ipaddress);
//////
//////newIPaddressNode.appendChild(txtipNode);
//////
//////Node newURIaddressNode=doc.createElement("Request_URL");
//////Text txturiNode=doc.createTextNode(url);
//////
//////newURIaddressNode.appendChild(txturiNode);
//////
//////Node newUserNameNode=doc.createElement("Login_UserName");
//////Text txtuserNode=doc.createTextNode(username);
//////
//////newUserNameNode.appendChild(txtuserNode);
//////
//////Node newLibraryNode=doc.createElement("Library");
//////Text txtLibNode=doc.createTextNode(library_id);
//////
//////newLibraryNode.appendChild(txtLibNode);
//////
//////Node newSubLibraryNode=doc.createElement("SubLibrary");
//////Text txtSublibNode=doc.createTextNode(sublibrary_id);
//////
//////newSubLibraryNode.appendChild(txtSublibNode);
//////
//////
//////Node newUserRoleNode=doc.createElement("Login_Role");
//////Text txtuseRolerNode=doc.createTextNode(username);
//////
//////newUserRoleNode.appendChild(txtuseRolerNode);
//////
//////Node newUserIdNode=doc.createElement("UserID");
//////Text txtUserIdNode=doc.createTextNode(userid);
//////
//////newUserIdNode.appendChild(txtUserIdNode);
//////
//////
//////
//////
//////
//////
//////newUserInfoNode.appendChild(newDateNode);
//////newUserInfoNode.appendChild(newURIaddressNode);
//////newUserInfoNode.appendChild(newIPaddressNode);
//////newUserInfoNode.appendChild(newLibraryNode);
//////newUserInfoNode.appendChild(newSubLibraryNode);
//////newUserInfoNode.appendChild(newUserNameNode);
//////newUserInfoNode.appendChild(newUserRoleNode);
//////newUserInfoNode.appendChild(newUserIdNode);
//////
//////rootNode.insertBefore(newUserInfoNode,thisUserInfoNode);
//////break;
//////}
//////TransformerFactory transFact=TransformerFactory.newInstance();
//////Transformer trans=transFact.newTransformer();
//////DOMSource source=new DOMSource(doc);
//////File newXML=new File(path+"/web/logs/list.xml");
//////FileOutputStream os=new FileOutputStream(newXML);
//////StreamResult result=new StreamResult(os);
//////trans.transform(source,result);
//////}
//////catch(Exception e)
//////{
//////System.out.println(e);
//////}
////
//////System.out.println("Path"+path);
////////
//////
//////
//////
////File docFile = new File(path+"/web/logs/list.xml");
////
////Document doc = null;
////try
////{
////DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
////DocumentBuilder db = dbf.newDocumentBuilder();
////doc = db.parse(docFile);
////}
////catch (java.io.IOException e)
////{
////System.out.println("Can't find the file");
////}
////catch (Exception e)
////{
////System.out.print("Problem parsing the file.");
////}
////
////Element root = doc.getDocumentElement();
////
////System.out.println("The root element is " + root.getNodeName() + ".\n");
////
////NodeList children = root.getChildNodes();
////System.out.print("There are "+children.getLength()+" child elements.\n");
////System.out.print("They are: \n");
////
//////Print the file
////for (Node child = root.getFirstChild();child != null;child = child.getNextSibling())
////{
////if (child.getNodeType() == child.TEXT_NODE)
////{
////System.out.println("Text: "+child.getNodeValue());
////}
////else if (child.getNodeType() == child.ELEMENT_NODE)
////{
////System.out.println(child.getNodeName()+" = "+child.getFirstChild().getNodeValue());
////}
////}
////
////
//////NodeList deleteElement = root.getElementsByTagName("staff");
////
//////Node deleteNode= deleteElement.item(0);
////
//////root.removeChild(deleteNode);
////Element staffElement = doc.createElement("userinfo");
////
////Node updateText = doc.createTextNode("");
////staffElement.appendChild(updateText);
//////
////Element firstName = doc.createElement("username");
////Node firstNameNode = doc.createTextNode(username);
////firstName.appendChild(firstNameNode);
////
////staffElement.appendChild(firstName);
////
////////
//////
//////Element lastName = doc.createElement("ipaddress");
//////
//////Node lastNameNode = doc.createTextNode(ipaddress);
//////lastName.appendChild(lastNameNode);
//////
//////staffElement.appendChild(lastName);
//////
//////
////////
//////Element nickName = doc.createElement("url");
//////Node nickNameNode = doc.createTextNode(url);
//////nickName.appendChild(nickNameNode);
//////
//////staffElement.appendChild(nickName);
//////
//////
////////
//////Element salary = doc.createElement("userid");
//////Node salaryNode = doc.createTextNode(userid);
//////salary.appendChild(salaryNode);
//////
//////staffElement.appendChild(salary);
//////
//////
////////
////root.appendChild(staffElement);
////
//////Node StaffNode=(Node)updateElement;
////
////
////
////
////
////try{
////String outputURL = path+"/web/logs/list.xml";
////
////DOMSource source = new DOMSource(doc);
////StreamResult result = new StreamResult(new FileOutputStream(outputURL));
////
////TransformerFactory transFactory = TransformerFactory.newInstance();
////Transformer transformer = transFactory.newTransformer();
////
////transformer.transform(source, result);
////
////} catch (Exception e) {
////e.printStackTrace();
////}

}
}



