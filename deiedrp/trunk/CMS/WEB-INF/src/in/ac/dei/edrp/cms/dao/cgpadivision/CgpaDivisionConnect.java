/*
 * @(#) CgpaDivisionConnect.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.cms.dao.cgpadivision;

import in.ac.dei.edrp.cms.domain.cgpadivision.CgpaDivisionInfoGetter;

import java.util.List;
import java.util.StringTokenizer;


/**
 * This interface consist the list of methods used
 * in CgpaDivisionImpl file.
 * @author Ashish
 * @date 8 Mar 2011
 * @version 1.0
 */
public interface CgpaDivisionConnect {
    /**
     * Method retrieves list of divisions for the concerned university
     * @param input Object of the referenced bean class
     * @return List
     */
    List<CgpaDivisionInfoGetter> getDivisions(CgpaDivisionInfoGetter input);

    /**
     * This method is used for setting up the details for the divisions
     * on the basis of activity(insert/update)
     * @param input Object of the referenced bean class
     * @return String
     */
    String setDivisionDetails(CgpaDivisionInfoGetter input);

    /**
     * This method retrieves the divisions already defined for the university.
     * @param input Object of the referenced bean class
     * @return List
     */
    List<CgpaDivisionInfoGetter> getSetDivisionRecords(
        CgpaDivisionInfoGetter input);

    /**
     * Method for deleting records(divisions)for the concerned university
     * @param input Object of the referenced bean class
     * @param items selected items to be deleted
     * @return String
     */
    String deleteDivisionRecords(CgpaDivisionInfoGetter input,
        StringTokenizer items);
}
