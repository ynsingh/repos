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


/**
 * @author Shikhar Sinha
 */
import java.util.List;

import in.ac.dei.edrp.client.CM_userInfoGetter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */

//giving the HelloS path to the Server Connection
@RemoteServiceRelativePath("helloLogin")
//Function For Connect Page Starts
public interface CM_LoginConnectS extends RemoteService {
    CM_userInfoGetter[] Login(String username, String password);

    CM_userInfoGetter[] methodLogin(String username, String password)
        throws Exception;

    CM_userInfoGetter[] getPageAuthority(String user_id);

    CM_userInfoGetter[] getPrimaryAuthorities(String user_name);

    List<String> getsecondaryauthorities(String uni_id, String pagename);

    CM_userInfoGetter[] getAuthorityOnPage(String pagename, String uniid);
}