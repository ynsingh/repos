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
package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * The server side implementation of the RPC service.
 */

//Serializing the Method
@SuppressWarnings("serial")
/**
 * Developing the Server Implementation Page for Login 
 */
public class CM_LoginConnectSImpl extends RemoteServiceServlet
    implements CM_LoginConnectS {
    SqlMapClient client = SqlMapManager.getSqlMapClient();

    public CM_userInfoGetter[] Login(String username, String password) {
        try {
            CM_userInfoGetter CMUIG = new CM_userInfoGetter();
            CMUIG.setuID(username);
            CMUIG.setUser_name(username);
            CMUIG.setpassword(password);

            List<?> list;
            list = client.queryForList("selectAllUserInfo", CMUIG);

            CM_userInfoGetter[] CMUIG1 = (CM_userInfoGetter[]) list.toArray(new CM_userInfoGetter[list.size()]);

            if (CMUIG1.length > 0) {
                if (((CMUIG1[0].getUser_name()).equals(username))) {
                    return (CM_userInfoGetter[]) list.toArray(new CM_userInfoGetter[list.size()]);
                } else {
                    CM_userInfoGetter[] CMUIG2 = new CM_userInfoGetter[2];
                    CMUIG2[0].setuID("Invalid");
                    CMUIG2[0].setpassword("Invalid");

                    return CMUIG2;
                }
            } else {
                CM_userInfoGetter[] CMUIG3 = new CM_userInfoGetter[2];

                CMUIG3[0] = new CM_userInfoGetter();

                CMUIG3[0].setuID("Invalid");
                CMUIG3[0].setpassword("Invalid");

                return CMUIG3;
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }

    public CM_userInfoGetter[] methodLogin(String username, String password)
        throws Exception {
        try {
            CM_userInfoGetter CMUIG = new CM_userInfoGetter();
            CMUIG.setUser_name(username);
            CMUIG.setpassword(password);

            List<?> list;
            list = client.queryForList("selectAllUserInfo", CMUIG);

            CM_userInfoGetter[] CMUIG1 = (CM_userInfoGetter[]) list.toArray(new CM_userInfoGetter[list.size()]);

            if (CMUIG1.length > 0) {
                // if ((CMUIG1[0].getActivated()).equals(true)) {
                return (CM_userInfoGetter[]) list.toArray(new CM_userInfoGetter[list.size()]);               
            } else {
                CM_userInfoGetter[] CMUIG3 = new CM_userInfoGetter[2];

                CMUIG3[0] = new CM_userInfoGetter();

                CMUIG3[0].setUser_name("Invalid");
                CMUIG3[0].setpassword("Invalid");

                return CMUIG3;
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception(e);
        }
    }

    @SuppressWarnings("unchecked")
    public CM_userInfoGetter[] getPageAuthority(String user_id) {
        CM_userInfoGetter cmg = new CM_userInfoGetter();
        cmg.setUser_id(user_id);

        List l1;

        try {
            l1 = client.queryForList("getmainpages", cmg);

            return (CM_userInfoGetter[]) l1.toArray(new CM_userInfoGetter[l1.size()]);
        } catch (Exception e) {
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_userInfoGetter[] getPageAuthorityNew(CM_userInfoGetter userInfo) {
//        CM_userInfoGetter cmg = new CM_userInfoGetter();
//        cmg.setUser_id(user_id);
//System.out.println("hi inside impl of page  authority");
        List l1;

        try {
//        	System.out.println("before calling the query"+userInfo.getApplication()+userInfo.getUser_name()+userInfo.getInstituteID());
            l1 = client.queryForList("getMainPagesNew", userInfo);
//            System.out.println("nupur page authority new "+l1.size());
            return (CM_userInfoGetter[]) l1.toArray(new CM_userInfoGetter[l1.size()]);
        } catch (Exception e) {
        	System.out.println(e.getStackTrace());
        	e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_userInfoGetter[] getPrimaryAuthorities(String user_name) {

        CM_userInfoGetter cmp = new CM_userInfoGetter();

        cmp.setUser_id(user_name);

        List l1;

        try {
            l1 = client.queryForList("getprimaryauthorities", cmp);


            return (CM_userInfoGetter[]) l1.toArray(new CM_userInfoGetter[l1.size()]);
        } catch (Exception e) {
            System.out.println("Exception in primaryauthorities" + e);
        }

        return null;
    }
    
    @SuppressWarnings("unchecked")
    public CM_userInfoGetter[] getPrimaryAuthoritiesNew(CM_userInfoGetter userInfo) {
//    	System.out.println("nupur promary authority new ");
        List l1;
        try {
            l1 = client.queryForList("getprimaryauthoritiesNew", userInfo);
//            System.out.println("nupur promary authority new "+l1.size());
            return (CM_userInfoGetter[]) l1.toArray(new CM_userInfoGetter[l1.size()]);
            
        } catch (Exception e) {
            System.out.println("Exception in primaryauthorities" + e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_userInfoGetter[] getAuthorityOnPage(String pagename, String uniid) {
        CM_userInfoGetter cmp = new CM_userInfoGetter();
        List l1;

        cmp.setUser_id(uniid);
        cmp.setPage(pagename);

        try {
            l1 = client.queryForList("getauthorityonpage", cmp);

            return (CM_userInfoGetter[]) l1.toArray(new CM_userInfoGetter[l1.size()]);
        } catch (Exception e) {
            System.out.println("Exception in getAuthorityOnPage" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<String> getsecondaryauthorities(String uni_id, String pagename) {

        List<String> retAuthority = new ArrayList<String>();

        CM_userInfoGetter cmp = new CM_userInfoGetter();

        cmp.setUser_id(uni_id);
        cmp.setPage(pagename);

        List<CM_userInfoGetter> l1;

        try {
            l1 = client.queryForList("getsecondryauthorities", cmp);


            if (l1.size() > 0) {
                retAuthority.clear();

                String authority = l1.get(0).getAuthority();

                if (authority.charAt(0) == '1') {
                    retAuthority.add("create");
                }

                if (authority.charAt(1) == '1') {
                    retAuthority.add("delete");
                }

                if (authority.charAt(2) == '1') {
                    retAuthority.add("update");
                }

                if (authority.charAt(3) == '1') {
                    retAuthority.add("view");
                }
            }

            return retAuthority;
        } catch (Exception e) {
            System.out.println("Exception in secondryauthorities" + e);
        }

        return null;
    }
}
