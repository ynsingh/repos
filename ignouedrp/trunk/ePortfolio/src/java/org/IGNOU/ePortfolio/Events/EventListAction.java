package org.IGNOU.ePortfolio.Events;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.DAO.EventsDao;
import org.IGNOU.ePortfolio.Model.Events;

/**
 *
 * @author IGNOU Team
 */
public class EventListAction extends ActionSupport {

    private Events eventslist = new Events();
    private EventsDao evDao = new EventsDao();
    private List<Events> eventList;
    private Long eventsId;
    private String eventTitle;
    private Date eventDateFrom, eventDateTo, createDate, eventDisplayDate;
    private String venue, address, city, state, country;
    private Integer pincode;
    private Long phone;
    private String emailId, website, description;
    private Boolean postponed;
    private String postponedReason;
    private String msg;
    private String EventType;
    private Date Today;
    private ArrayList<Events> ArcheventList = new ArrayList<Events>();
    private String recordNotFound = getText("recordNotFound");
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public String AllEventsList() {
        eventList = evDao.AllEventsList();
        return SUCCESS;
    }

    public String ShowEventInfo() {
        eventList = evDao.EventsList();
        if (eventList.isEmpty()) {
            msg = " ";
        }
        EventType = "Upcoming Events";
        return SUCCESS;
    }

    public List ShowEventLists() {
        eventList = evDao.EventsAnnounceList();
        return getEventList();
    }

    public String ShowPostponedEventLists() {
        eventList = evDao.PostponedEventsList();
        EventType = "Postponed Events";
        if (eventList.isEmpty()) {
            msg = recordNotFound;
        }
        return SUCCESS;
    }

    public String ShowArchivedEventLists() {
        eventList = evDao.ArchivedEventsList();
        if (eventList.isEmpty()) {
            msg = recordNotFound;
        }
        EventType = "Archived Events";
        return SUCCESS;
    }

    public String EditEventsInfo() {
        eventList = evDao.EditEvent(eventsId);
        return SUCCESS;
    }

    public String UpdateEventInfo() throws Exception {
        evDao.UpdateEvents(eventsId, eventTitle, eventDateFrom, eventDateTo, createDate, eventDisplayDate, venue, address, city, state, country, pincode, phone, emailId, website, description, postponed, postponedReason);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteEventInfo() throws Exception {
        evDao.DeleteEvents(eventsId);
        msg = infoDeleted;
        return SUCCESS;
    }

    /**
     * @return the eventslist
     */
    public Events getEventslist() {
        return eventslist;
    }

    /**
     * @param eventslist the eventslist to set
     */
    public void setEventslist(Events eventslist) {
        this.eventslist = eventslist;
    }

    /**
     * @return the evDao
     */
    public EventsDao getEvDao() {
        return evDao;
    }

    /**
     * @param evDao the evDao to set
     */
    public void setEvDao(EventsDao evDao) {
        this.evDao = evDao;
    }

    /**
     * @return the eventList
     */
    public List<Events> getEventList() {
        return eventList;
    }

    /**
     * @param eventList the eventList to set
     */
    public void setEventList(List<Events> eventList) {
        this.eventList = eventList;
    }

    /**
     * @return the eventsId
     */
    public Long getEventsId() {
        return eventsId;
    }

    /**
     * @param eventsId the eventsId to set
     */
    public void setEventsId(Long eventsId) {
        this.eventsId = eventsId;
    }

    /**
     * @return the eventTitle
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * @param eventTitle the eventTitle to set
     */
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    /**
     * @return the eventDateFrom
     */
    public Date getEventDateFrom() {
        return eventDateFrom;
    }

    /**
     * @param eventDateFrom the eventDateFrom to set
     */
    public void setEventDateFrom(Date eventDateFrom) {
        this.eventDateFrom = eventDateFrom;
    }

    /**
     * @return the eventDateTo
     */
    public Date getEventDateTo() {
        return eventDateTo;
    }

    /**
     * @param eventDateTo the eventDateTo to set
     */
    public void setEventDateTo(Date eventDateTo) {
        this.eventDateTo = eventDateTo;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the eventDisplayDate
     */
    public Date getEventDisplayDate() {
        return eventDisplayDate;
    }

    /**
     * @param eventDisplayDate the eventDisplayDate to set
     */
    public void setEventDisplayDate(Date eventDisplayDate) {
        this.eventDisplayDate = eventDisplayDate;
    }

    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the pincode
     */
    public Integer getPincode() {
        return pincode;
    }

    /**
     * @param pincode the pincode to set
     */
    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    /**
     * @return the phone
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the postponed
     */
    public Boolean getPostponed() {
        return postponed;
    }

    /**
     * @param postponed the postponed to set
     */
    public void setPostponed(Boolean postponed) {
        this.postponed = postponed;
    }

    /**
     * @return the postponedReason
     */
    public String getPostponedReason() {
        return postponedReason;
    }

    /**
     * @param postponedReason the postponedReason to set
     */
    public void setPostponedReason(String postponedReason) {
        this.postponedReason = postponedReason;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the EventType
     */
    public String getEventType() {
        return EventType;
    }

    /**
     * @param EventType the EventType to set
     */
    public void setEventType(String EventType) {
        this.EventType = EventType;
    }

    /**
     * @return the Today
     */
    public Date getToday() {
        return Today;
    }

    /**
     * @param Today the Today to set
     */
    public void setToday(Date Today) {
        this.Today = Today;
    }

    /**
     * @return the ArcheventList
     */
    public ArrayList<Events> getArcheventList() {
        return ArcheventList;
    }

    /**
     * @param ArcheventList the ArcheventList to set
     */
    public void setArcheventList(ArrayList<Events> ArcheventList) {
        this.ArcheventList = ArcheventList;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the recordNotFound
     */
    public String getRecordNotFound() {
        return recordNotFound;
    }

    /**
     * @param recordNotFound the recordNotFound to set
     */
    public void setRecordNotFound(String recordNotFound) {
        this.recordNotFound = recordNotFound;
    }

    /**
     * @return the infoDeleted
     */
    public String getInfoDeleted() {
        return infoDeleted;
    }

    /**
     * @param infoDeleted the infoDeleted to set
     */
    public void setInfoDeleted(String infoDeleted) {
        this.infoDeleted = infoDeleted;
    }

    /**
     * @return the infoUpdated
     */
    public String getInfoUpdated() {
        return infoUpdated;
    }

    /**
     * @param infoUpdated the infoUpdated to set
     */
    public void setInfoUpdated(String infoUpdated) {
        this.infoUpdated = infoUpdated;
    }
}
