/*
 * @(#) EntityMasterController.java
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
package in.ac.dei.edrp.cms.controller.entity;

import in.ac.dei.edrp.cms.dao.entity.EntityMasterDao;
import in.ac.dei.edrp.cms.domain.entity.EntityInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Controller for processing of entity Master
 * @author Manpreet Kaur
 * @date 10-02-2011
 * @version 1.0
 */
public class EntityMasterController extends MultiActionController {
    private EntityMasterDao entityMasterDao;

    /**
     * Method to initialize controller
     * @param entityMasterDao object of EntityMasterDao interface
     */
    public void setEntityMasterDao(EntityMasterDao entityMasterDao) {
        this.entityMasterDao = entityMasterDao;
    }

    /**
     *  Method for populating list of entity types
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodEntityList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }       
        List<EntityInfoGetter> entityList = entityMasterDao.methodEntityList(session.getAttribute("userId").toString());

        return new ModelAndView("entity/EntityTypeList", "entityList",
            entityList);
    }

    /**
     * Method for getting list of parent entities
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodParentEntityList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }       
    	String level = request.getParameter("level");
        List<EntityInfoGetter> parentList = entityMasterDao.methodParentEntityList(session.getAttribute("userId").toString(),
                level);

        return new ModelAndView("entity/ParentList", "parentList", parentList);
    }

    /**
     * Method to add new entity details in database
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodAddEntity(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	EntityInfoGetter entityInfo = new EntityInfoGetter();
        entityInfo.setEntityType(request.getParameter("entityType").trim());
        entityInfo.setEntityCity(request.getParameter("city").trim());
        entityInfo.setEntityState(request.getParameter("state").trim());
        entityInfo.setEntityPhone(request.getParameter("phone"));
        entityInfo.setFax(request.getParameter("fax"));
        entityInfo.setKnownBy(request.getParameter("knownby").trim());
        entityInfo.setParentEntityId(request.getParameter("parentId").trim());
        entityInfo.setLevel(request.getParameter("level").trim());
        entityInfo.setEntityCode(request.getParameter("entityCode"));
        entityInfo.setPinCode(request.getParameter("pincode"));
        entityInfo.setEntityAddress(request.getParameter("address").trim());
        entityInfo.setLocationId(request.getParameter("location").trim());
        entityInfo.setEntityName(request.getParameter("name").trim());

        String entityResult = entityMasterDao.methodAddEntity(session.getAttribute("userId").toString(), entityInfo);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            entityResult);
    }

    /**
     * Method for populating list of entities for manage purpose based on given criteria
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodPopulateEntity(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	String entityType = request.getParameter("entityType");
        List<EntityInfoGetter> entityList = entityMasterDao.methodPopulateEntity(session.getAttribute("userId").toString(),
                entityType);

        return new ModelAndView("entity/EntityList", "entityList", entityList);
    }

    /**
     * Method to update entity information
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodUpdateEntity(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	EntityInfoGetter entityInfo = new EntityInfoGetter();
        entityInfo.setEntityType(request.getParameter("entityType").trim());
        entityInfo.setEntityCity(request.getParameter("city").trim());
        entityInfo.setEntityState(request.getParameter("state").trim());
        entityInfo.setEntityPhone(request.getParameter("phone"));
        entityInfo.setFax(request.getParameter("fax"));
        entityInfo.setKnownBy(request.getParameter("knownby").trim());
        entityInfo.setParentEntityId(request.getParameter("parentId").trim());
        entityInfo.setLevel(request.getParameter("level").trim());
        entityInfo.setEntityCode(request.getParameter("entityCode"));
        entityInfo.setPinCode(request.getParameter("pincode"));
        entityInfo.setEntityAddress(request.getParameter("address").trim());
        entityInfo.setLocationId(request.getParameter("location").trim());
        entityInfo.setEntityName(request.getParameter("name").trim());
        entityInfo.setEntityId(request.getParameter("entityId"));

        String message=entityMasterDao.methodUpdateEntity(session.getAttribute("userId").toString(), entityInfo);

        return new ModelAndView("RegistrationForm/RegisterStudent",
            "result", message);
    }

    /**
     * Method for deletion of entity
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodDeleteEntity(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        String entityId = request.getParameter("entityId");
       String message= entityMasterDao.methodDeleteEntity(entityId);

        return new ModelAndView("RegistrationForm/RegisterStudent",
            "result", message);
    }
/**
 * Method to get location list
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
    public ModelAndView methodLocationList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        List<EntityInfoGetter> locationList = entityMasterDao.methodLocationList(session.getAttribute("userId").toString());

        return new ModelAndView("entity/ParentList", "parentList", locationList);
    }
/**
 * Method to get list of child entities of a parent entity    
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
    public ModelAndView methodChildEntityList(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	
            String entityId = request.getParameter("parentId");

            List<EntityInfoGetter> childList = entityMasterDao.getChildEntityList(entityId);

            return new ModelAndView("entity/ParentList", "parentList", childList);
        }
}
