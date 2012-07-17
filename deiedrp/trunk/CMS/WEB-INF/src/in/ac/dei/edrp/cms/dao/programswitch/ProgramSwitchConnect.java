/*
 * @(#) ProgramSwitchConnect.java
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
package in.ac.dei.edrp.cms.dao.programswitch;

import in.ac.dei.edrp.cms.domain.programswitch.ProgramSwitchInfoGetter;

import java.util.List;
import java.util.StringTokenizer;


/**
 * This interface consist the list of methods used
 * in ProgramSwitchImpl file.
 * @author Ashish
 * @date 14 Mar 2011
 * @version 1.0
 */
public interface ProgramSwitchConnect {
    /**
     * Method for getting the list of programs-combinations
     * for the concerned university
     * @param input object of the referenced bean
     * @return
     */
    List<ProgramSwitchInfoGetter> getInputRecords(ProgramSwitchInfoGetter input);

    /**
     * Method for getting the list Switch types
     * defined for the concerned university
     * @param input object of the referenced bean
     * @return
     */
    List<ProgramSwitchInfoGetter> getSwitchTypes(ProgramSwitchInfoGetter input);

    /**
     * Method for setting up a record
     * of the selected program combination
     * based on the activity(insert/update) for the university
     * @param input object of the referenced bean
     * @return
     */
    String setProgramSwitchDetails(ProgramSwitchInfoGetter input);

    /**
     * Method for getting the list of
     * defined records set for the university
     * @param input object of the referenced bean
     * @return
     */
    List<ProgramSwitchInfoGetter> getSetRecords(ProgramSwitchInfoGetter input);

    /**
     * Method for deleting the selected records from program switch
     * @param items items referenced for deletion
     * @return
     */
    String deleteProgramSwitchRecords(StringTokenizer items);
/**
 * Method to get the old semesters for the program combination
 * @param input object of the referenced bean
 * @return
 */
	List<ProgramSwitchInfoGetter> getOldSemesters(ProgramSwitchInfoGetter input);
/**
 * The method picks the details on the basis of input
 * passed
 * @param input object of the referenced bean
 * @return list
 */
List<ProgramSwitchInfoGetter> getRecords4Counter(ProgramSwitchInfoGetter input);
}
