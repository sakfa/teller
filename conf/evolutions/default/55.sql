# --- !Ups

create table EVALUATION (
  ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  EVENT_ID BIGINT NOT NULL,
  PARTICIPANT_ID BIGINT NULL,
  QUESTION_1 TEXT NOT NULL,
  QUESTION_2 TEXT NOT NULL,
  QUESTION_3 TEXT NOT NULL,
  QUESTION_4 TEXT NOT NULL,
  QUESTION_5 TEXT NOT NULL,
  QUESTION_6 TINYINT NOT NULL,
  QUESTION_7 TINYINT NOT NULL,
  QUESTION_8 TEXT NOT NULL,
  STATUS ENUM('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
  HANDLED TIMESTAMP,
  CERTIFICATE VARCHAR(254),
  CREATED TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CREATED_BY VARCHAR(254) NOT NULL DEFAULT 'Sergey Kotlov',
  UPDATED TIMESTAMP NOT NULL,
  UPDATED_BY VARCHAR(254) NOT NULL DEFAULT 'Sergey Kotlov'
);

alter table EVALUATION add constraint EVALUATION_FK foreign key(EVENT_ID) references EVENT(ID) on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table EVALUATION drop foreign key EVALUATION_FK;
drop table EVALUATION;
