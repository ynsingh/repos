package org.smvdu.payroll.api.TreeGen;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import org.smvdu.payroll.api.UserTask.UserTaskBeans;
@ManagedBean
@RequestScoped
public class SimpleTreeBean {
    public SimpleTreeBean()
    {

    }
    private ArrayList<UserTaskBeans> taskBeans;
    private ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();
    private ArrayList<HashMap<Object,TreeNode>> nodeListId = new ArrayList<HashMap<Object, TreeNode>>();

    public ArrayList<HashMap<Object, TreeNode>> getNodeListId() {
        return nodeListId;
    }

    public void setNodeListId(ArrayList<HashMap<Object, TreeNode>> nodeListId) {
        this.nodeListId = nodeListId;
    }
    public ArrayList<TreeNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(ArrayList<TreeNode> nodeList) {
        this.nodeList = nodeList;
    }
    protected org.richfaces.component.UITree sampleTreeBinding;
    public org.richfaces.component.UITree getSampleTreeBinding() {
        return sampleTreeBinding;
    }

    private boolean check;
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    public void setSampleTreeBinding(
            org.richfaces.component.UITree sampleTreeBinding) {
        this.sampleTreeBinding = sampleTreeBinding;
    }
    private String data;
    private TreeNode rootNode = null;
    private List<String> selectedNodeChildren = new ArrayList<String>();
    private HashMap<Object,TreeNode> currentNodes = new HashMap<Object, TreeNode>();
    List<TreeBeanImpl> nodeImpl1 = null;
    private String nodeTitle;
    private void addNodes(String path,TreeNode node,Properties properties) {
        boolean end = false;
        int counter = 1;
        //TreeBeanImpl node = null;
        while (!end)
        {
            String key = path != null ? path + "." + counter : String.valueOf(counter);
            String value = properties.getProperty(key);
            if (value != null)
            {
                //node = new TreeBeanImpl(value);
                TreeNodeImpl nodeImpl = new TreeNodeImpl();
                nodeImpl.setData(value);
                node.addChild(key,nodeImpl);
                //nodeImpl1.add(node);
    //            System.out.println("Node Value : "+nodeImpl.getData());
                currentNodes.put(key, node);
                nodeListId.add(currentNodes);
                //nodeList.add();
                addNodes(key,nodeImpl,properties);
                counter++;
            }
            else
            {
                end = true;
            }
        }
        //nodeImpl1.add(node);
    }

    private void loadTree() {
        try
        {
                FacesContext cont = FacesContext.getCurrentInstance();
                ExternalContext econtext = cont.getExternalContext();
                String path = econtext.getRealPath("/");
                String filePath = path + File.separator + "Propertie/Tree.properties";
                FileInputStream f = new FileInputStream(new File(filePath));
                
                
                //InputStream i=econtext.getResourceAsStream(filePath);
                Properties p = new Properties();
                p.load(f);
                //System.out.println("Key Set : "+p.keySet());
                rootNode = new TreeNodeImpl();

                addNodes(null,rootNode,p);
                
        }
        catch (IOException e)
        {
              e.printStackTrace();
        }
    }

    public void processSelection(NodeSelectedEvent event)
    {
        HtmlTree tree = (HtmlTree) event.getComponent();
        System.out.println("Tree Component : "+tree);
        nodeTitle = (String) tree.getRowData();
    //    selectedNodeChildren.clear();
        int i=0;
        //Object rowKey = tree.getRowKey();
        TreeNode currentNode = tree.getModelTreeNode(tree.getRowKey());
        if (currentNode.isLeaf())
        {
            selectedNodeChildren.add((String)currentNode.getData());
            System.out.println("DAta Should Be Write Here if : "+(String)currentNode.getData()+" Key : ");
            this.setCheck(true);
        }
        else
        {
            Iterator<Map.Entry<Object, TreeNode>> it = currentNode.getChildren();
            while (it!=null &&it.hasNext())
            {
                Map.Entry<Object, TreeNode> entry = it.next();
                selectedNodeChildren.add(entry.getValue().getData().toString());
                this.setCheck(true);
                System.out.println("DAta Should Be Write Here else : "+entry.getValue().getData().toString()+entry.getKey());
                i++;
            }
            System.out.println("Total Child : "+i);
        }
    }




    public void saveUserTask()
    {
        try
        {
            ArrayList<TreeNode> t1 = (ArrayList<TreeNode>) sampleTreeBinding.getData();
            /*for(TreeNode t : t1)
            {
                System.out.println("Updating..." + t.getData());
            }*/
            System.out.println("Key Set : "+currentNodes.keySet());
            Set<Object> s = currentNodes.keySet();
            int j = 0;
            Iterator i = s.iterator();
            while(i.hasNext() && j<nodeListId.size())
            {
                System.out.println(" : "+nodeListId.get(j).get(i.next()).getData());
                j++;
            }
           /*for(int i = 0;i<nodeListId.size() && i<s.size();i++)
           {
               System.out.println("Data : "+nodeListId.get(i));
           }*/
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
    

    public TreeNode getTreeNode()
    {
        if (rootNode == null)
        {
            loadTree();
            rootNode.setData(nodeListId);
        }
        return rootNode;
    }
    public String getNodeTitle() {
        return nodeTitle;
    }
    public void setNodeTitle(String nodeTitle) {
        this.nodeTitle = nodeTitle;
    }

    public ArrayList<UserTaskBeans> getTaskBeans() {
        return taskBeans;
    }

    public void setTaskBeans(ArrayList<UserTaskBeans> taskBeans) {
        this.taskBeans = taskBeans;
    }
    /*public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    @Override
    public String toString()
    {
        return data;
    }*/

}
