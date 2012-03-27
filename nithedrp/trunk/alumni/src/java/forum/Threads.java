package forum;

import java.sql.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Threads {

    private int threadid, parentid, boxid, memberid;
    private long postdate;
    private String subject, posttext;
    private final int LAST_POSTS = 5;
    private String dbname, dbserver, dbuser, dbpasswd, dbport;
   
    private String path;
    public void setPath(String path1) {
        path = path1;
    }

    public String getPath() {
        return path;
    }

    public void readconfig() throws IOException {
        Properties prop = new Properties();
        InputStream in = new FileInputStream(getPath());
        prop.load(in);
        dbname = prop.getProperty("dbname");
        dbserver = prop.getProperty("dbserver");
        dbuser = prop.getProperty("dbuser");
        dbpasswd = prop.getProperty("dbpassword");
        dbport = prop.getProperty("dbport");


        if (in != null) {
            in.close();
        }
    }

    public int getThreadid() {
        return threadid;
    }

    public void setThreadid(int threadid) {
        this.threadid = threadid;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getBoxid() {
        return boxid;
    }

    public void setBoxid(int boxid) {
        this.boxid = boxid;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPosttext() {
        return posttext;
    }

    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }

    public long getPostdate() {
        return postdate;
    }

    public void setPostdate(long postdate) {
        this.postdate = postdate;
    }

    public int getNumThreads(int boxid) {
        int numThreads = 0;
        String query = "SELECT thread_id FROM Threads WHERE box_id=" + boxid + " AND parent_id=0 ORDER BY post_date DESC";

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            while (rst.next()) {
                numThreads++;
            }
            con.close();
        } catch (Exception e) {
        }
        return numThreads;
    }

    public int getNumMsgs(int boxid) {
        int numMsgs = 0;
        String query = "SELECT thread_id FROM Threads WHERE box_id=" + boxid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            while (rst.next()) {
                numMsgs++;
            }
            con.close();
        } catch (Exception e) {
        }
        return numMsgs;
    }

    public List getThreads(int boxid) {
        List threads = new LinkedList();
        String query = "SELECT thread_id FROM Threads WHERE box_id=" + boxid + " AND parent_id=0 ORDER BY post_date DESC";

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            while (rst.next()) {
                threadid = rst.getInt("thread_id");
                threads.add(new Integer(threadid));
            }

            con.close();
        } catch (Exception e) {
        }
        return threads;
    }

    public List getReplies(int parentid) {
        List threads = new LinkedList();
        String query = "SELECT thread_id FROM Threads WHERE parent_id=" + parentid + " ORDER BY post_date";

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            while (rst.next()) {
                threadid = rst.getInt("thread_id");
                threads.add(new Integer(threadid));
            }

            con.close();
        } catch (Exception e) {
        }
        return threads;
    }

    public List getLastThreads(int boxid) {
        List threads = new LinkedList();
        int count = 0;
        String query = "SELECT thread_id FROM Threads WHERE box_id=" + boxid + " AND parent_id=0 ORDER BY post_date DESC";

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            if (getNumThreads(boxid) <= 5) {
                while (rst.next()) {
                    threadid = rst.getInt("thread_id");
                    threads.add(new Integer(threadid));
                }
            } else {
                while (rst.next() && (count < LAST_POSTS)) {
                    count++;
                    threadid = rst.getInt("thread_id");
                    threads.add(new Integer(threadid));
                }
            }
            con.close();
        } catch (Exception e) {
        }
        return threads;
    }

    public boolean getThread(int threadid) {
        String query = "SELECT * FROM Threads WHERE thread_id=" + threadid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            while (rst.next()) {
                this.threadid = rst.getInt("thread_id");
                parentid = rst.getInt("parent_id");
                boxid = rst.getInt("box_id");
                memberid = rst.getInt("member_id");
                subject = rst.getString("subject");
                posttext = rst.getString("post_text");
                postdate = rst.getLong("post_date");
            }

            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean postThread() {
        threadid = (int) (1000 + Math.random() * 1000);
        parentid = 0;
        postdate = new java.util.Date().getTime();
        String query = "INSERT INTO Threads (thread_id, parent_id, box_id, member_id, subject, post_text, post_date) "
                + "VALUES (" + threadid + ", "
                + parentid + ", "
                + boxid + ", "
                + memberid + ", "
                + "'" + subject + "', "
                + "'" + posttext + "', "
                + postdate + ")";
        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            stat.execute(query);
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean replyThread() {
        postdate = new java.util.Date().getTime();
        String query = "INSERT INTO Threads (thread_id, parent_id, box_id, member_id, subject, post_text, post_date) "
                + "VALUES (" + threadid + ", "
                + parentid + ", "
                + boxid + ", "
                + memberid + ", "
                + "'" + subject + "', "
                + "'" + posttext + "', "
                + postdate + ")";
        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            stat.execute(query);
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setThread(int threadid) {
        postdate = new java.util.Date().getTime();
        String query = "UPDATE Threads SET subject='" + subject + "', "
                + "post_text='" + posttext + "', "
                + "post_date='" + postdate + "' "
                + "WHERE thread_id=" + threadid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            stat.execute(query);
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delThread(int threadid) {
        String parent_id = "";
        getThread(threadid);

        if (parentid == 0) {
            parent_id = " OR parent_id=" + threadid;
        }

        String query = "DELETE FROM Threads WHERE thread_id=" + threadid + parent_id;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            stat.execute(query);
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
