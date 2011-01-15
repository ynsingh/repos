/** $URL:
* Licensed under the Educational Community License, Version 1.0 (the "License");
* you may not use this file except in compliance with the License.
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
**********************************************************************************/
package in.ac.dei.edrp.server;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class creates connection to database and used in each method
 * @author On Demand Examination Team
*/

public class DatabaseConnection {
	public Connection con;
	public Connection createconnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String username = "root";
            String password = "";
            String mysqlurl = "jdbc:mysql://localhost:3306/mhrd1";
            con = DriverManager.getConnection(mysqlurl, username, password);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return con;
    }
}
