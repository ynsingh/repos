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

/*
   Author Name :Devendra Singhal
 */
package in.ac.dei.edrp.client.RPCFiles;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FinalMeritListAsync {

	 void generateFinalMeritList(String userId, String entityId, String programId, AsyncCallback<String> callback);
	 void importOmrMarks(String userId,String programId,String entityId,String testId,String conductDate,String groupStatus,AsyncCallback<String> callback);
}
