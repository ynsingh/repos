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


/**
 * A xsd Duration type.
 * <p><p>
 * Specifies an amount of time: the duration of an event in relative terms (e.g. the duration given the start
 * datetime of the run of a unit-of-learning.
 * The format - also used in the W3C XML schema specification - is: PnYnMnDTnHnMnS where:<p><p>
 * P is the designator that must always be present.<p>
 * n is a variable where an integer is filled in.<p>
 * nY represents the number of years.<p>
 * nM represents the number of month.<p>
 * nD represents the number of days.<p>
 * T is the date/time separator which must always be present when representing time.<p>
 * nH is the number of hours.<p>
 * nM is the number of minutes.<p>
 * nS is the number of seconds.<p><p>
 * Example: P2Y0M1DT20H10M55S. Meaning that the duration is: 2 years and 0 month and 1 day and 20 hours
 * and 10 minutes and 55 seconds. Limited forms of lexical production are also allowed: For example, a duration of
 * 40 minutes is expressed as PT40M; a duration of 30 days is P30D.
 * <p><p>
 *
 * @author Phillip Beauvoir
 * @version $Id: DurationType.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class DurationType {
    
    int _years, _months, _days, _hours, _mins, _secs;
    
    /**
     * Default Constructor
     */
    public DurationType() {
    }
    
    /**
     * Constructor
     * @param durationString The Duration String
     */
    public DurationType(String durationString) {
        setDurationString(durationString);
    }
    
    /**
     * Constructor with set values
     * 
     * @param years
     * @param months
     * @param days
     * @param hours
     * @param mins
     * @param secs
     */
    public DurationType(int years, int months, int days, int hours, int mins, int secs) {
        _years = years;
        _months = months;
        _days = days;
        _hours = hours;
        _mins = mins;
        _secs = secs;
    }

    /**
     * Set and Parse the duration string.
     * @param durationString The Duration String
     */
    public void setDurationString(String durationString) {
        _years = parseValue(durationString, "y");
        _months = parseValue(durationString, "m");
        _days = parseValue(durationString, "d");
        _hours = parseValue(durationString, "h");
        _mins = parseValue(durationString, "mm");
        _secs = parseValue(durationString, "s");
    }
    
    /**
     * @return The duration String or null if all values are zero
     */
    public String getDurationString() {
        String s = "";
        
        if(getYears() != 0) {
            s = getYears() + "Y";
        }
        if(getMonths() != 0) {
            s += getMonths() + "M";
        }
        if(getDays() != 0) {
            s += getDays() + "D";
        }
        
        if(getHours() != 0 || getMinutes() != 0 || getSeconds() != 0) {
            s += "T";
        }
        
        if(getHours() != 0) {
            s += getHours() + "H";
        }
        
        if(getMinutes() != 0) {
            s += getMinutes() + "M";
        }

        if(getSeconds() != 0) {
            s += getSeconds() + "S";
        }

        return s == "" ? null : "P" + s;
    }
    
    /**
     * @return The years if present or zero
     */
    public int getYears() {
        return _years;
    }
    
    /**
     * Set the Years
     * @param years
     */
    public void setYears(int years) {
        _years = years;
    }
    
    /**
     * @return The months if present or zero
     */
    public int getMonths() {
        return _months;
    }

    /**
     * Set the Months
     * @param months
     */
    public void setMonths(int months) {
        _months = months;
    }
    
    /**
     * @return The days if present or zero
     */
    public int getDays() {
        return _days;
    }

    /**
     * Set the days
     * @param years
     */
    public void setDays(int days) {
        _days = days;
    }
    
    /**
     * @return The hours if present or zero
     */
    public int getHours() {
        return _hours;
    }
    
    /**
     * Set the Hours
     * @param hours
     */
    public void setHours(int hours) {
        _hours = hours;
    }
    
    /**
     * @return The minutes if present or zero
     */
    public int getMinutes() {
        return _mins;
    }
    
    /**
     * Set the Minutes
     * @param years
     */
    public void setMinutes(int mins) {
        _mins = mins;
    }
    
    /**
     * @return The seconds if present or zero
     */
    public int getSeconds() {
        return _secs;
    }

    /**
     * Set the Seconds
     * @param secs
     */
    public void setSeconds(int secs) {
        _secs = secs;
    }
    
    /**
     * Return the value of the numbers just before the delimiter
     * @param delimiter Value of 'y', 'm', 'd', 'h', 'mm', or 's'
     * @return
     */
    private int parseValue(String durationString, String delimiter) {
        // Empty string
        if(isEmptyOrNull(durationString)) {
            return 0;
        }
        
        // No "P" start
        if(!hasStartDesignator(durationString)) {
            return 0;
        }
        
        // Lower case copy
        String d = durationString.toLowerCase();
        
        // If months, then if there's a Time delimiter chop it off before that so we don't confuse 
        // with the "M" for minutes later in the string
        if("m".equals(delimiter)) {
            int t = d.indexOf("t");
            if(t != -1) {
                d = d.substring(0, d.indexOf("t"));
            }
        }
        
        // If hours, mins or secs look for the T delimiter
        else if("h".equals(delimiter) || "mm".equals(delimiter) || "s".equals(delimiter)) {
            // No Time delimiter
            if(!hasTimeDelimiter(durationString)) {
                return 0;
            }
            
            // Create new String at T delimiter
            try {
                d = d.substring(d.indexOf("t"));
            }
            catch(StringIndexOutOfBoundsException ex) {
                return 0;
            }
            
            // If "mm" (minutes) then change to "m"
            if("mm".equals(delimiter)) {
                delimiter = "m";
            }
        }
        
        // Get end position of delimiter
        int endpos = d.indexOf(delimiter);
        if(endpos <= 0) {
            return 0;
        }
        
        // Get start position of number by working backwards from end position until we come
        // to a non-number character or the start of the string
        int startpos;
        for(startpos = endpos - 1; startpos >= 0; startpos--) {
            char c = d.charAt(startpos);
            if(!Character.isDigit(c)) {
                break;
            }
        }
        
        if(startpos < 0) {
            return 0;
        }
        
        String num = d.substring(startpos + 1, endpos);
        
        try {
            return Integer.parseInt(num);
        }
        catch(NumberFormatException ex) {
            return 0;
        }
    }

    
    /**
     * @return True if the first character of the string starts with "P"
     */
    private boolean hasStartDesignator(String durationString) {
        if(isEmptyOrNull(durationString)) {
            return false;
        }

        String d = durationString.toLowerCase();
        return d.indexOf("p") == 0;
    }
    
    /**
     * @return True if there is a Time "T" delimiter
     */
    private boolean hasTimeDelimiter(String durationString) {
        if(isEmptyOrNull(durationString)) {
            return false;
        }

        String d = durationString.toLowerCase();
        return d.indexOf("t") != -1;
    }

    /**
     * @return True if the string is null or the empty tring
     */
    private boolean isEmptyOrNull(String s) {
        return s == null || "".equals(s);
    }
}
