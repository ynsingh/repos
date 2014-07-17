CREATE TABLE IF NOT EXISTS cheque_print (
  id int(11) NOT NULL AUTO_INCREMENT,
  entry_no int(11) NOT NULL,
  name VARCHAR (255) NOT NULL,
  bank_name VARCHAR (255) NOT NULL,
  cheque_no VARCHAR (25) NOT NULL,
  cheque_print_date datetime NOT NULL,
  cheque_bounce_date datetime NOT NULL,
  cheque_print_status int(1) NOT NULL DEFAULT 0,
  cheque_bounce_status int(1) NOT NULL DEFAULT 0,
  cheque_reprint_date datetime NOT NULL,
  No_of_bounce_cheque VARCHAR(15) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS cheque_bounce_record (
  id int(11) NOT NULL AUTO_INCREMENT,
  entry_no int(11) NOT NULL,
  name VARCHAR (255) NOT NULL,
  bank_name VARCHAR (255) NOT NULL,
  old_cheque_no VARCHAR (25) NOT NULL,
  new_cheque_no VARCHAR (25) NOT NULL,
  cheque_bounce_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
