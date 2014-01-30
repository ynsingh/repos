/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Assignment;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinay
 */
public class AssignmentQTypeAction extends ActionSupport {

    private int mcqNo, noofOpt, fillNo, tfNo, matchNo, shortansNo, longansNo;
    private boolean mcq, fill, tf, match, shortans, longans;
    private List<Integer> mcqQuestion = new ArrayList<Integer>();
    private List<Integer> noofOptList = new ArrayList<Integer>();
    private List<Integer> fillQuestion = new ArrayList<Integer>();
    private List<Integer> tfQuestion = new ArrayList<Integer>();
    private List<Integer> matchQuestion = new ArrayList<Integer>();
    private List<Integer> shortansQuestion = new ArrayList<Integer>();
    private List<Integer> longansQuestion = new ArrayList<Integer>();

    public AssignmentQTypeAction() {
    }

    @Override
    public String execute() throws Exception {
        if (mcq == true) {
            for (int i = 0; i < getMcqNo(); i++) {
                mcqQuestion.add(getMcqNo());
            }
            for (int i = 0; i < noofOpt; i++) {
                noofOptList.add(i);
            }
        }
        if (fill == true) {
            for (int i = 0; i < fillNo; i++) {
                fillQuestion.add(fillNo);
            }
        }
        if (tf == true) {
            for (int i = 0; i < tfNo; i++) {
                tfQuestion.add(tfNo);
            }
        }
        if (match == true) {
            for (int i = 0; i < matchNo; i++) {
                matchQuestion.add(matchNo);
            }
        }
        if (shortans == true) {
            for (int i = 0; i < shortansNo; i++) {
                shortansQuestion.add(shortansNo);
            }
        }
        if (longans == true) {
            for (int i = 0; i < longansNo; i++) {
                longansQuestion.add(longansNo);
            }
        }
//        for (int i = 0; i < QId.length; i++) {
//            for (int j = 0; j < mcqCount[i]; j++) {
//                if (QId[i].equals("MCQ")) {
//                    getTextBox().add(i, QId[j]);
//                }
//                if (QId[i].equals("Short Text")) {
//                    getTextarea().add(QId[j]);
//                }
//            }
//        }
        return SUCCESS;
    }

    /**
     * @return the mcqNo
     */
    public int getMcqNo() {
        return mcqNo;
    }

    /**
     * @param mcqNo the mcqNo to set
     */
    public void setMcqNo(int mcqNo) {
        this.mcqNo = mcqNo;
    }

    /**
     * @return the fillNo
     */
    public int getFillNo() {
        return fillNo;
    }

    /**
     * @param fillNo the fillNo to set
     */
    public void setFillNo(int fillNo) {
        this.fillNo = fillNo;
    }

    /**
     * @return the tfNo
     */
    public int getTfNo() {
        return tfNo;
    }

    /**
     * @param tfNo the tfNo to set
     */
    public void setTfNo(int tfNo) {
        this.tfNo = tfNo;
    }

    /**
     * @return the matchNo
     */
    public int getMatchNo() {
        return matchNo;
    }

    /**
     * @param matchNo the matchNo to set
     */
    public void setMatchNo(int matchNo) {
        this.matchNo = matchNo;
    }

    /**
     * @return the shortansNo
     */
    public int getShortansNo() {
        return shortansNo;
    }

    /**
     * @param shortansNo the shortansNo to set
     */
    public void setShortansNo(int shortansNo) {
        this.shortansNo = shortansNo;
    }

    /**
     * @return the longansNo
     */
    public int getLongansNo() {
        return longansNo;
    }

    /**
     * @param longansNo the longansNo to set
     */
    public void setLongansNo(int longansNo) {
        this.longansNo = longansNo;
    }

    /**
     * @return the mcq
     */
    public boolean isMcq() {
        return mcq;
    }

    /**
     * @param mcq the mcq to set
     */
    public void setMcq(boolean mcq) {
        this.mcq = mcq;
    }

    /**
     * @return the fill
     */
    public boolean isFill() {
        return fill;
    }

    /**
     * @param fill the fill to set
     */
    public void setFill(boolean fill) {
        this.fill = fill;
    }

    /**
     * @return the tf
     */
    public boolean isTf() {
        return tf;
    }

    /**
     * @param tf the tf to set
     */
    public void setTf(boolean tf) {
        this.tf = tf;
    }

    /**
     * @return the match
     */
    public boolean isMatch() {
        return match;
    }

    /**
     * @param match the match to set
     */
    public void setMatch(boolean match) {
        this.match = match;
    }

    /**
     * @return the shortans
     */
    public boolean isShortans() {
        return shortans;
    }

    /**
     * @param shortans the shortans to set
     */
    public void setShortans(boolean shortans) {
        this.shortans = shortans;
    }

    /**
     * @return the longans
     */
    public boolean isLongans() {
        return longans;
    }

    /**
     * @param longans the longans to set
     */
    public void setLongans(boolean longans) {
        this.longans = longans;
    }

    /**
     * @return the mcqQuestion
     */
    public List<Integer> getMcqQuestion() {
        return mcqQuestion;
    }

    /**
     * @param mcqQuestion the mcqQuestion to set
     */
    public void setMcqQuestion(List<Integer> mcqQuestion) {
        this.mcqQuestion = mcqQuestion;
    }

    /**
     * @return the fillQuestion
     */
    public List<Integer> getFillQuestion() {
        return fillQuestion;
    }

    /**
     * @param fillQuestion the fillQuestion to set
     */
    public void setFillQuestion(List<Integer> fillQuestion) {
        this.fillQuestion = fillQuestion;
    }

    /**
     * @return the tfQuestion
     */
    public List<Integer> getTfQuestion() {
        return tfQuestion;
    }

    /**
     * @param tfQuestion the tfQuestion to set
     */
    public void setTfQuestion(List<Integer> tfQuestion) {
        this.tfQuestion = tfQuestion;
    }

    /**
     * @return the matchQuestion
     */
    public List<Integer> getMatchQuestion() {
        return matchQuestion;
    }

    /**
     * @param matchQuestion the matchQuestion to set
     */
    public void setMatchQuestion(List<Integer> matchQuestion) {
        this.matchQuestion = matchQuestion;
    }

    /**
     * @return the shortansQuestion
     */
    public List<Integer> getShortansQuestion() {
        return shortansQuestion;
    }

    /**
     * @param shortansQuestion the shortansQuestion to set
     */
    public void setShortansQuestion(List<Integer> shortansQuestion) {
        this.shortansQuestion = shortansQuestion;
    }

    /**
     * @return the longansQuestion
     */
    public List<Integer> getLongansQuestion() {
        return longansQuestion;
    }

    /**
     * @param longansQuestion the longansQuestion to set
     */
    public void setLongansQuestion(List<Integer> longansQuestion) {
        this.longansQuestion = longansQuestion;
    }

    /**
     * @return the noofOpt
     */
    public int getNoofOpt() {
        return noofOpt;
    }

    /**
     * @param noofOpt the noofOpt to set
     */
    public void setNoofOpt(int noofOpt) {
        this.noofOpt = noofOpt;
    }

    /**
     * @return the noofOptList
     */
    public List<Integer> getNoofOptList() {
        return noofOptList;
    }

    /**
     * @param noofOptList the noofOptList to set
     */
    public void setNoofOptList(List<Integer> noofOptList) {
        this.noofOptList = noofOptList;
    }

}
