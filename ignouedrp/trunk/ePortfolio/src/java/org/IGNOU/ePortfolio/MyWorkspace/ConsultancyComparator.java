/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import java.util.Comparator;
import org.IGNOU.ePortfolio.Model.ConsultancyNature;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 11-07-2012
 */
public class ConsultancyComparator implements Comparator<ConsultancyNature> {

    @Override
    public int compare(ConsultancyNature c1, ConsultancyNature c2) {
        String Con1 = c1.getCNatureId().toString();
        String Con2 = c2.getCNatureId().toString();
        return Con1.compareTo(Con2);
    }
}
