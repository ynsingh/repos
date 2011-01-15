package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;
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


/**
 * @author Manpreet Kaur
 */
public class CM_progTermDetailGetter implements IsSerializable{
	
	private String entity_program_term_id;
	private String program_id;
	private String program_name;
	private String minimum_sgpa;
	private String term_name;
	private String number_of_teaching_days;
	private String duration_in_weeks;
	private String term_start_date;
	private String term_end_date;
	private String total_credits;
	private String insert_time;
	private String modification_time;
	private String creator_id;
	private String modifier_id;
	
	/**
	 * @return the entity_program_term_id
	 */
	public String getEntity_program_term_id() {
		return entity_program_term_id;
	}
	/**
	 * @param entity_program_term_id the entity_program_term_id to set
	 */
	public void setEntity_program_term_id(String entity_program_term_id) {
		this.entity_program_term_id = entity_program_term_id;
	}
	/**
	 * @return the program_id
	 */
	public String getProgram_id() {
		return program_id;
	}
	/**
	 * @param program_id the program_id to set
	 */
	public void setProgram_id(String program_id) {
		this.program_id = program_id;
	}
	/**
	 * @return the minimum_sgpa
	 */
	public String getMinimum_sgpa() {
		return minimum_sgpa;
	}
	/**
	 * @param minimum_sgpa the minimum_sgpa to set
	 */
	public void setMinimum_sgpa(String minimum_sgpa) {
		this.minimum_sgpa = minimum_sgpa;
	}
	/**
	 * @return the term_name
	 */
	public String getTerm_name() {
		return term_name;
	}
	/**
	 * @param term_name the term_name to set
	 */
	public void setTerm_name(String term_name) {
		this.term_name = term_name;
	}
	/**
	 * @return the number_of_teaching_days
	 */
	public String getNumber_of_teaching_days() {
		return number_of_teaching_days;
	}
	/**
	 * @param number_of_teaching_days the number_of_teaching_days to set
	 */
	public void setNumber_of_teaching_days(String number_of_teaching_days) {
		this.number_of_teaching_days = number_of_teaching_days;
	}
	/**
	 * @return the duration_in_weeks
	 */
	public String getDuration_in_weeks() {
		return duration_in_weeks;
	}
	/**
	 * @param duration_in_weeks the duration_in_weeks to set
	 */
	public void setDuration_in_weeks(String duration_in_weeks) {
		this.duration_in_weeks = duration_in_weeks;
	}
	/**
	 * @return the term_start_date
	 */
	public String getTerm_start_date() {
		return term_start_date;
	}
	/**
	 * @param term_start_date the term_start_date to set
	 */
	public void setTerm_start_date(String term_start_date) {
		this.term_start_date = term_start_date;
	}
	/**
	 * @return the term_end_date
	 */
	public String getTerm_end_date() {
		return term_end_date;
	}
	/**
	 * @param term_end_date the term_end_date to set
	 */
	public void setTerm_end_date(String term_end_date) {
		this.term_end_date = term_end_date;
	}
	/**
	 * @return the total_credits
	 */
	public String getTotal_credits() {
		return total_credits;
	}
	/**
	 * @param total_credits the total_credits to set
	 */
	public void setTotal_credits(String total_credits) {
		this.total_credits = total_credits;
	}
	/**
	 * @return the insert_time
	 */
	public String getInsert_time() {
		return insert_time;
	}
	/**
	 * @param insert_time the insert_time to set
	 */
	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}
	/**
	 * @return the modification_time
	 */
	public String getModification_time() {
		return modification_time;
	}
	/**
	 * @param modification_time the modification_time to set
	 */
	public void setModification_time(String modification_time) {
		this.modification_time = modification_time;
	}
	/**
	 * @return the creator_id
	 */
	public String getCreator_id() {
		return creator_id;
	}
	/**
	 * @param creator_id the creator_id to set
	 */
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	/**
	 * @return the modifier_id
	 */
	public String getModifier_id() {
		return modifier_id;
	}
	/**
	 * @param modifier_id the modifier_id to set
	 */
	public void setModifier_id(String modifier_id) {
		this.modifier_id = modifier_id;
	}
	public String getProgram_name() {
		return program_name;
	}
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
}
