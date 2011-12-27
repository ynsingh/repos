== Requirements ==
 * Apache/Any other web server configured for serving PHP files
 * mySQL
 * Internet Connection (Google Maps API)
 * php5-pdo package (Ubuntu/Debian php5-mysql package)
 * php-mysql package
 * mysql-server package

== How To Install ==
 * Extract everything to your htdocs/www directory
 * Edit database details in includes/config.php
 * Edit SITE_DOMAIN in includes/config.php
 * Open the corresponding url in your browser
 * The database will automatically be built up
 * chmod `cache` folder to be writable by apache user (777)
	* Not necessary on Windows

== Known Issues ==
 * Does not work perfectly in Internet Explorer (Maps)
 * 
