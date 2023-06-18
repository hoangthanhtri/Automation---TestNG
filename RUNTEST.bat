@echo
call mvn clean test -DthreadCount="2" surefire-report:report
echo After build
start target\surefire-reports\emailable-report.html
