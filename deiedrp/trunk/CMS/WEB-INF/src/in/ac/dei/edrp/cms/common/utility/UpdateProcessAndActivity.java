/**
 * @(#) UpdateProcessAndActivity.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
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

package in.ac.dei.edrp.cms.common.utility;

import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UpdateProcessAndActivity extends SqlMapClientDaoSupport {

	public boolean updateProcessStartDate(StartActivityBean startActivityBean) {
		boolean b = false;
		try {
			int x = getSqlMapClientTemplate().update(
					"startActivity.updateProcessStartDate", startActivityBean);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
		return b;
	}

	public boolean updateProcessEndDate(StartActivityBean startActivityBean) {
		boolean b = false;
		try {
			int x = getSqlMapClientTemplate().update(
					"startActivity.updateProcessEndDate", startActivityBean);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
		return b;
	}

	public boolean updateActivityStartDate(StartActivityBean startActivityBean) {
		boolean b = false;
		try {
			int x = getSqlMapClientTemplate().update(
					"startActivity.updateActivityStartDate", startActivityBean);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
		return b;
	}

	public boolean updateActivityEndDate(StartActivityBean startActivityBean) {
		boolean b = false;
		try {
			int x = getSqlMapClientTemplate().update(
					"startActivity.updateActivityEndDate", startActivityBean);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
		return b;
	}

	public boolean updateProcessStatus(StartActivityBean startActivityBean) {
		boolean b = false;
		try {
			int x = getSqlMapClientTemplate().update(
					"startActivity.updateProcessStatus", startActivityBean);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
		return b;
	}

	public boolean updateActivityStatus(StartActivityBean startActivityBean) {
		boolean b = false;
		try {
			int x = getSqlMapClientTemplate().update(
					"startActivity.updateActivityStatus", startActivityBean);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
		return b;
	}

}
