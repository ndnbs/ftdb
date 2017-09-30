REM ###########################
REM invoke-it.bat
REM ###########################

java -jar build\libs\ftdb-all.jar create

java -jar build\libs\ftdb-all.jar populate 

java -jar build\libs\ftdb-all.jar dump


