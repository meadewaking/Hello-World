/*create user city identified by "123456";    --创建用户并赋予密码
create user dragon identified by "123456";                   
create user snake identified by "123456";
create user frog identified by "123456";
create user cat identified by "123456";
drop user frog cascade;                       --删除用户
alter user snake account lock;                --冻结账户
alter user snake account unlock;              --解冻账户
create user frog identified by "123456"
alter user frog identified by "654321"
grant connect,resource,DBA to city;
revoke connect,resource,dba from city;*/

grant connect,resource to city        --赋予用户连接和基本角色        
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

grant select on emp to ss with grant option     --赋予用户传递某项权限的权限
grant all on emp to sos        --赋予用户全部权限
select * from emp;             --查看表
grant select on emp to city    --赋予用户查看表的权限

create temporary tablespace us tempfile           --设立临时表空间
'f:\s2\database\t1.dbf' size 9M，

alter database tempfile 'f:\s2\database\emp.dbf' resize 80M         --重设表空间大小
alter tablespace us add tempfile'f:\s2\database\emp2.dbf' size 10M  --增加原有表空间大小
drop tablespace us                                        --删除表空间
drop tablespace us including contents and datafiles       --删除表空间和其中所有的内容

insert into emp(id,no) values(1,1);
savepoint p1;                --设立存档点
insert into emp(id,no) values(2,2);
savepoint p2;
insert into emp(id,no) values(3,3);       --插入数据
savepoint p3;
rollback to p1               --回滚到存档点

delete from emp

create table clerk(
clerkid varchar2(50),
no varchar2(50),
achieve varchar2(50)
)
alter table emp add(                      --增加字段
name varchar2(50)
--……
--字段n 类型【约束】
)
alter table emp drop column name                --删除某表中的某一字段
drop table emp                                  --删除某表
drop table clerk
alter table emp add constraint pk_no primary key(no)      --为某一字段加主键
alter table clerk add constraint fk_no foreign key (no) references emp（no）    --为某一张表的某一字段加外键
alter table clerk drop constraint fk_no        --删除某种约束
create table clerkclone as select * from clerk     --表数据迁移
select * from clerkclone;
update emp set id = 52                   --修改某表中字段的值
select id from emp                       --查看某表中的某一字段

create table achievement(
id varchar2(50) primary key,
achieve1 varchar2(50),
achieve2 varchar2(50),
achieve3 varchar2(50)
)
alter table clerk add constraint fk_achieve foreign key (achieve) references achievement(id)--将某表中一字段作为外键并在另一张表中做主键
--alter table achievement add constraint pk_id primary key(id)
alter table clerk drop constraint fk_achieve
alter table achievement drop constraint sys_c009413
insert into achievement(id,achieve1,achieve2，achieve3) values(1,'大','巨大','非常大');
select * from achievement;
select * from clerk
insert into clerk(clerkid,no,achieve) values(1,'大',1);
insert into clerk(clerkid,no,achieve) values(2,'大',1);
insert into clerk(clerkid,no,achieve) values(3,'大',1);
select * from clerk order by clerkid desc
update clerk set no = 52 where clerkid = 3
delete from clerk where clerkid = 3    --删除表中某一条记录

