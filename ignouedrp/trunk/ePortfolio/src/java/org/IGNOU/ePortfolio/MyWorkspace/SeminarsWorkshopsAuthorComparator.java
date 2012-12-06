/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import java.util.Comparator;
import org.IGNOU.ePortfolio.Model.SeminarsWorkshopsAuthor;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 11-07-2012
 */
public class SeminarsWorkshopsAuthorComparator implements Comparator<SeminarsWorkshopsAuthor> {

    @Override
    public int compare(SeminarsWorkshopsAuthor SW1, SeminarsWorkshopsAuthor SW2) {
        String swid1 = SW1.getSwAuthorId().toString();
        String swid2 = SW2.getSwAuthorId().toString();
        return swid1.compareTo(swid2);
    }
}
