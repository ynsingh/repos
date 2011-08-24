
package com.erp.nfes;

import java.io.Serializable;
import java.util.HashMap;


public class QuestionsVO implements Serializable{

	private String name;			//Name of the question item.
	private int number;				//The number value of the question item.
	private String description;		//The description of the question item.
	private String creator;			//The creator of the question.
	private String time;			//The time of creation.
	private String prompt;			//The prompt of the question.
	private String itemtype;		//The item type of the question.
	private String prioritem;		//The previous item of the question. Normally not stored.
	private String nextitem;		//The next item of the question. Normally not stored.
	private String action;			//The action object for the question.
	private String region_template_name;
	private String template_name;
	private int region_id;
	private int item_sequence;
	private String choice;
	private String code;
	private String value;
	private HashMap codechoicePair;
	

	
	//Get and Set functions for the above question parameters.
	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public String getItemtype() {
		return itemtype;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getNextitem() {
		return nextitem;
	}

	/**
	 * @return
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return
	 */
	public String getPrioritem() {
		return prioritem;
	}

	/**
	 * @return
	 */
	public String getPrompt() {
		return prompt;
	}

	/**
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @param string
	 */
	public void setCreator(String string) {
		creator = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param string
	 */
	public void setItemtype(String string) {
		itemtype = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setNextitem(String string) {
		nextitem = string;
	}

	/**
	 * @param i
	 */
	public void setNumber(int i) {
		number = i;
	}

	/**
	 * @param string
	 */
	public void setPrioritem(String string) {
		prioritem = string;
	}

	/**
	 * @param string
	 */
	public void setPrompt(String string) {
		prompt = string;
	}

	/**
	 * @param string
	 */
	public void setTime(String string) {
		time = string;
	}

	/**
	 * @return
	 */
	public int getItem_sequence() {
		return item_sequence;
	}

	/**
	 * @return
	 */
	public int getRegion_id() {
		return region_id;
	}

	/**
	 * @return
	 */
	public String getTemplate_name() {
		return template_name;
	}

	/**
	 * @param i
	 */
	public void setItem_sequence(int i) {
		item_sequence = i;
	}

	/**
	 * @param i
	 */
	public void setRegion_id(int i) {
		region_id = i;
	}

	/**
	 * @param string
	 */
	public void setTemplate_name(String string) {
		template_name = string;
	}

	/**
	 * @return
	 */
	public String getChoice() {
		return choice;
	}

	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param string
	 */
	public void setChoice(String string) {
		choice = string;
	}

	/**
	 * @param string
	 */
	public void setCode(String string) {
		code = string;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

	/**
	 * @return
	 */
	public HashMap getCodechoicePair() {
		return codechoicePair;
	}

	/**
	 * @param map
	 */
	public void setCodechoicePair(HashMap map) {
		codechoicePair = map;
	}

	/**
	 * @return
	 */
	public String getRegion_template_name() {
		return region_template_name;
	}

	/**
	 * @param string
	 */
	public void setRegion_template_name(String string) {
		region_template_name = string;
	}

	



}
