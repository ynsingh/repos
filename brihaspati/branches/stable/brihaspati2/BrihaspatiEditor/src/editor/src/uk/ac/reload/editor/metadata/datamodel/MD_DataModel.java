/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2004 Oleg Liber, Bill Olivier, Phillip Beauvoir
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *  Project Management Contact:
 *
 *  Oleg Liber
 *  Bolton Institute of Higher Education
 *  Deane Road
 *  Bolton BL3 5AB
 *  UK
 *
 *  e-mail:   o.liber@bolton.ac.uk
 *
 *
 *  Technical Contact:
 *
 *  Phillip Beauvoir
 *  e-mail:   p.beauvoir@bolton.ac.uk
 *
 *  Web:      http://www.reload.ac.uk
 *
 */

package uk.ac.reload.editor.metadata.datamodel;

import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.metadata.xml.Metadata;



/**
 * 2nd level DataModel
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_DataModel.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class MD_DataModel
extends DataModel
{
    
    /**
     * The General group
     */
    private MD_General _mdGeneral;
    
    /**
     * The LifeCycle group
     */
    private MD_LifeCycle _mdLifeCycle;

    /**
     * The MetaMetaData group
     */
    private MD_MetaMetaData _mdMetaMetaData;

    /**
     * The Technical group
     */
    private MD_Technical _mdTechnical;

    /**
     * The Educational group
     */
    private MD_Educational _mdEducational;

    /**
     * The Rights group
     */
    private MD_Rights _mdRights;

    /**
     * The Relation group
     */
    private MD_Relation _mdRelation;

    /**
     * The Annotation group
     */
    private MD_Annotation _mdAnnotation;

    /**
     * The Classification group
     */
    private MD_Classification _mdClassification;

    
    /**
     * Constructor
     * @param md The Metadata XML Document
     */
    public MD_DataModel(Metadata md) {
        super(md);
    }
    
    /**
     * @return The Metadata XML Document
     */
    public Metadata getMetadata() {
        return (Metadata)getSchemaDocument();
    }
    
    /**
     * @return The MD_General group
     */
    public MD_General getMD_General() {
        if(_mdGeneral == null) {
            _mdGeneral = new MD_General(this);
        }
        return _mdGeneral;
    }
    
    /**
     * @return The MD_Technical group
     */
    public MD_Technical getMD_Technical() {
        if(_mdTechnical == null) {
            _mdTechnical = new MD_Technical(this);
        }
        return _mdTechnical;
    }

    /**
     * @return The MD_MetaMetaData group
     */
    public MD_MetaMetaData getMD_MetaMetaData() {
        if(_mdMetaMetaData == null) {
            _mdMetaMetaData = new MD_MetaMetaData(this);
        }
        return _mdMetaMetaData;
    }

    /**
     * @return The MD_LifeCycle group
     */
    public MD_LifeCycle getMD_LifeCycle() {
        if(_mdLifeCycle == null) {
            _mdLifeCycle = new MD_LifeCycle(this);
        }
        return _mdLifeCycle;
    }

    /**
     * @return The MD_Educational group
     */
    public MD_Educational getMD_Educational() {
        if(_mdEducational == null) {
            _mdEducational = new MD_Educational(this);
        }
        return _mdEducational;
    }

    /**
     * @return The MD_Rights group
     */
    public MD_Rights getMD_Rights() {
        if(_mdRights == null) {
            _mdRights = new MD_Rights(this);
        }
        return _mdRights;
    }

    /**
     * @return The MD_Relation group
     */
    public MD_Relation getMD_Relation() {
        if(_mdRelation == null) {
            _mdRelation = new MD_Relation(this);
        }
        return _mdRelation;
    }

    /**
     * @return The MD_Annotation group
     */
    public MD_Annotation getMD_Annotation() {
        if(_mdAnnotation == null) {
            _mdAnnotation = new MD_Annotation(this);
        }
        return _mdAnnotation;
    }

    /**
     * @return The MD_Classification group
     */
    public MD_Classification getMD_Classification() {
        if(_mdClassification == null) {
            _mdClassification = new MD_Classification(this);
        }
        return _mdClassification;
    }

}
