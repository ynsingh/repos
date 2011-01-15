package in.ac.dei.edrp.client.Shared;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;


/**
 * @author Manpreet Kaur
 */

/**
 * This class creates calender to be shown on home page of the user
 */
@SuppressWarnings("deprecation")
public class CM_calender extends Composite implements ClickListener,
    SourcesChangeEvents {
    private final NavBar navbar = new NavBar(this);
    private final DockPanel outer = new DockPanel();

    // creates grid for calender
    private final Grid grid = new Grid(6, 7) {
            public boolean clearCell(int row, int column) {
                boolean retValue = super.clearCell(row, column);

                Element td = getCellFormatter().getElement(row, column);
                DOM.setInnerHTML(td, "");

                return retValue;
            }
        };

    private Date date = new Date();
    private ChangeListenerCollection changeListeners;

    // array of days in a week
    private String[] days = new String[] {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday"
        };

    // array of months in a year
    private String[] months = new String[] {
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"
        };

    //constructor of class for setting properties
    public CM_calender() {
        setWidget(outer);
        grid.setStyleName("table");
        grid.setCellSpacing(0);
        outer.add(navbar, DockPanel.NORTH);
        outer.add(grid, DockPanel.CENTER);
        drawCalendar();
        setStyleName("CalendarWidget");
    }

    /*
     * This method draws the calender
     */
    private void drawCalendar() {
        int year = getYear();
        int month = getMonth();
        @SuppressWarnings("unused")
        int day = getDay();
        setHeaderText(year, month);
        grid.getRowFormatter().setStyleName(0, "weekheader");

        // customizing grid for days in month
        for (int i = 0; i < days.length; i++) {
            grid.getCellFormatter().setStyleName(0, i, "days");
            grid.setText(0, i, days[i].substring(0, 3));
        }

        Date now = new Date();
        int sameDay = now.getDate(); // getting today's date
        int today = ((now.getMonth() == month) &&
            ((now.getYear() + 1900) == year)) ? sameDay : 0;

        int firstDay = new Date(year - 1900, month, 1).getDay();
        int numOfDays = getDaysInMonth(year, month);

        int j = 0;

        // setting the days in grid
        for (int i = 1; i < 6; i++) {
            for (int k = 0; k < 7; k++, j++) {
                int displayNum = (j - firstDay + 1);

                if ((j < firstDay) || (displayNum > numOfDays)) {
                    grid.getCellFormatter().setStyleName(i, k, "empty");
                    grid.setHTML(i, k, "&nbsp;");
                } else {
                    HTML html = new CellHTML("<span>" +
                            String.valueOf(displayNum) + "</span>", displayNum);
                    html.addClickListener(this);
                    grid.getCellFormatter().setStyleName(i, k, "cell");

                    if (displayNum == today) {
                        grid.getCellFormatter().addStyleName(i, k, "today");
                    } else if (displayNum == sameDay) {
                        grid.getCellFormatter().addStyleName(i, k, "day");
                    }

                    grid.setWidget(i, k, html);
                }
            }
        }
    }

    /**
     *  setting month and year on navigation bar
     * @param year
     * @param month
     */
    protected void setHeaderText(int year, int month) {
        navbar.title.setText(months[month] + ", " + year);
    }

    /**
     * Method for getting number of days in month
     * @param year
     * @param month
     * @return
     */
    private int getDaysInMonth(int year, int month) {
        switch (month) {
        case 1:

            if ((((year % 4) == 0) && ((year % 100) != 0)) ||
                    ((year % 400) == 0)) {
                return 29; // leap year
            } else {
                return 28;
            }

        case 3:
            return 30;

        case 5:
            return 30;

        case 8:
            return 30;

        case 10:
            return 30;

        default:
            return 31;
        }
    }

    /**
     * Method for obtaining previous month
     */
    public void prevMonth() {
        int month = getMonth() - 1;

        if (month < 0) {
            setDate(getYear() - 1, 11, getDay());
        } else {
            setMonth(month);
        }

        drawCalendar();
    }

    /**
     * Method for obtaining next month
     */
    public void nextMonth() {
        int month = getMonth() + 1;

        if (month > 11) {
            setDate(getYear() + 1, 0, getDay());
        } else {
            setMonth(month);
        }

        drawCalendar();
    }

    /**
     * Method for getting previous year
     */
    public void prevYear() {
        setYear(getYear() - 1);
        drawCalendar();
    }

    /**
     * Method for getting next month
     */
    public void nextYear() {
        setYear(getYear() + 1);
        drawCalendar();
    }

    /**
     * Method for setting the date
     * @param year
     * @param month
     * @param day
     */
    private void setDate(int year, int month, int day) {
        date = new Date(year - 1900, month, day);
    }

    /**
     * Method for setting year
     * @param year
     */
    private void setYear(int year) {
        date.setYear(year - 1900);
    }

    /**
     * Method for setting month
     * @param month
     */
    private void setMonth(int month) {
        date.setMonth(month);
    }

    /**
     * Method for getting year
     * @return
     */
    public int getYear() {
        return 1900 + date.getYear();
    }

    /**
     * Method for getting month
     * @return
     */
    public int getMonth() {
        return date.getMonth();
    }

    /**
     * Method for getting day
     * @return
     */
    public int getDay() {
        return date.getDate();
    }

    /**
     * Method for getting date
     * @return
     */
    public Date getDate() {
        return date;
    }

    /*
     * Adding click handler on calender
     */
    public void onClick(Widget sender) {
        CellHTML cell = (CellHTML) sender;
        setDate(getYear(), getMonth(), cell.getDay());
        drawCalendar();

        if (changeListeners != null) {
            changeListeners.fireChange(this);
        }
    }

    /*
     * Adding change listner
     */
    public void addChangeListener(ChangeListener listener) {
        if (changeListeners == null) {
            changeListeners = new ChangeListenerCollection();
        }

        changeListeners.add(listener);
    }

    /*
     * Removing change listner
     */
    public void removeChangeListener(ChangeListener listener) {
        if (changeListeners != null) {
            changeListeners.remove(listener);
        }
    }

    /*
     * This class creates navigation bar of the calender
     */
    private class NavBar extends Composite implements ClickListener {
        public final DockPanel bar = new DockPanel();
        public final Button prevMonth = new Button("&lt;", this);
        public final Button prevYear = new Button("&lt;&lt;", this);
        public final Button nextYear = new Button("&gt;&gt;", this);
        public final Button nextMonth = new Button("&gt;", this);
        public final HTML title = new HTML();
        private final CM_calender calendar;

        public NavBar(CM_calender calendar) {
            this.calendar = calendar;

            setWidget(bar);
            bar.setStyleName("navbar");
            title.setStyleName("header");

            HorizontalPanel prevButtons = new HorizontalPanel();
            prevButtons.add(prevMonth);
            prevButtons.add(prevYear);

            HorizontalPanel nextButtons = new HorizontalPanel();
            nextButtons.add(nextYear);
            nextButtons.add(nextMonth);

            // adding and setting panels on decopanel
            bar.add(prevButtons, DockPanel.WEST);
            bar.setCellHorizontalAlignment(prevButtons, DockPanel.ALIGN_LEFT);
            bar.add(nextButtons, DockPanel.EAST);
            bar.setCellHorizontalAlignment(nextButtons, DockPanel.ALIGN_RIGHT);
            bar.add(title, DockPanel.CENTER);
            bar.setVerticalAlignment(DockPanel.ALIGN_MIDDLE);
            bar.setCellHorizontalAlignment(title, HasAlignment.ALIGN_CENTER);
            bar.setCellVerticalAlignment(title, HasAlignment.ALIGN_MIDDLE);
            bar.setCellWidth(title, "100%");
        }

        /*
         *  adiing click handler to navigators
         */
        public void onClick(Widget sender) {
            if (sender == prevMonth) {
                calendar.prevMonth();
            } else if (sender == prevYear) {
                calendar.prevYear();
            } else if (sender == nextYear) {
                calendar.nextYear();
            } else if (sender == nextMonth) {
                calendar.nextMonth();
            }
        }
    }

    private static class CellHTML extends HTML {
        private int day;

        public CellHTML(String text, int day) {
            super(text);
            this.day = day;
        }

        /*
         * returning selected day
         */
        public int getDay() {
            return day;
        }
    }
}
