@echo
call mvn clean test surefire-report:report
echo After build
start target\surefire-reports\emailable-report.html
