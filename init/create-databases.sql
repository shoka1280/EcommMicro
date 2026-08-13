-- Runs automatically on first initialization of the Postgres data volume
-- (mounted into /docker-entrypoint-initdb.d/). Flyway/Hibernate create the
-- TABLES inside each of these databases, but the databases themselves must
-- exist first — that is what this script does.
--
-- Postgres has no "CREATE DATABASE IF NOT EXISTS", so we guard each one.

SELECT 'CREATE DATABASE "order"'   WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'order')\gexec
SELECT 'CREATE DATABASE payment'   WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'payment')\gexec
SELECT 'CREATE DATABASE product'   WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'product')\gexec
