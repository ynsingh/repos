
package uk.ac.reload.testsupport;

import org.jdom.Namespace;


/**
 * Globals for tests
 * 
 * @author Phillip Beauvoir
 * @version $Id: ITestsGlobals.java,v 1.1 1998/03/26 16:03:03 ynsingh Exp $
 */
public interface ITestsGlobals {
    
    // MD Namespace Version 1.2.1
    Namespace IMSMD_NAMESPACE_121 = Namespace.getNamespace("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");    

    // MD Namespace Version 1.2.2
    Namespace IMSMD_NAMESPACE_122 = Namespace.getNamespace("http://www.imsglobal.org/xsd/imsmd_v1p2");        
    
    // CP Version 1.1.3
    Namespace IMSCP_NAMESPACE_113 = Namespace.getNamespace("http://www.imsglobal.org/xsd/imscp_v1p1");
    
    // LD Version 1.0
    Namespace IMSLD_NAMESPACE_100 = Namespace.getNamespace("http://www.imsglobal.org/xsd/imsld_v1p0");

    // ADL SCORM Version 1.2 
    Namespace ADLCP_NAMESPACE_12 = Namespace.getNamespace("adlcp", "http://www.adlnet.org/xsd/adlcp_rootv1p2");
}
