Installation instruction:

1. Install JDK 6
	1. Double click on the file "jdk-6u18-windows-i586.exe" to start the installer
	2. Follow the instruction from the installer to install the JDK and JRE
	3. verify the installation by open a command prompt and type "java -version" 
		you should see the following:
		C:\>java -version
		java version "1.6.0_18"
2. Install Tomcat 5
	1. Double click on the file "apache-tomcat-5.5.23.exe" to start the installer
	2. Click "Next"
	3. Click "I Agree"
	4. Click "Next"
	5. Click "Next"
	6. Change the "HTTP/1.1 Connector Port" to "8090" and set the "Password" to "adm1n" and click "Next"
	7. Click "Install"	
	8. Click "Finish"
	9. After install you should see Tomcat starts and there is an icon on the right corner
3. Install MySQL 5.1
	1. Double click the file "mysql-essential-5.1.22-rc-win32.msi" to start the installer
	2. Click "Next"
	3. Select "Typical" and click "Next"
	4. Click "Install"
	5. Click "Next"
	6. Click "Next"
	7. Click "Finish"
	8. Click "Next"
	9. Select "Detailed Configuration" and click "Next"
	10. Select "Server Machine" and click "Next"
	11. Select "Multifunctional Database" and click "Next"
	12. Click "Next"
	13. Select "Decision Support (DSS)/OLAP" and click "Next"
	14. Click "Next"
	15. Click "Next"
	16. Select the check box "Include Bin Directory in Windows PATH" and click "Next"
	17. Enter "st3v3" for root password, check the box "Enable root access from remote machines" and click "Next"
	18. Click the "Execute" button
	19. Click "Finish"
4. Install MySQL Admin Tools
	1. Double click the file "mysql-gui-tools-5.0-r12-win32.msi" to start the installer
	2. Follow the instruction from the installer
5. Setup MySQL
	5.1 Create Remote User "steve"
		1. Open the MySQL Administrator by click Start->All Programs->MySQL->MySQL Administrator
		2. Enter "localhost" as ServerHost and "root" as the Username and "st3v3" as the Password and click "OK"
		3. Select "User Administration" 
		4. Click the "Add new user" button
		5. Enter "steve" for MySQL User, "st3v3" for password
		6. Click "Apply Changes" button
		7. Right click the user "steve" in the left panel and select "Add host from which the user can connect"
		8. Enter "*" for Host and click "OK"
		9. Click the "Apply Changes" button
	5.2 Create schema "Gold"
		10. Select "Catalogs" icon
		11. Point your mouse to the lower Left panel and right mouse click and select "Create New Schema"
		12. Enter "Gold" for "Schema Name:" and click "OK"
		13. Select the user "steve" in the lower left panel
		14. Select the "Schema Privileges" tab
		15. Select "gold" in the Schemata column
		16. Select the "<<" to Assign all privileges for Gold to user "steve"
		17. Click "Apply Changes" button
6. Setup Tomcat
	6.1 Setup connecton pool and SSL
		1. Stop Tomcat by right click on the Apache Tomcat icon on the lower right corner 
			and select "Stop Service"
		2. Create a certificate keystore by executing the following command: 
			"C:\Program Files\Java\jdk1.5.0_15\bin\keytool -genkey -alias tomcat -keyalg RSA"
			When prompted for key password, enter "changeit" and hit enter
			Enter "SuntekSystems Engineer" for first name and last name
			Enter "SuntekSystems" for organization unit
			Enter "SuntekSystems" for organization
			Enter "Fremont" for city
			Enter "CA" for state
			Enter "US" for country
			Enter yes
			Hit Enter to accept default			
		2. Copy the file server.xml to the "C:\Program Files\Apache Software Foundation\Tomcat 5.5\conf\" directory
		3. Copy the file context.xml to the "C:\Program Files\Apache Software Foundation\Tomcat 5.5\conf\" directory
		4. Copy mysql-connector-java-5.1.6-bin.jar to "C:\Program Files\Apache Software Foundation\Tomcat 5.5\common\lib" directory
7. Install BHR
	7.1 Install schema 
	    From backedup dump file:
			1. Open MySQL Administrator
			2. Select "Restore" icon
			3. Click "Open Backup File" 
			4. Browser to the bhr database dump file and click "Open"
			5. Select "Another schema" and select "gold"
			6. Click "Start Restore" button		
        (OR) Install brand new schema
			1. Open MySQL Query Browser
			2. Enter gold in the "Default Schema" 
			3. Select File->Open Script and select "schema.sql" 
			4. Click the Execute button and all the tables will be created. 
			5. Select File->Open Script and select "factoryData.sql" 
			6. Click the Execute button and it will create the default user "Administrator" with password "Administrator"
	7.2 Install BHR and Scheduler
		1. Copy the file "scheduler.war" and "bhr.war" to the 
			"C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps"
		2. Start Tomcat by right click on the Apache Tomcat icon on the lower right corner 
			and select "Start Service"
8. Test the Application
	8.1 Test Scheduler
		1. Open Internet Explorer and goto http://localhost:8090/scheduler/
		2. Click on the "Scheduler" icon
		3. On the Login Dialog enter the username and password to login (Administrator/Administrator)
	8.2 Test BHR
		1. Open Internet Explorer and goto http://localhost:8090/scheduler/
		2. Click on the "BHR" icon
		3. Enter the username and password to login the BHR	(Administrator/Administrator)