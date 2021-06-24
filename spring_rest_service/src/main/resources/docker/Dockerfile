FROM postgres:13
ENV POSTGRES_USER user
ENV POSTGRES_PASSWORD pass
ENV POSTGRES_DB rest_serv
ADD CreateDB.sql /docker-entrypoint-initdb.d/
#ADD CreateDBuser.sql /docker-entrypoint-initdb.d/
#ADD CreateDBproj.sql /docker-entrypoint-initdb.d/
