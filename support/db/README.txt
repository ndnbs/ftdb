Home of any H2 DBs used

#####
# Can use the H2 "Shell" like this.
#####
C:\data\git-work\ftdb>java -cp build\libs\ftdb-all.jar org.h2.tools.Shell

Welcome to H2 Shell 1.4.196 (2017-06-10)
Exit with Ctrl+C
[Enter]   jdbc:h2:./support/db/ftdb
URL
[Enter]   org.h2.Driver
Driver
[Enter]   sa
User
[Enter]   Hide
Password
Password
Connected
Commands are case insensitive; SQL statements end with ';'
help or ?      Display this help
list           Toggle result list / stack trace mode
maxwidth       Set maximum column width (default is 100)
autocommit     Enable or disable autocommit
history        Show the last 20 statements
quit or exit   Close the connection and exit

sql> show columns from overall_status;
FIELD      | TYPE          | NULL | KEY | DEFAULT
ID         | INTEGER(10)   | NO   | PRI | NULL
L_DELTA_DT | TIMESTAMP(23) | NO   |     | NULL
PORT       | INTEGER(10)   | NO   |     | NULL
SYMB       | VARCHAR(255)  | NO   |     | NULL
EXCH       | VARCHAR(255)  | NO   |     | NULL
STAT       | VARCHAR(255)  | NO   |     | NULL
PVOM       | DECIMAL(20)   | NO   |     | NULL
O_DATE     | DATE(8)       | NO   |     | NULL
O_AMT      | DECIMAL(20)   | NO   |     | NULL
O_PRI      | DECIMAL(20)   | NO   |     | NULL
S_DATE     | DATE(8)       | NO   |     | NULL
S_AMT      | DECIMAL(20)   | NO   |     | NULL
S_PRI      | DECIMAL(20)   | NO   |     | NULL
L_PRI      | DECIMAL(20)   | NO   |     | NULL
L_PRI_DT   | TIMESTAMP(23) | NO   |     | NULL
L_PRI_MECH | VARCHAR(255)  | NO   |     | NULL
R0001_V    | VARCHAR(255)  | NO   |     | NULL
R0001_DT   | TIMESTAMP(23) | NO   |     | NULL
R0002_V    | VARCHAR(255)  | NO   |     | NULL
R0002_DT   | TIMESTAMP(23) | NO   |     | NULL
(20 rows, 88 ms)
sql>


#####
# Can use the H2 "Server" like this. Notice, with the all-inclusive JAR, the -cp switches work out nicely.
#####
C:\data\git-work\ftdb>java -cp build\libs\ftdb-all.jar org.h2.tools.Server -web -browser
Web Console server running at http://192.168.14.104:8082 (only local connections)

