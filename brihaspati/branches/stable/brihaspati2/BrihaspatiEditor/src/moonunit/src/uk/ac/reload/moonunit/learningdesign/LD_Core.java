/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2003 Oleg Liber, Bill Olivier, Phillip Beauvoir
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

package uk.ac.reload.moonunit.learningdesign;

import uk.ac.reload.jdom.XMLDocument;

/**
 * Core IMS Learning Design Methods and Functionality (none at the moment)
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_Core.java,v 1.1 1998/03/25 20:31:04 ynsingh Exp $
 */
public class LD_Core {

    public static final String ROOT_NAME = "learning-design";

    public static final String ACT = "act";
    public static final String ACTIVITIES = "activities";
    public static final String ACTIVITY_STRUCTURE = "activity-structure";
    public static final String ACTIVITY_STRUCTURE_REF = "activity-structure-ref";
    public static final String CLASS = "class";
    public static final String COMPLETE_ACT = "complete-act";
    public static final String COMPLETE_UNIT_OF_LEARNING = "complete-unit-of-learning";
    public static final String CONFERENCE = "conference";
    public static final String CONFERENCE_MANAGER = "conference-manager";
    public static final String CONFERENCE_TYPE = "conference-type";
    public static final String CREATE_NEW = "create-new";
    public static final String EMAIL_DATA = "email-data";
    public static final String ENVIRONMENT = "environment";
    public static final String ENVIRONMENTS = "environments";
    public static final String ENVIRONMENT_REF = "environment-ref";
    public static final String HREF = "href";
    public static final String IDENTIFIER = "identifier";
    public static final String IDENTIFIER_REF = "identifierref";
    public static final String INFORMATION = "information";
    public static final String INDEX = "index";
    public static final String INDEX_CLASS = "index-class";
    public static final String INDEX_ELEMENT = "index-element";
    public static final String INDEX_SEARCH = "index-search";
    public static final String INDEX_TYPE_OF_ELEMENT = "index-type-of-element";
    public static final String ISVISIBLE = "isvisible";
    public static final String ITEM = "item";
    public static final String LEARNER = "learner";
    public static final String LEARNING_ACTIVITY = "learning-activity";
    public static final String LEARNING_ACTIVITY_REF = "learning-activity-ref";
    public static final String LEARNING_OBJECT = "learning-object";
    public static final String LEVEL = "level";
    public static final String MATCH_PERSONS = "match-persons";
    public static final String MAX_PERSONS = "max-persons";
    public static final String METADATA = "metadata";
    public static final String METHOD = "methos";
    public static final String MIN_PERSONS = "min-persons";
    public static final String MODERATOR = "moderator";
    public static final String MONITOR = "monitor";
    public static final String NUMBER_TO_SELECT = "number-to-select";
    public static final String OBSERVER = "observer";
    public static final String PARAMETERS = "parameters";
    public static final String PARTICIPANT = "participant";
    public static final String PLAY = "play";
    public static final String REF = "ref";
    public static final String ROLES = "roles";
    public static final String ROLE_PART = "role-part";
    public static final String ROLE_REF = "role-ref";
    public static final String SEARCH = "search";
    public static final String SEARCH_TYPE = "search-type";
    public static final String SELECT = "select";
    public static final String SENDMAIL = "send-mail";
    public static final String SERVICE = "service";
    public static final String SORT = "sort";
    public static final String STAFF = "staff";
    public static final String STRUCTURE_TYPE = "structure-type";
    public static final String SUPPORT_ACTIVITY = "support-activity";
    public static final String SUPPORT_ACTIVITY_REF = "support-activity-ref";
    public static final String TIME_LIMIT = "time-limit";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String UOL_HREF = "unit-of-learning-href";
    public static final String URI = "uri";
    public static final String USER_CHOICE = "user-choice";
    public static final String VERSION = "version";
    public static final String WHEN_LAST_ACT_COMPLETED = "when-last-act-completed";
    public static final String WHEN_PLAY_COMPLETED = "when-play-completed";
    public static final String WHEN_ROLE_PART_COMPLETED = "when-role-part-completed";

    
    /**
     * The JDOM Document that forms the Learning Design document that we shall be working on
     */
    private XMLDocument _doc;

    /**
     * Constructor
     */
    public LD_Core(XMLDocument doc) {
        _doc = doc;
    }

}