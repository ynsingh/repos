<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<html>
    <head>
        <title>tree</title>
    </head>
    <script type="text/javaScript">
    
    </script>
    <body>
        <f:view>
            <rich:panel header="User Task">
                <h:form>
                <h:panelGrid columns="1">
                    <rich:panel>
                
                    <h:panelGrid width="100%">
                        <rich:tree switchType="ajax" id="tr"  iconExpanded = "/images/item.png"
                                   iconCollapsed="/images/header.png" style="width:300px"
                                   binding="#{simpleTreeBean.sampleTreeBinding}"
                                   value="#{simpleTreeBean.treeNode}" var="item" ajaxSubmitSelection="true"
                                   nodeSelectListener="#{simpleTreeBean.processSelection}">
                            <rich:treeNode ajaxSingle="true">
                                <h:selectBooleanCheckbox value="#{simpleTreeBean.check}">
                                    <a4j:support event="onclick"/>
                                </h:selectBooleanCheckbox>
                                <h:outputText value="#{item}"/>
                            </rich:treeNode>
                        </rich:tree>
                    </h:panelGrid>
          
                </rich:panel>
                    <rich:separator/>
                    <rich:panel>
                        <a4j:commandButton value="Update User Task" action="#{simpleTreeBean.saveUserTask}"/>
                    </rich:panel>
                </h:panelGrid>
                      </h:form>
            </rich:panel>
        </f:view>
    </body>
</html>
