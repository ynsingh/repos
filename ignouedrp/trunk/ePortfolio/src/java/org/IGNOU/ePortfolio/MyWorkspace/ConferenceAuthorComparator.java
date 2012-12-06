/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import java.util.Comparator;
import org.IGNOU.ePortfolio.Model.ConferenceAuthors;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 11-07-2012
 */
public class ConferenceAuthorComparator implements Comparator<ConferenceAuthors> {

    @Override
    public int compare(ConferenceAuthors Conf1, ConferenceAuthors Conf2) {
        String aId1 = Conf1.getConferenceAuthorsId().toString();
        String aId2 = Conf2.getConferenceAuthorsId().toString();
        return aId1.compareTo(aId2);
    }
}