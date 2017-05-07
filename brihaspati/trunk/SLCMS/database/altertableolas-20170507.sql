 use olas;

CREATE TABLE IF NOT EXISTS logs (
  id int(11) NOT NULL AUTO_INCREMENT,
  date datetime NOT NULL,
  level int(1) NOT NULL,
  host_ip varchar(25) NOT NULL,
  user varchar(25) NOT NULL,
  url varchar(255) NOT NULL,
  user_agent varchar(100) NOT NULL,
  message_title varchar(255) NOT NULL,
  message_desc mediumtext NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
