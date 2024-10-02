create table HPPersonGeneric(
                                sysId BIGINT PRIMARY KEY,
                                personId VARCHAR,
                                sysVersion VARCHAR,
                                photoFkSysId VARCHAR,
                                sysExtension VARCHAR,
                                salutation VARCHAR,
                                familyName VARCHAR,
                                givenName VARCHAR,
                                middleName VARCHAR,
                                name VARCHAR,
                                nameTranslationSysId VARCHAR,
                                altFamilyName VARCHAR,
                                altGivenName VARCHAR,
                                altMiddleName VARCHAR,
                                birthDate VARCHAR,
                                gender VARCHAR,
                                maritalStatus VARCHAR,
                                primaryLanguage VARCHAR,
                                citizenship VARCHAR,
                                residence VARCHAR,
                                ethnicity VARCHAR,
                                religion VARCHAR,
                                sysTenant VARCHAR,
                                createdBy VARCHAR,
                                creationTime BIGINT,
                                sysChangeUser VARCHAR,
                                sysChangeTime BIGINT,
                                sysParentId BIGINT,
                                sysDateTo BIGINT,
                                sysDateFrom BIGINT

);

create table HPPersonDependant (
                                   sysId BIGINT,
                                   sysVersion VARCHAR,
                                   HPPersonGenericSysId BIGINT,
                                   HPRelatedPersonSysId BIGINT,
                                   attachmentFkSysId BIGINT,
                                   sysExtension VARCHAR,
                                   contactRelationship VARCHAR,
                                   id BIGINT,
                                   sysTenant VARCHAR,
                                   createdBy VARCHAR,
                                   creationTime BIGINT,
                                   sysChangeUser VARCHAR,
                                   sysChangeTime BIGINT,
                                   sysParentId BIGINT,
                                   sysDateTo BIGINT,
                                   sysDateFrom BIGINT
);

insert into hppersongeneric (sysId, personId, familyName, givenName, middleName, birthDate)
values (1,'test', 'Mishustin', 'Anton', 'Igorevich', '13-01-2003');

insert into hppersongeneric (sysId, personId, familyName, givenName, middleName,sysParentId, birthDate)
values (2, 'wife', 'Mishustina', 'Irina', 'Petrovna',1, '27-03-2004');

insert into hppersongeneric (sysId, personId, familyName, givenName, middleName,sysParentId, birthDate)
values (3, 'father', 'Mishustin', 'Igor', 'Vitalievich',1, '15-09-1982');

insert into hppersondependant (sysId, HPPersonGenericSysId, HPRelatedPersonSysId,contactRelationship)
values (2, 1, 3,'relatives');

insert into hppersondependant (sysId, HPPersonGenericSysId, HPRelatedPersonSysId,contactRelationship)
values (3, 1, 3,'relatives');

/*ввожу в таблицу нужные мне данные для запроса:
  personId,
  sysId,
  sysParentId, для выявления связи между работником и его женой.
  familyName,
  givenName,
  middleName,
  birthDate,
  HPPersonGenericSysId,
  HPRelatedPersonSysId,
  contactRelationship */

select rp. familyName,
       rp. givenName,
       rp. middleName,
       rp. birthDate,
       pd. contactRelationship
FROM HPPersonGeneric AS emp
         JOIN HPPersonDependant AS pd ON emp.sysId = pd.HPPersonGenericSysId
         JOIN HPPersonGeneric AS rp ON pd.HPRelatedPersonSysId = rp.sysId
WHERE emp.personId = 'test';