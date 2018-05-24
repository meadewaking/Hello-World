/*create user city identified by "123456";    --�����û�����������
create user dragon identified by "123456";                   
create user snake identified by "123456";
create user frog identified by "123456";
create user cat identified by "123456";
drop user frog cascade;                       --ɾ���û�
alter user snake account lock;                --�����˻�
alter user snake account unlock;              --�ⶳ�˻�
create user frog identified by "123456"
alter user frog identified by "654321"
grant connect,resource,DBA to city;
revoke connect,resource,dba from city;*/

grant connect,resource to city        --�����û����Ӻͻ�����ɫ        
create user ss identified by "123456";
grant connect,resource to ss;
create user sos identified by "123456";
grant connect,resource to sos;
--grant create user to ss;
--revoke create user from ss;

create table emp(
id int,
no varchar2(50)
)

grant select on emp to ss with grant option     --�����û�����ĳ��Ȩ�޵�Ȩ��
grant all on emp to sos        --�����û�ȫ��Ȩ��
select * from emp;             --�鿴��
grant select on emp to city    --�����û��鿴���Ȩ��

create temporary tablespace us tempfile           --������ʱ��ռ�
'f:\s2\database\t1.dbf' size 9M��

alter database tempfile 'f:\s2\database\emp.dbf' resize 80M         --�����ռ��С
alter tablespace us add tempfile'f:\s2\database\emp2.dbf' size 10M  --����ԭ�б�ռ��С
drop tablespace us                                        --ɾ����ռ�
drop tablespace us including contents and datafiles       --ɾ����ռ���������е�����

insert into emp(id,no) values(1,1);
savepoint p1;                --�����浵��
insert into emp(id,no) values(2,2);
savepoint p2;
insert into emp(id,no) values(3,3);       --��������
savepoint p3;
rollback to p1               --�ع����浵��

delete from emp

create table clerk(
clerkid varchar2(50),
no varchar2(50),
achieve varchar2(50)
)
alter table emp add(                      --�����ֶ�
name varchar2(50)
--����
--�ֶ�n ���͡�Լ����
)
alter table emp drop column name                --ɾ��ĳ���е�ĳһ�ֶ�
drop table emp                                  --ɾ��ĳ��
drop table clerk
alter table emp add constraint pk_no primary key(no)      --Ϊĳһ�ֶμ�����
alter table clerk add constraint fk_no foreign key (no) references emp��no��    --Ϊĳһ�ű��ĳһ�ֶμ����
alter table clerk drop constraint fk_no        --ɾ��ĳ��Լ��
create table clerkclone as select * from clerk     --������Ǩ��
select * from clerkclone;
update emp set id = 52                   --�޸�ĳ�����ֶε�ֵ
select id from emp                       --�鿴ĳ���е�ĳһ�ֶ�

create table achievement(
id varchar2(50) primary key,
achieve1 varchar2(50),
achieve2 varchar2(50),
achieve3 varchar2(50)
)
alter table clerk add constraint fk_achieve foreign key (achieve) references achievement(id)--��ĳ����һ�ֶ���Ϊ���������һ�ű���������
--alter table achievement add constraint pk_id primary key(id)
alter table clerk drop constraint fk_achieve
alter table achievement drop constraint sys_c009413
insert into achievement(id,achieve1,achieve2��achieve3) values(1,'��','�޴�','�ǳ���');
select * from achievement;
select * from clerk
insert into clerk(clerkid,no,achieve) values(1,'��',1);
insert into clerk(clerkid,no,achieve) values(2,'��',1);
insert into clerk(clerkid,no,achieve) values(3,'��',1);
select * from clerk order by clerkid desc
update clerk set no = 52 where clerkid = 3
delete from clerk where clerkid = 3    --ɾ������ĳһ����¼

