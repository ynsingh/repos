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
package in.ac.dei.edrp.client.Login;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Shikhar Sinha
 */
import in.ac.dei.edrp.client.CM_userInfoGetter;

import java.util.List;


/**
 * The async counterpart of <code>CMconnect</code>.
 */

//Creating the Asyncronous Function For the Server
public interface CM_LoginConnectSAsync {
    void Login(String username, String password,
        AsyncCallback<CM_userInfoGetter[]> callback);

    void methodLogin(String username, String password,
        AsyncCallback<CM_userInfoGetter[]> callback);

    void getPageAuthority(String user_id,
        AsyncCallback<CM_userInfoGetter[]> asyncCallback);

    void getPrimaryAuthorities(String user_name,
        AsyncCallback<CM_userInfoGetter[]> asyncCallback);

    void getAuthorityOnPage(String pagename, String uniid,
        AsyncCallback<CM_userInfoGetter[]> asyncCallback);

    void getsecondaryauthorities(String university_id, String pagename,
        AsyncCallback<List<String>> asyncCallback);
}
