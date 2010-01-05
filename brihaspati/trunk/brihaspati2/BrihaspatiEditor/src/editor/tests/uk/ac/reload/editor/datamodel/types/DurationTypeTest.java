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

package uk.ac.reload.editor.datamodel.types;

import junit.framework.TestCase;


/**
 * DurationTypeTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: DurationTypeTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class DurationTypeTest extends TestCase {

    // ---------------------------------------------------------------------------------------------

    public void testDurationType_intsConstructor() {
        DurationType dt = new DurationType(1, 2, 3, 4, 5, 6);
        assertEquals("Wrong duration string returned", "P1Y2M3DT4H5M6S", dt.getDurationString());

        dt = new DurationType(1, 0, 3, 0, 5, 0);
        assertEquals("Wrong duration string returned", "P1Y3DT5M", dt.getDurationString());

        dt = new DurationType(0, 0, 0, 0, 0, 0);
        assertNull("Wrong duration string returned", dt.getDurationString());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetDurationString() {
        String duration = "P1Y3M2DT1H3M4S";
        DurationType dt = new DurationType(duration);
        assertEquals("Wrong duration string returned", duration, dt.getDurationString());
        
        duration = "P1YT2M";
        dt.setDurationString(duration);
        assertEquals("Wrong duration string returned", duration, dt.getDurationString());

        duration = "P0Y0M0DT0H0M0S";
        dt.setDurationString(duration);
        assertNull("Wrong duration string returned", dt.getDurationString());

        duration = "P1Y";
        dt.setDurationString(duration);
        assertEquals("Wrong duration string returned", duration, dt.getDurationString());

        duration = "P1M";
        dt.setDurationString(duration);
        assertEquals("Wrong duration string returned", duration, dt.getDurationString());

        duration = "P1D";
        dt.setDurationString(duration);
        assertEquals("Wrong duration string returned", duration, dt.getDurationString());

        duration = "PT1H";
        dt.setDurationString(duration);
        assertEquals("Wrong duration string returned", duration, dt.getDurationString());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetYears() {
        DurationType dt = new DurationType();
        
        // Bogus
        
        assertEquals("Wrong Years returned", 0, dt.getYears());
        
        dt.setDurationString("");
        assertEquals("Wrong Years returned", 0, dt.getYears());
        
        dt.setDurationString("P");
        assertEquals("Wrong Years returned", 0, dt.getYears());

        dt.setDurationString("Y");
        assertEquals("Wrong Years returned", 0, dt.getYears());
        
        dt.setDurationString("1Y");
        assertEquals("Wrong Years returned", 0, dt.getYears());
        
        dt.setDurationString("1Y3M");
        assertEquals("Wrong Years returned", 0, dt.getYears());

        dt.setDurationString("PY");
        assertEquals("Wrong Years returned", 0, dt.getYears());

        dt.setDurationString("PtextY");
        assertEquals("Wrong Years returned", 0, dt.getYears());

        // Correct
        
        dt.setDurationString("P4M2D");
        assertEquals("Wrong Years returned", 0, dt.getYears());

        dt.setDurationString("PT40M");
        assertEquals("Wrong Years returned", 0, dt.getYears());

        dt.setDurationString("P0Y");
        assertEquals("Wrong Years returned", 0, dt.getYears());

        dt.setDurationString("P1Y");
        assertEquals("Wrong Years returned", 1, dt.getYears());
    
        dt.setDurationString("P1Y2M3DT20H");
        assertEquals("Wrong Years returned", 1, dt.getYears());

        dt.setDurationString("P01YT20H");
        assertEquals("Wrong Years returned", 1, dt.getYears());

        dt.setDurationString("P200YT20H");
        assertEquals("Wrong Years returned", 200, dt.getYears());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testSetYears() {
        DurationType dt = new DurationType();
        assertNull("Wrong duration string returned", dt.getDurationString());
        
        dt.setYears(12);
        assertEquals("Wrong duration string returned", "P12Y", dt.getDurationString());

        dt.setDurationString("P1Y2M3DT4H5M6S");
        assertEquals("Wrong years", 1, dt.getYears());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetMonths() {
        DurationType dt = new DurationType();

        // Bogus

        assertEquals("Wrong Months returned", 0, dt.getMonths());
        
        dt.setDurationString("");
        assertEquals("Wrong Months returned", 0, dt.getMonths());
        
        dt.setDurationString("P");
        assertEquals("Wrong Months returned", 0, dt.getMonths());

        dt.setDurationString("M");
        assertEquals("Wrong Months returned", 0, dt.getMonths());
        
        dt.setDurationString("1M");
        assertEquals("Wrong Months returned", 0, dt.getMonths());
        
        dt.setDurationString("1Y3M");
        assertEquals("Wrong Months returned", 0, dt.getMonths());

        dt.setDurationString("PM");
        assertEquals("Wrong Months returned", 0, dt.getMonths());

        dt.setDurationString("PtextM");
        assertEquals("Wrong Months returned", 0, dt.getMonths());
        
        // Correct
        
        dt.setDurationString("P1Y");
        assertEquals("Wrong Months returned", 0, dt.getMonths());
    
        dt.setDurationString("P1Y12MT20H23M");
        assertEquals("Wrong Months returned", 12, dt.getMonths());

        dt.setDurationString("P1Y1DT20H23M30S");
        assertEquals("Wrong Months returned", 0, dt.getMonths());

        dt.setDurationString("P1Y2M1DT20H");
        assertEquals("Wrong Months returned", 2, dt.getMonths());

        dt.setDurationString("PT40M");
        assertEquals("Wrong Months returned", 0, dt.getMonths());

        dt.setDurationString("P40M");
        assertEquals("Wrong Months returned", 40, dt.getMonths());

        dt.setDurationString("P0MT12M");
        assertEquals("Wrong Months returned", 0, dt.getMonths());

        dt.setDurationString("M");
        assertEquals("Wrong Months returned", 0, dt.getMonths());
    }

    // ---------------------------------------------------------------------------------------------

    public void testSetMonths() {
        DurationType dt = new DurationType();
        assertNull("Wrong duration string returned", dt.getDurationString());
        
        dt.setMonths(12);
        assertEquals("Wrong duration string returned", "P12M", dt.getDurationString());

        dt.setDurationString("P1Y2M3DT4H5M6S");
        assertEquals("Wrong months", 2, dt.getMonths());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDays() {
        DurationType dt = new DurationType();

        // Bogus

        assertEquals("Wrong Days returned", 0, dt.getDays());
        
        dt.setDurationString("");
        assertEquals("Wrong Days returned", 0, dt.getDays());
        
        dt.setDurationString("P");
        assertEquals("Wrong Days returned", 0, dt.getDays());

        dt.setDurationString("D");
        assertEquals("Wrong Days returned", 0, dt.getDays());
        
        dt.setDurationString("1D");
        assertEquals("Wrong Days returned", 0, dt.getDays());
        
        dt.setDurationString("1Y3M2D");
        assertEquals("Wrong Days returned", 0, dt.getDays());

        dt.setDurationString("PD");
        assertEquals("Wrong Days returned", 0, dt.getDays());

        dt.setDurationString("PtextD");
        assertEquals("Wrong Days returned", 0, dt.getDays());
        
        // Correct
        dt.setDurationString("P1Y");
        assertEquals("Wrong Days returned", 0, dt.getDays());
        
        dt.setDurationString("P1D");
        assertEquals("Wrong Days returned", 1, dt.getDays());

        dt.setDurationString("P1M11D");
        assertEquals("Wrong Days returned", 11, dt.getDays());

        dt.setDurationString("P1Y");
        assertEquals("Wrong Days returned", 0, dt.getDays());
        
        dt.setDurationString("P1Y1M1DT1H1M1S");
        assertEquals("Wrong Days returned", 1, dt.getDays());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testSetDays() {
        DurationType dt = new DurationType();
        assertNull("Wrong duration string returned", dt.getDurationString());
        
        dt.setDays(31);
        assertEquals("Wrong duration string returned", "P31D", dt.getDurationString());

        dt.setDurationString("P1Y2M3DT4H5M6S");
        assertEquals("Wrong days", 3, dt.getDays());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetHours() {
        DurationType dt = new DurationType();

        // Bogus

        assertEquals("Wrong Hours returned", 0, dt.getHours());
        
        dt.setDurationString("");
        assertEquals("Wrong Hours returned", 0, dt.getHours());
        
        dt.setDurationString("P");
        assertEquals("Wrong Hours returned", 0, dt.getHours());

        dt.setDurationString("TH");
        assertEquals("Wrong Hours returned", 0, dt.getHours());
        
        dt.setDurationString("P1H");
        assertEquals("Wrong Hours returned", 0, dt.getHours());

        dt.setDurationString("1H");
        assertEquals("Wrong Hours returned", 0, dt.getHours());
        
        dt.setDurationString("1Y3M2DT4H");
        assertEquals("Wrong Hours returned", 0, dt.getHours());

        dt.setDurationString("PTH");
        assertEquals("Wrong Hours returned", 0, dt.getHours());

        dt.setDurationString("PTtextH");
        assertEquals("Wrong Hours returned", 0, dt.getHours());
        
        // Correct
        
        dt.setDurationString("PT0H");
        assertEquals("Wrong Hours returned", 0, dt.getHours());
        
        dt.setDurationString("PT1H");
        assertEquals("Wrong Hours returned", 1, dt.getHours());
        
        dt.setDurationString("PT1H1M1S");
        assertEquals("Wrong Hours returned", 1, dt.getHours());

        dt.setDurationString("P1Y1MT1H1M1S");
        assertEquals("Wrong Hours returned", 1, dt.getHours());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testSetHours() {
        DurationType dt = new DurationType();
        assertNull("Wrong duration string returned", dt.getDurationString());
        
        dt.setHours(12);
        assertEquals("Wrong duration string returned", "PT12H", dt.getDurationString());

        dt.setDurationString("P1Y2M3DT4H5M6S");
        assertEquals("Wrong hours", 4, dt.getHours());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetMinutes() {
        DurationType dt = new DurationType();

        // Bogus

        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());
        
        dt.setDurationString("");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());
        
        dt.setDurationString("P");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());

        dt.setDurationString("TM");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());
        
        dt.setDurationString("P1M");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());

        dt.setDurationString("1M");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());
        
        dt.setDurationString("1Y3M2DT4M");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());

        dt.setDurationString("PTM");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());

        dt.setDurationString("PTtextM");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());
        
        // Correct
        
        dt.setDurationString("PT0M");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());
        
        dt.setDurationString("PT1M");
        assertEquals("Wrong Minutes returned", 1, dt.getMinutes());
        
        dt.setDurationString("PT1H1M1S");
        assertEquals("Wrong Minutes returned", 1, dt.getMinutes());

        dt.setDurationString("P1Y2MT3H10M5S");
        assertEquals("Wrong Minutes returned", 10, dt.getMinutes());
        
        dt.setDurationString("P1Y2MT3H4S");
        assertEquals("Wrong Minutes returned", 0, dt.getMinutes());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testSetMinutes() {
        DurationType dt = new DurationType();
        assertNull("Wrong duration string returned", dt.getDurationString());
        
        dt.setMinutes(30);
        assertEquals("Wrong duration string returned", "PT30M", dt.getDurationString());

        dt.setDurationString("P1Y2M3DT4H5M6S");
        assertEquals("Wrong minutes", 5, dt.getMinutes());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetSeconds() {
        DurationType dt = new DurationType();

        // Bogus

        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());
        
        dt.setDurationString("");
        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());
        
        dt.setDurationString("P");
        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());

        dt.setDurationString("TS");
        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());
        
        dt.setDurationString("P1S");
        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());

        dt.setDurationString("1S");
        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());
        
        dt.setDurationString("1Y3M2DT4S");
        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());

        dt.setDurationString("PTS");
        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());

        dt.setDurationString("PTtextS");
        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());
        
        // Correct
        
        dt.setDurationString("PT0S");
        assertEquals("Wrong Seconds returned", 0, dt.getSeconds());
        
        dt.setDurationString("PT1S");
        assertEquals("Wrong Seconds returned", 1, dt.getSeconds());
        
        dt.setDurationString("PT1H2M3S");
        assertEquals("Wrong Seconds returned", 3, dt.getSeconds());

        dt.setDurationString("P1Y2MT3H4M5S");
        assertEquals("Wrong Seconds returned", 5, dt.getSeconds());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testSetSeconds() {
        DurationType dt = new DurationType();
        assertNull("Wrong duration string returned", dt.getDurationString());
        
        dt.setSeconds(30);
        assertEquals("Wrong duration string returned", "PT30S", dt.getDurationString());

        dt.setDurationString("P1Y2M3DT4H5M6S");
        assertEquals("Wrong seconds", 6, dt.getSeconds());
    }
    
    // ---------------------------------------------------------------------------------------------
}
