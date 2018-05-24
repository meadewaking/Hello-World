create table dept(
       id number(7) primary key,
       name varchar2(50) not null
);
create table emp(
       id number(7),
       name varchar2(50) not null,
       sex varchar2(50) default '��',
       birth varchar2(50),
       age varchar2(50),
       sal number(7,2),
       comm number(7,2),
       deptno number(4) references dept(id)
);
create table no(
       sm number(7),
       sp varchar2(50)
);
insert into dept(id,name) values(11,'zhao');
insert into dept(id,name) values(22,'qian');
insert into dept(id,name) values(33,'sun');

insert into no(sm,sp) values(112,'zha');
insert into no(sm,sp) values(223,'qia');
insert into no(sm,sp) values(334,'su');

insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(001,'����','��','1999/5/6','18',2800,800,11);
insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(004,'����','��','1989/4/16','28',2400,600,11);
insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(002,'����','Ů','1992/8/16','28',3200,1800,22);
insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(005,'����','Ů','1992/8/16','38',3600,1600,22);
insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(003,'����','��','1989/3/3','36',4600,2800,33);

select max(id) from dept;
select sal/10 from emp;
select * from dept where id='11'or id='22';
select * from dept where name like '%n%';
select * from emp where sal between 2000 and 3000;
select avg(sal) from emp where id='1'or id='2';
select sum(sal) from emp ;
select * from dept;
select * from emp;
select distinct * from emp;
select sal as ddd from emp;             --�Ա�����ʾ������
select sal ddd from emp;                --as����ʡ��
select emp.name, emp.sex ,emp.age, dept.id from emp ,dept where emp.deptno = dept.id  --����ѯ
select a.name,a.sex,a.birth,b.id from emp a,dept b where a.deptno = b.id   --�ñ�������ѯ
select * from no
union                       --ͬ������ϲ�����ȥ���ظ���¼
select * from dept 

select * from no
union all                      --ͬ������ϲ�����ȥ���ظ���¼
select * from dept 

select * from no
minus                      --ȡ���
select * from dept 

select upper('abc') from dual    --upperת��Ϊ��д
select length('�ҵ�abc') from dual
select substr('abc',2,2) from dual    --��ȡ�ַ���substr(�ַ�������ʼλ�ã���ȡ����)
select a.*,b.* from emp a left join dept b on a.deptno = b.id;    --������
select a.*,b.* from emp a right join dept b on a.deptno = b.id;    --������
select a.*,b.* from emp a full join dept b on a.deptno = b.id;    --ȫ����
select * from emp where sal > (select avg(sal) from emp where deptno = 11) and deptno = 11;   --���ұ��Ϊ11���Ÿ���ƽ�����ʵ�Ա��
select * from emp e where sal > (select avg(sal)from emp where deptno = e.deptno);      --�������и���ͬһ����ƽ�����ʵ�Ա�� 
select * from emp where sal > all(select sal from emp where deptno = 22);               --�ҳ����бȱ��Ϊ22�Ĳ���Ա�����ʶ��ߵ�Ա��
select * from emp where sal > any(select sal from emp where deptno = 22);               --�ҳ����бȱ��Ϊ22�Ĳ����κ�һ��Ա�����ʶ��ߵ�Ա��
Create or replace public synonym a for scott.emp                                                     --����ͬ���
drop synonym a                                                                          --ɾ��ͬ���
select * from a

declare
       a int;
       e emp%rowtype;
begin
       a:=&a;                                              --����ʽ��ֵ
       select * into e from emp where id = a;             --���������ҵ����ֶ�ֵ��������ĳнӱ���
       dbms_output.put_line('id = '||e.id||' name = '||e.name);                   --������ַ������ӷ�Ϊ||
       exception
         when no_data_found then
           dbms_output.put_line('û�д˲���');
         when too_many_rows then
           dbms_output.put_line('���ؼ�¼������');
end;

declare
       a int;
begin
       a:=&a;                                              --����ʽ��ֵ
       if a>1 then
         dbms_output.put_line('����һ');
       elsif a=1 then
         dbms_output.put_line('����һ');
       else
         dbms_output.put_line('С��һ');
       end if;
end;

declare
       a int;
begin
       a := 0;
       while a < 10
       loop
             a := a + 1;
             dbms_output.put_line(a);
       end loop; 
end;

declare
       a int;
begin
       a := 0;
       for a in 1..10
       loop
             dbms_output.put_line(a);
       end loop; 
end;
