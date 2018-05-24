--结课项目

--创建员工表
create table emp(
    id number(10) primary key,--员工编号主键
    ename varchar2(50),--员工姓名
    sex varchar2(50),--性别
    birth varchar2(50),--出生年月
    sal number(7,2),--工资
    dId number(10)--外键，关联dept表，部门编号
);

select * from emp;--查询emp表中所有信息
drop table emp;--删除emp表

--创建部门表
create table dept(
    id number(10) primary key,--部门编号主键
    dname varchar2(50)--部门名称
);

select * from dept;--查询dept表中所有信息
drop table dept;--删除dept表

alter table emp add constraint fk_dept_emp_did foreign key(dId) references dept(id);--(fk+父表名+子表名+键名)
--建立主外键联系

create sequence seqe;--emp序列
create sequence seqd;--dept序列
--建立序列

drop sequence seqe;--emp序列
drop sequence seqd;--dept序列
--删除序列

insert into dept(id,dname) values(seqe.nextval,'001');
insert into dept(id,dname) values(seqe.nextval,'002');
insert into dept(id,dname) values(seqe.nextval,'003');
--插入父表数据

insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'张一','男',to_date('1999-5-6','YYYY-MM-DD'),trunc(4000.556,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'张二','男',to_date('1987-02-08','YYYY-MM-DD'),trunc(8000.587,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'张三','女',to_date('1976-12-20','YYYY-MM-DD'),trunc(3000.721,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'李四','女',to_date('1994-10-20','YYYY-MM-DD'),trunc(4000.114,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'王五','男',to_date('1999-11-20','YYYY-MM-DD'),trunc(5000.879,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'赵六','女',to_date('1989-01-22','YYYY-MM-DD'),trunc(5300.235,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'李七','女',to_date('1982-02-02','YYYY-MM-DD'),trunc(2100.476,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'王八','男',to_date('1983-05-10','YYYY-MM-DD'),trunc(4800.593,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'刘九','女',to_date('1982-09-11','YYYY-MM-DD'),trunc(6700.214,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'刘十','男',to_date('1996-08-15','YYYY-MM-DD'),trunc(2200.659,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'郑十一','男',to_date('1978-07-09','YYYY-MM-DD'),trunc(3100.985,2),3);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'郑十二','女',to_date('1996-04-25','YYYY-MM-DD'),trunc(3300.578,2),3);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'吴十三','男',to_date('1990-06-08','YYYY-MM-DD'),trunc(4400.246,2),3);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'黄十四','女',to_date('1993-09-12','YYYY-MM-DD'),trunc(7600.159,2),3);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'魏十五','男',to_date('1992-10-15','YYYY-MM-DD'),trunc(5900.861,2),3);
--插入子表数据，使用trunc函数保留两位小数※trunc函数不四舍五入※

/*以上为数据表建立*/

/*（1）编写一个存储过程实现显示每个部门名称、部门id和每个部门人员数量。*/

create or replace procedure proputInfo(x int)--创建并替代（原有的）存储过程名为putInfo,※无参存储过程名不能带（）※（约定使用有参）
as
       e dept%rowtype;--声明dept表的行变量e
       cursor c is select * from dept;--声明游标c并指向dept表的第一条数据
       tcount int;--声明变量tcount，承接员工数
begin
       open c;--打开游标
       fetch c into e;--提取游标c，并下移
       dbms_output.put_line('部门名称   部门id  部门人员数');--输出
                                                             
       while(c%found)--游标c有数据时进行循环
       loop--循环开始
             select count(*) into tcount from emp where did = e.id;--查询指定部门编号的记录，并将记录的条数（部门人数）赋给tcount
             dbms_output.put_line('  '||e.dname||'        '||e.id||'        '||tcount);--循环输出信息
             fetch c into e; --游标下移
       end loop;--结束循环
       close c;--关闭游标
end;

--调用
declare
  x int;     --声明无用参数x
begin
  proputInfo(x);--调用存储过程
end;

/*（2）编写一个函数实现查找所有工资高于公司平均工资的人；
       实现查询工资高于本部门平均工资的员工；
       实现查找每个部门工资最高的人的详细资料。*/

create or replace function funSelectInfo(x int) return int--创建并替代（原有的）函数名为funSelectInfo,返回int型数据（无用）（约定使用有参）
as
       e1 emp%rowtype;--声明行变量e1，用于承接高于公司平均工资的人
       e2 emp%rowtype;--声明行变量e2，用于承接高于部门平均工资的人
       e3 emp%rowtype;--声明行变量e3，用于承接部门最高工资的人
       cursor c1 is select * from emp where sal>(select avg(sal) from emp);
       --声明游标c1并指向高于公司平均工资的第一条记录
       cursor c2 is select * from emp e where sal>(select avg(sal) from emp where dId = e.dId);
       --声明游标c2并指向高于部门平均工资的第一条记录
       cursor c3 is select * from emp e where sal=(select max(sal) from emp where dId = e.dId);
       --声明游标c3并指向部门工资最高的第一条记录
begin
       open c1;--打开游标
       dbms_output.put_line('工资高于公司平均工资的员工:');
       dbms_output.put_line(' '||'编号'||' '||'姓名'||'  '||'性别'||' '||'生日'||'         '||'工资'||'      '||'部门');
       fetch c1 into e1;--提取游标c1，并下移
       while(c1%found)--游标c1有数据时进行循环
       loop--循环
             dbms_output.put_line(' '||e1.id||'    '||e1.ename||'  '||e1.sex||'   '||e1.birth||'   '||e1.sal||'   '||e1.did);
             fetch c1 into e1; --游标下移
       end loop;--结束循环
       dbms_output.put_line('');
       close c1;--关闭游标

       open c2;--打开游标
       dbms_output.put_line('高于本部门平均工资的员工：');
       dbms_output.put_line(' '||'编号'||' '||'姓名'||'  '||'性别'||' '||'生日'||'         '||'工资'||'      '||'部门');
       fetch c2 into e2;--提取游标c2，并下移
       while(c2%found)--游标c2有数据时进行循环
       loop--循环
             dbms_output.put_line(' '||e2.id||'    '||e2.ename||'  '||e2.sex||'   '||e2.birth||'   '||e2.sal||'   '||e2.did);
             fetch c2 into e2; --游标下移
       end loop;--结束循环
       dbms_output.put_line('');
       close c2;--关闭游标

       open c3;--打开游标
       dbms_output.put_line('每个部门工资最高的人：');
       dbms_output.put_line(' '||'编号'||' '||'姓名'||'  '||'性别'||' '||'生日'||'         '||'工资'||'      '||'部门');
       fetch c3 into e3;--提取游标c3，并下移
       while(c3%found)--游标c3有数据时进行循环
       loop--循环
             dbms_output.put_line(' '||e3.id||'    '||e3.ename||'  '||e3.sex||'   '||e3.birth||'   '||e3.sal||'   '||e3.did);
             fetch c3 into e3; --游标下移
       end loop;--结束循环
       dbms_output.put_line('');
       close c3;--关闭游标
       
       return 1;--无用返回
end;

--函数的调用
declare
  x int; --无用变量x
  y int; --无用变量y
begin
  y:=funSelectInfo(x);--调用函数，无用承接(为什么要用函数？)
end;

/*（3）编写一个触发器实现级联删除，即删除1个部门后要将该部门下的所有员工均删除。*/
--需要换账户登入,无法对sys拥有的对象创建触发器
create user help identified by "123456";--创建测试用户help
grant connect,resource,DBA to help;--赋予help权限
grant all on emp to help ;--赋予help emp表的全部权限
grant all on dept to help ;--赋予help dept表的全部权限
drop user help cascade;   --删除help
--help对表的操作不影响sys的数据

create or replace trigger dropSingleDept--创建并替代（原有的）触发器名为dropSingleDept
after delete on dept--删除后触发
for each row--行级触发器
declare 
begin
	delete from emp where emp.dId= :old.id;--删除满足emp表中did与删除前dept表中的id相同的信息
end;

delete from dept where id=1;--删除部门id为1的信息

/*（4）运用序列向dept表中插入3条记录，其name值分别为10、20、30。
而后运用序列向emp表中插入隶属于这3个部门的员工记录共计10条，
每个部门的员工记录数不低于3条，这10条记录的sal值各不相同。*/

/*问：（1）运用存储过程查询员工的工资与20部门员工的工资相等的员工；
      （2）查询员工的工资大于20部门所有员工的工资的员工；
      （3）查询所有员工工资大于其他任何2名员工工资和的员工信息。*/
      
--插入数据
insert into dept(id,dname) values(seqd.nextval,'10');
insert into dept(id,dname) values(seqd.nextval,'20');
insert into dept(id,dname) values(seqd.nextval,'30');

--插入数据
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'许十六','男',to_date('1999-5-6','YYYY-MM-DD'),trunc(1000.228,2),4);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'许十七','男',to_date('1987-02-08','YYYY-MM-DD'),trunc(1100.395,2),4);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'许十八','女',to_date('1976-12-20','YYYY-MM-DD'),trunc(2300.766,2),4);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'许十九','女',to_date('1983-05-10','YYYY-MM-DD'),trunc(3000.721,2),5);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'许二十','男',to_date('1996-08-15','YYYY-MM-DD'),trunc(5300.856,2),5);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'陈二一','女',to_date('1990-06-08','YYYY-MM-DD'),trunc(5600.291,2),5);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'陈二二','女',to_date('1990-06-08','YYYY-MM-DD'),trunc(2800.477,2),6);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'陈二三','男',to_date('1999-11-20','YYYY-MM-DD'),trunc(4200.393,2),6);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'吕二四','女',to_date('1992-10-15','YYYY-MM-DD'),trunc(5100.861,2),6);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'吕二五','男',to_date('1983-05-10','YYYY-MM-DD'),trunc(4300.779,2),6);

create or replace procedure proSelectInfo(x int)
as
       e4 emp%rowtype;--声明行变量e4承接与20部门相等员工
       e5 emp%rowtype;--声明行变量e5承接大于20部门相等员工
       e6 emp%rowtype;--声明行变量e6承接最小工资员工
       e7 emp%rowtype;--声明行变量e7承接次小工资员工
       e8 emp%rowtype;--声明行变量e8承接大于最小工资和员工
       cursor c4 is select * from emp where sal in (select sal from emp where did = (select id from dept where dname = '20')) and did <> (select id from dept where dname = '20');
       --查询员工的工资与20部门员工的工资相等的员工（除去20部门的员工）
       cursor c5 is select * from emp where sal >(select max(sal) from emp where did = (select id from dept where dname = '20')); 
       --查询员工的工资大于20部门所有员工的工资的员工
       cursor c6 is select * from emp;--声明游标c在查询emp表所有数据的第一条数据上方

begin
       open c4;--打开游标
       fetch c4 into e4;--读取游标c4，通过将游标下移读取游标中的值（把表中第一行数据赋给行变量e4）
       dbms_output.put_line('工资与20部门员工的工资相等的员工：');
       dbms_output.put_line(' '||'编号'||' '||'姓名'||'  '||'性别'||' '||'生日'||'         '||'工资'||'      '||'部门');
       while(c4%found)--满足游标c4有数据进行循环
       loop--循环
             dbms_output.put_line(' '||e4.id||'    '||e4.ename||'  '||e4.sex||'   '||e4.birth||'   '||e4.sal||'   '||e4.did);--输出信息
             fetch c4 into e4; --游标下移
       end loop;--结束循环
       close c4;--关闭游标
       dbms_output.put_line('');
       
       
       open c5;--打开游标
       fetch c5 into e5;--读取游标c5，通过将游标下移读取游标中的值（把表中第一行数据赋给行变量e5）
       dbms_output.put_line('工资大于20部门所有员工的工资的员工：');
       dbms_output.put_line(' '||'编号'||' '||'姓名'||'  '||'性别'||' '||'生日'||'         '||'工资'||'      '||'部门');
       while(c5%found)--满足游标c5有数据进行循环
       loop--循环
             dbms_output.put_line(' '||e5.id||'    '||e5.ename||'  '||e5.sex||'   '||e5.birth||'   '||e5.sal||'   '||e5.did);--输出信息
             fetch c5 into e5; --游标下移
       end loop;--结束循环
       close c5;--关闭游标
       dbms_output.put_line('');
       
       
       dbms_output.put_line('员工工资大于其他任何2名员工工资和的员工：');
       dbms_output.put_line(' '||'编号'||' '||'姓名'||'  '||'性别'||' '||'生日'||'         '||'工资'||'      '||'部门');
       select * into e6 from emp where sal = (select min(sal) from emp);
       --查询emp表中满足工资为最小的员工的信息赋值给e6
       select * into e7 from emp where sal = (select min(sal) from emp where id <> e6.id);
       --查询emp表中满足工资为第二小的员工的信息赋值给e7
       
       open c6;--打开游标
       fetch c6 into e8;--读取游标c6，通过将游标下移读取游标中的值（把表中第一行数据赋给行变量e8）
       while(c6%found)--满足游标c6有数据进行循环
            loop--循环
                  if e8.sal > (e7.sal+e6.sal) then--如果工资满足最小工资和则输出
                     dbms_output.put_line(' '||e8.id||'    '||e8.ename||'  '||e8.sex||'   '||e8.birth||'   '||e8.sal||'   '||e8.did);--循环输出信息
                  end if;--结束
                  fetch c6 into e8;--游标下移
            end loop;--结束循环
       close c6;--关闭游标
end;


--调用
declare
  x int;--无用变量x
begin
   proSelectInfo(x);
end;
