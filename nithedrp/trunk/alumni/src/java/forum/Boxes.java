package forum;

import java.sql.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Boxes {

    private int boxid, memberid;
    private String boxname, sortdesc;
    private String dbname, dbserver, dbuser, dbpasswd, dbport;
    private String path;
    public void setPath(String path1) {
        path = path1;
    }


    public String getPath() {
        return path;
    }
    public String getCurDir() {
        String userdir = System.getProperty("user.dir");
        return userdir;
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

    public String getBoxname() {
        return boxname;
    }

    public void setBoxname(String boxname) {
        this.boxname = boxname;
    }

    public String getSortdesc() {
        return sortdesc;
    }

    public void setSortdesc(String sortdesc) {
        this.sortdesc = sortdesc;
    }

    public List getBoxes() {
        List boxes = new LinkedList();
        String query = "SELECT box_id FROM Boxes";

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            while (rst.next()) {
                boxid = rst.getInt("box_id");
                boxes.add(new Integer(boxid));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boxes;
    }

    public List getBoxes(int memberid) {
        List boxes = new LinkedList();
        String query = "SELECT box_id FROM Boxes WHERE member_id=" + memberid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            while (rst.next()) {
                boxid = rst.getInt("box_id");
                boxes.add(new Integer(boxid));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boxes;
    }

    public boolean getBox(int boxid) {
        String query = "SELECT * FROM Boxes WHERE box_id=" + boxid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            while (rst.next()) {
                this.boxid = rst.getInt("box_id");
                memberid = rst.getInt("member_id");
                boxname = rst.getString("box_name");
                sortdesc = rst.getString("sort_desc");
            }

            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setBox() {
        boxid = (int) (1000 + Math.random() * 1000);
        String query = "INSERT INTO Boxes (box_id, member_id, box_name, sort_desc) "
                + "VALUES (" + boxid + ", "
                + +memberid + ", "
                + "'" + boxname + "', "
                + "'" + sortdesc + "')";
        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            stat.execute(query);
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setBox(int boxid) {
        String query = "UPDATE Boxes SET box_name='" + boxname + "', "
                + "sort_desc='" + sortdesc + "' "
                + "WHERE box_id=" + boxid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            stat.execute(query);
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delBox(int boxid) {
        String query1 = "DELETE FROM Boxes WHERE box_id=" + boxid;
        String query2 = "DELETE FROM Threads WHERE box_id=" + boxid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            stat.execute(query1);
            stat.execute(query2);
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isMod(int memberid) {
        String query = "SELECT box_id FROM Boxes WHERE member_id=" + memberid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);
            if (rst.next() == true) {
                con.close();
                return true;
            } else {
                con.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean assignBox(int boxid, int memberid) {
        String query = "UPDATE Boxes SET member_id=" + memberid + " "
                + "WHERE box_id=" + boxid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            stat.execute(query);
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unassignBox(int memberid) {
        String query = "UPDATE Boxes SET member_id=0 "
                + "WHERE member_id=" + memberid;

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            stat.execute(query);
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List getUnallocBoxes() {
        List boxes = new LinkedList();
        String query = "SELECT box_id FROM Boxes WHERE member_id=0";

        try {
            readconfig();
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?user=" + dbuser + "&password=" + dbpasswd);
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery(query);

            while (rst.next()) {
                boxid = rst.getInt("box_id");
                boxes.add(new Integer(boxid));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boxes;
    }
}
