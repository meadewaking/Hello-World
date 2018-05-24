create table dept(
       id number(7) primary key,
       name varchar2(50) not null
);
create table emp(
       id number(7),
       name varchar2(50) not null,
       sex varchar2(50) default '男',
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

insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(001,'张三','男','1999/5/6','18',2800,800,11);
insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(004,'李四','男','1989/4/16','28',2400,600,11);
insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(002,'王五','女','1992/8/16','28',3200,1800,22);
insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(005,'王二','女','1992/8/16','38',3600,1600,22);
insert into emp (id,name,sex,birth,age,sal,comm,deptno) values(003,'赵六','男','1989/3/3','36',4600,2800,33);

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
select sal as ddd from emp;             --以别名显示查找项
select sal ddd from emp;                --as可以省略
select emp.name, emp.sex ,emp.age, dept.id from emp ,dept where emp.deptno = dept.id  --跨表查询
select a.name,a.sex,a.birth,b.id from emp a,dept b where a.deptno = b.id   --用别名跨表查询
select * from no
union                       --同列数表合并，并去掉重复记录
select * from dept 

select * from no
union all                      --同列数表合并，不去掉重复记录
select * from dept 

select * from no
minus                      --取差积
select * from dept 

select upper('abc') from dual    --upper转换为大写
select length('找到abc') from dual
select substr('abc',2,2) from dual    --截取字符串substr(字符串，开始位置，截取长度)
select a.*,b.* from emp a left join dept b on a.deptno = b.id;    --左连接
select a.*,b.* from emp a right join dept b on a.deptno = b.id;    --右连接
select a.*,b.* from emp a full join dept b on a.deptno = b.id;    --全连接
select * from emp where sal > (select avg(sal) from emp where deptno = 11) and deptno = 11;   --查找编号为11部门高于平均工资的员工
select * from emp e where sal > (select avg(sal)from emp where deptno = e.deptno);      --遍历表中高于同一部门平均工资的员工 
select * from emp where sal > all(select sal from emp where deptno = 22);               --找出所有比编号为22的部门员工工资都高的员工
select * from emp where sal > any(select sal from emp where deptno = 22);               --找出所有比编号为22的部门任何一个员工工资都高的员工
Create or replace public synonym a for scott.emp                                                     --创建同义词
drop synonym a                                                                          --删除同义词
select * from a

declare
       a int;
       e emp%rowtype;
begin
       a:=&a;                                              --交互式赋值
       select * into e from emp where id = a;             --将按条件找到的字段值赋给定义的承接变量
       dbms_output.put_line('id = '||e.id||' name = '||e.name);                   --输出，字符串连接符为||
       exception
         when no_data_found then
           dbms_output.put_line('没有此部门');
         when too_many_rows then
           dbms_output.put_line('返回记录数过多');
end;

declare
       a int;
begin
       a:=&a;                                              --交互式赋值
       if a>1 then
         dbms_output.put_line('大于一');
       elsif a=1 then
         dbms_output.put_line('等于一');
       else
         dbms_output.put_line('小于一');
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
