/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import java.util.Comparator;
import org.IGNOU.ePortfolio.Model.JournalAuthor;

/**
 * @author IGNOU Team
 * @version 1
 * @since 11-07-2012
 */
public class JournalAuthorComparator implements Comparator<JournalAuthor> {

    @Override
    public int compare(JournalAuthor JA1, JournalAuthor JA2) {
        String J1 = JA1.getJournalAuthorId().toString();
        String J2 = JA2.getJournalAuthorId().toString();
        return J1.compareTo(J2);
    }
}
