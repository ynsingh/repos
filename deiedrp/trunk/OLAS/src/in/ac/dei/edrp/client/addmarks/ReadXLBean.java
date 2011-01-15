/** $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **********************************************************************************/
package in.ac.dei.edrp.client.addmarks;

import java.io.Serializable;
/**
 * @author On Demand Examination Team
 */


@SuppressWarnings("serial")
public class ReadXLBean implements Serializable{
	//declares variables for the module
	public String entityID;
	public String entityType;
	public String entityName;
	public String entityEmployeeID;
	public String courseCode;
	public String question;
	public String questionType;
	public String questionMarks;
	public String chapterNumber;
	public String status;
	public String questionID;
	public String questionPool;
	public String level;
	public String answer;
	public String options;
	//getters & setters of the variables
	public String getEntityID() {
		return entityID;
	}
	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityEmployeeID() {
		return entityEmployeeID;
	}
	public void setEntityEmployeeID(String entityEmployeeID) {
		this.entityEmployeeID = entityEmployeeID;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionMarks() {
		return questionMarks;
	}
	public void setQuestionMarks(String questionMarks) {
		this.questionMarks = questionMarks;
	}
	public String getChapterNumber() {
		return chapterNumber;
	}
	public void setChapterNumber(String chapterNumber) {
		this.chapterNumber = chapterNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getQuestionPool() {
		return questionPool;
	}
	public void setQuestionPool(String questionPool) {
		this.questionPool = questionPool;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
}
