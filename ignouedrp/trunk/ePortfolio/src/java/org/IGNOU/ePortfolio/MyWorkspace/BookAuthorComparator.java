/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import java.util.Comparator;
import org.IGNOU.ePortfolio.Model.BookChapterAuthor;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 11-07-2012
 */
public class BookAuthorComparator implements Comparator<BookChapterAuthor> {

    @Override
    public int compare(BookChapterAuthor BA1, BookChapterAuthor BA2) {
        String bA1 = BA1.getAuthorId().toString();
        String bA2 = BA2.getAuthorId().toString();
        return bA1.compareTo(bA2);
    }
}
