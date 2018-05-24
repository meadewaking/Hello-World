--create user city identified by "123456"
--create user drajon identified by "123456"                   
--create user snake identified by "123456"
--create user frouj identified by "123456"
--grant connect,resource to city
--grant delete on city to sysdba
--revoke/*回收*/ connect,resource from city
--alter/*修改*/ user city identified by "654321"
--alter user city account lock/*锁*/ /*unlock 解锁*/
/*create table 表名(
字段1 数据类型 [约束]，
字段2 数据类型 [约束]，
......
)*/

create table emp(--员工表
id number (10),--序列号
empno number(4),--员工编号
ename varchar2(50),--员工姓名
job varchar2(50),--员工种类
mgr varchar2(50),--直接上级
hirdate varchar2(50),--入职日期
sal number(7,2),--工资
comm number(7,2),--佣金
deptno number(4)--部门编号
)
alter table emp add constraint pk_empno primary key(empno)--加主键
insert into emp (id,empno,ename,job,mgr,hirdate,sal,comm,deptno) values(seq.nextval,001,'张三','税务员','科长','1999/5/6',2300,800,0321)--向表中插入数据
insert into emp (id,empno,ename,job,mgr,hirdate,sal,comm,deptno) values(seq.nextval,002,'李四','稽查员','室长','2003/11/5',2200,600,0896)
insert into emp (id,empno,ename,job,mgr,hirdate,sal,comm,deptno) values(seq.nextval,004,'王五','程序员','经理','2012/2/9',3300,750,0527)
drop table emp  --删除表
select * from emp--显示表 
delete from emp where empno=1 --删除表中的某一项                                    

create table dept(--部门表
deptno number(4),--部门数量
dname varchar2(50),--部门名称
loc varchar2(50)--
)

select * from dept

create table salgrade(--薪资表
grade number(4),--学历
losal number(4),--最低工资
hisal number(4)--最高工资
)

select * from salgrade

select ename ,job from emp where sal=2300    --通过某几项从某个表中查找某几项
select count(*),sal from emp group by sal having sal>1000--查询满足某一条件的所有项,所有项必须是同一类
select count(*),sal from emp group by sal having sal>1000 order by sal asc--对搜索结果进行排序，asc为升序，desc为降序

create sequence seq --创建序列
increment by 值  --增量
start with 值   --初始值
maxvalue 值     --最大值
minvalue 值    --最小值
cycle|nocycle  --是否循环
cache 值|no cache    --是否缓存
drop sequence seq   --删除序列
select seq.currval from dual --查看序列的下一个值,seq.nextval为自然数升序，seq.currval为当前数

alter table emp modify sal varchar2   --修改某一项的数据类型
alter table emp drop column sal    --删除某一列

select * from emp where ename like '%三%'  --模糊查询，%代表不定多的字符，_代表一个字符

--alter table 表名 add constraint 主键名 primary key(字段)pk_字段

--alter table 子表名 add constraint 外键名 foreign key(子表字段) references 父表 fk_子表_父表

--alter table 表名 add constraint 唯一约束名 unique(字段) uq_检查约束名 check(字段) ck_

--alter table 表名 drop constraint 约束名

--create tablespace 表空间名称 datafile '路径+文件名1.dbf’ size 值(单位M)1，'路径+文件名2.dbf’ size 值2...
create tablespace emp datafile
'f:\s2\database\emp.dbf'size 8M

alter database datafile 'f:\s2\database\emp.dbf' resize 80M

alter tablespace emp add datafile'f:\s2\database\emp1.dbf' size 10M
drop tablespace emp
