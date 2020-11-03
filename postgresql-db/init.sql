CREATE DATABASE cofeappdb;
CREATE USER myuser with password 'mypass';
\c cofeappdb;
CREATE SCHEMA dbo;

drop table if exists dbo.user cascade;
CREATE TABLE dbo.user(
  id serial PRIMARY KEY,
  email varchar(255),
  name varchar(255),
  phone varchar(255),
  country varchar(255)
);

drop table if exists dbo.reward cascade;
CREATE TABLE dbo.reward(
  id serial PRIMARY KEY,
  name varchar(255),
  amount decimal,
  expiry_date timestamp
);

drop table if exists dbo.user_reward cascade;
CREATE TABLE dbo.user_reward(
  id serial PRIMARY KEY,
  user_id int not null,
  reward_id int not null,
  FOREIGN KEY (user_id) REFERENCES dbo.user (id),
  FOREIGN KEY (reward_id) REFERENCES dbo.reward (id)
);

GRANT USAGE ON SCHEMA dbo TO myuser;
GRANT ALL ON ALL TABLES IN SCHEMA dbo TO myuser;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA dbo TO myuser;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA dbo TO myuser;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO myuser;
