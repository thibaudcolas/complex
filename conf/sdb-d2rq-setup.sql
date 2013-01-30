-- SDB and D2RQ PostgreSQL setup script.
-- Future : https://github.com/castagna/bsbm-automated/blob/master/sdb.sh

-- Setup sdb user for sdb databases.
CREATE USER sdb WITH PASSWORD 'password';

CREATE DATABASE sdb_inseepop OWNER sdb;
CREATE DATABASE sdb_inseecog OWNER sdb;
CREATE DATABASE sdb_monuments OWNER sdb;
CREATE DATABASE sdb_passim OWNER sdb;
CREATE DATABASE sdb_isf OWNER sdb;
CREATE DATABASE sdb_geonames OWNER sdb;

-- Setup d2rq user for d2rq database.
CREATE USER d2rq WITH PASSWORD 'password';

CREATE DATABASE d2rq OWNER d2rq;
