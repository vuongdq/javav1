docker pull mysql



-d:detach(background mode)
-e: environment variables;



docker run \
-e "ACCEPT_EULA=Y" \
-e "SA_PASSWORD=Abc@123456789" \
--name sql-server-2019-container \
-p 1435:1433 \
-d mcr.microsoft.com/mssql/server:2019-CU16-GDR1-ubuntu-20.04



docker run -d \
--name mysql-container \
--network todo-app-network \
--network-alias todo-app-network-alias \
-v todo-mysql-database /var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=Abc@123456789 \
-e MYSQL_DATABASE=todoDB \
mysql


