FROM mysql:5.6

# Copy the database initialize script: 
# Contents of /docker-entrypoint-initdb.d are run on mysqld startup
ADD  docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

# Default values for passwords and database name. Can be overridden on docker run
# ENV MYSQL_ROOT_PASSWORD=my-secret-pw # Not defaulted for security reasons!
ENV MYSQL_DATABASE=spanners
ENV MYSQL_USER=spanners
ENV MYSQL_PASSWORD=password

CMD ["mysqld","--skip-performance-schema"]
