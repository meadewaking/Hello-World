--create user city identified by "123456"
--create user drajon identified by "123456"                   
--create user snake identified by "123456"
--create user frouj identified by "123456"
--grant connect,resource to city
--grant delete on city to sysdba
--revoke/*����*/ connect,resource from city
--alter/*�޸�*/ user city identified by "654321"
--alter user city account lock/*��*/ /*unlock ����*/
/*create table ����(
�ֶ�1 �������� [Լ��]��
�ֶ�2 �������� [Լ��]��
......
)*/

create table emp(--Ա����
id number (10),--���к�
empno number(4),--Ա�����
ename varchar2(50),--Ա������
job varchar2(50),--Ա������
mgr varchar2(50),--ֱ���ϼ�
hirdate varchar2(50),--��ְ����
sal number(7,2),--����
comm number(7,2),--Ӷ��
deptno number(4)--���ű��
)
alter table emp add constraint pk_empno primary key(empno)--������
insert into emp (id,empno,ename,job,mgr,hirdate,sal,comm,deptno) values(seq.nextval,001,'����','˰��Ա','�Ƴ�','1999/5/6',2300,800,0321)--����в�������
insert into emp (id,empno,ename,job,mgr,hirdate,sal,comm,deptno) values(seq.nextval,002,'����','����Ա','�ҳ�','2003/11/5',2200,600,0896)
insert into emp (id,empno,ename,job,mgr,hirdate,sal,comm,deptno) values(seq.nextval,004,'����','����Ա','����','2012/2/9',3300,750,0527)
drop table emp  --ɾ����
select * from emp--��ʾ�� 
delete from emp where empno=1 --ɾ�����е�ĳһ��                                    

create table dept(--���ű�
deptno number(4),--��������
dname varchar2(50),--��������
loc varchar2(50)--
)

select * from dept

create table salgrade(--н�ʱ�
grade number(4),--ѧ��
losal number(4),--��͹���
hisal number(4)--��߹���
)

select * from salgrade

select ename ,job from emp where sal=2300    --ͨ��ĳ�����ĳ�����в���ĳ����
select count(*),sal from emp group by sal having sal>1000--��ѯ����ĳһ������������,�����������ͬһ��
select count(*),sal from emp group by sal having sal>1000 order by sal asc--�����������������ascΪ����descΪ����

create sequence seq --��������
increment by ֵ  --����
start with ֵ   --��ʼֵ
maxvalue ֵ     --���ֵ
minvalue ֵ    --��Сֵ
cycle|nocycle  --�Ƿ�ѭ��
cache ֵ|no cache    --�Ƿ񻺴�
drop sequence seq   --ɾ������
select seq.currval from dual --�鿴���е���һ��ֵ,seq.nextvalΪ��Ȼ������seq.currvalΪ��ǰ��

alter table emp modify sal varchar2   --�޸�ĳһ�����������
alter table emp drop column sal    --ɾ��ĳһ��

select * from emp where ename like '%��%'  --ģ����ѯ��%����������ַ���_����һ���ַ�

--alter table ���� add constraint ������ primary key(�ֶ�)pk_�ֶ�

--alter table �ӱ��� add constraint ����� foreign key(�ӱ��ֶ�) references ���� fk_�ӱ�_����

--alter table ���� add constraint ΨһԼ���� unique(�ֶ�) uq_���Լ���� check(�ֶ�) ck_

--alter table ���� drop constraint Լ����

--create tablespace ��ռ����� datafile '·��+�ļ���1.dbf�� size ֵ(��λM)1��'·��+�ļ���2.dbf�� size ֵ2...
create tablespace emp datafile
'f:\s2\database\emp.dbf'size 8M

alter database datafile 'f:\s2\database\emp.dbf' resize 80M

alter tablespace emp add datafile'f:\s2\database\emp1.dbf' size 10M
drop tablespace emp
