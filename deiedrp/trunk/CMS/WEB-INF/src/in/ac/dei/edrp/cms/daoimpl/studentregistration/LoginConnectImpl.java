package in.ac.dei.edrp.cms.daoimpl.studentregistration;

import in.ac.dei.edrp.cms.dao.studentregistration.LoginConnectDao;
import in.ac.dei.edrp.cms.domain.LoginInfoGetter;

/**********************************************************************************
 * $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      .............
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/
import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;


/**
 * Server-side implementation of RPC service <code> LoginConnect </code>
 * @author Manpreet Kaur
 * @date 22-01-2011
 * @version 1.0
 */
public class LoginConnectImpl extends SqlMapClientDaoSupport
    implements LoginConnectDao {
    private static Logger logObj = Logger.getLogger(LoginConnectImpl.class);

    //	
    /**
     * Method for checking valid login and  getting student's id
     * @param input object containing user-name and password
     * @return LoginInfoGetter object containing student's id
     */
    public LoginInfoGetter getStudentLoginInfo(LoginInfoGetter input)
        throws Exception {
        try {
            List<?> list = null;
            list = getSqlMapClientTemplate()
                       .queryForList("studentRegistration.userInfoFromRegNo", input);

            LoginInfoGetter[] stdResultObject = list.toArray(new LoginInfoGetter[list.size()]);

            if (stdResultObject.length == 1) {
                return stdResultObject[0];
            } else {
                throw new Exception("wrong");
            }
        } catch (Exception e) {
            System.out.println(e);
            logObj.error("in getStudentLoginInfo : " + e);
            throw e;
        }
    }

    /**
     * Method for checking if student is already registered in current session or not
     * @param user_name registration or roll number of student
     */
    public String getAlreadyRegistered(String user_name)
        throws Exception {
        try {
            List<?> resultList = getSqlMapClientTemplate()
                                     .queryForList("studentRegistration.alreadyRegistered",
                    user_name);

            if (resultList.size() > 0) {
                throw new Exception(
                    "You are already registered for this session");
            }
        } catch (Exception e) {
            logObj.error("Exception in getAlreadyRegistered" + " : " + e);
            throw e;
        }

        return null;
    }
    
    public LoginInfoGetter getStudentLoginInfo1(LoginInfoGetter input) throws Exception {
    	try {
    	List<?> list = null;
    	list = getSqlMapClientTemplate()
    	.queryForList("studentRegistration.userInfoFromRegNo1", input);

    	LoginInfoGetter[] stdResultObject = list.toArray(new LoginInfoGetter[list.size()]);

    	if (stdResultObject.length == 1) {
    	return stdResultObject[0];
    	} else {
    	throw new Exception("wrong");
    	}
    	} catch (Exception e) {
    	System.out.println(e);
    	logObj.error("in getStudentLoginInfo : " + e);
    	throw e;
    	}
    	}
}
