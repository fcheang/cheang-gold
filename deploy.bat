echo remove war
rm "C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\bhr.war"
rm -rf "C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\bhr"
rm -rf "C:\Program Files\Apache Software Foundation\Tomcat 5.5\work\Catalina"
echo copy war
cp bhr.war "C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps"
echo done
