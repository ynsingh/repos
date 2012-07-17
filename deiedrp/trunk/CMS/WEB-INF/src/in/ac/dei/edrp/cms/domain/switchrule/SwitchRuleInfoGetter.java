/*
 * @(#) SwitchRuleInfoGetter.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.cms.domain.switchrule;


/**
 * This bean file is associated with
 * Switch Rule Setup.
 * @author Ashish
 * @date 26 Feb 2011
 * @version 1.0
 */
public class SwitchRuleInfoGetter {
    private String ruleId;
    private String userId;
    private String ruleCode1;
    private String ruleCode2;
    private String ruleCode3;
    private String ruleCode4;
    private String ruleCode5;
    private String ruleCode6;
    private String ruleFormula;
    private String componentId;
    private String componentDescription;
    private String activity;
    private String ruleDesc3;
    private String ruleDesc4;
    private String ruleDesc5;
    private String ruleDesc6;
    
    

    public String getRuleDesc3() {
		return ruleDesc3;
	}

	public void setRuleDesc3(String ruleDesc3) {
		this.ruleDesc3 = ruleDesc3;
	}

	public String getRuleDesc4() {
		return ruleDesc4;
	}

	public void setRuleDesc4(String ruleDesc4) {
		this.ruleDesc4 = ruleDesc4;
	}

	public String getRuleDesc5() {
		return ruleDesc5;
	}

	public void setRuleDesc5(String ruleDesc5) {
		this.ruleDesc5 = ruleDesc5;
	}

	public String getRuleDesc6() {
		return ruleDesc6;
	}

	public void setRuleDesc6(String ruleDesc6) {
		this.ruleDesc6 = ruleDesc6;
	}

	/**
     * default Constructor
     */
    public SwitchRuleInfoGetter() {
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getRuleFormula() {
        return ruleFormula;
    }

    public void setRuleFormula(String ruleFormula) {
        this.ruleFormula = ruleFormula;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRuleCode1() {
        return ruleCode1;
    }

    public void setRuleCode1(String ruleCode1) {
        this.ruleCode1 = ruleCode1;
    }

    public String getRuleCode2() {
        return ruleCode2;
    }

    public void setRuleCode2(String ruleCode2) {
        this.ruleCode2 = ruleCode2;
    }

    public String getRuleCode3() {
        return ruleCode3;
    }

    public void setRuleCode3(String ruleCode3) {
        this.ruleCode3 = ruleCode3;
    }

    public String getRuleCode4() {
        return ruleCode4;
    }

    public void setRuleCode4(String ruleCode4) {
        this.ruleCode4 = ruleCode4;
    }

    public String getRuleCode5() {
        return ruleCode5;
    }

    public void setRuleCode5(String ruleCode5) {
        this.ruleCode5 = ruleCode5;
    }

    public String getRuleCode6() {
        return ruleCode6;
    }

    public void setRuleCode6(String ruleCode6) {
        this.ruleCode6 = ruleCode6;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }
}
