--�����Ŀ

--����Ա����
create table emp(
    id number(10) primary key,--Ա���������
    ename varchar2(50),--Ա������
    sex varchar2(50),--�Ա�
    birth varchar2(50),--��������
    sal number(7,2),--����
    dId number(10)--���������dept�����ű��
);

select * from emp;--��ѯemp����������Ϣ
drop table emp;--ɾ��emp��

--�������ű�
create table dept(
    id number(10) primary key,--���ű������
    dname varchar2(50)--��������
);

select * from dept;--��ѯdept����������Ϣ
drop table dept;--ɾ��dept��

alter table emp add constraint fk_dept_emp_did foreign key(dId) references dept(id);--(fk+������+�ӱ���+����)
--�����������ϵ

create sequence seqe;--emp����
create sequence seqd;--dept����
--��������

drop sequence seqe;--emp����
drop sequence seqd;--dept����
--ɾ������

insert into dept(id,dname) values(seqe.nextval,'001');
insert into dept(id,dname) values(seqe.nextval,'002');
insert into dept(id,dname) values(seqe.nextval,'003');
--���븸������

insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'��һ','��',to_date('1999-5-6','YYYY-MM-DD'),trunc(4000.556,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'�Ŷ�','��',to_date('1987-02-08','YYYY-MM-DD'),trunc(8000.587,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'����','Ů',to_date('1976-12-20','YYYY-MM-DD'),trunc(3000.721,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'����','Ů',to_date('1994-10-20','YYYY-MM-DD'),trunc(4000.114,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'����','��',to_date('1999-11-20','YYYY-MM-DD'),trunc(5000.879,2),1);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'����','Ů',to_date('1989-01-22','YYYY-MM-DD'),trunc(5300.235,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'����','Ů',to_date('1982-02-02','YYYY-MM-DD'),trunc(2100.476,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'����','��',to_date('1983-05-10','YYYY-MM-DD'),trunc(4800.593,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'����','Ů',to_date('1982-09-11','YYYY-MM-DD'),trunc(6700.214,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'��ʮ','��',to_date('1996-08-15','YYYY-MM-DD'),trunc(2200.659,2),2);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'֣ʮһ','��',to_date('1978-07-09','YYYY-MM-DD'),trunc(3100.985,2),3);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'֣ʮ��','Ů',to_date('1996-04-25','YYYY-MM-DD'),trunc(3300.578,2),3);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'��ʮ��','��',to_date('1990-06-08','YYYY-MM-DD'),trunc(4400.246,2),3);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'��ʮ��','Ů',to_date('1993-09-12','YYYY-MM-DD'),trunc(7600.159,2),3);
insert into emp(id,ename,sex,birth,sal,dId) values(seqd.nextval,'κʮ��','��',to_date('1992-10-15','YYYY-MM-DD'),trunc(5900.861,2),3);
--�����ӱ����ݣ�ʹ��trunc����������λС����trunc���������������

/*����Ϊ���ݱ���*/

/*��1����дһ���洢����ʵ����ʾÿ���������ơ�����id��ÿ��������Ա������*/

create or replace procedure proputInfo(x int)--�����������ԭ�еģ��洢������ΪputInfo,���޲δ洢���������ܴ���������Լ��ʹ���вΣ�
as
       e dept%rowtype;--����dept����б���e
       cursor c is select * from dept;--�����α�c��ָ��dept��ĵ�һ������
       tcount int;--��������tcount���н�Ա����
begin
       open c;--���α�
       fetch c into e;--��ȡ�α�c��������
       dbms_output.put_line('��������   ����id  ������Ա��');--���
                                                             
       while(c%found)--�α�c������ʱ����ѭ��
       loop--ѭ����ʼ
             select count(*) into tcount from emp where did = e.id;--��ѯָ�����ű�ŵļ�¼��������¼����������������������tcount
             dbms_output.put_line('  '||e.dname||'        '||e.id||'        '||tcount);--ѭ�������Ϣ
             fetch c into e; --�α�����
       end loop;--����ѭ��
       close c;--�ر��α�
end;

--����
declare
  x int;     --�������ò���x
begin
  proputInfo(x);--���ô洢����
end;

/*��2����дһ������ʵ�ֲ������й��ʸ��ڹ�˾ƽ�����ʵ��ˣ�
       ʵ�ֲ�ѯ���ʸ��ڱ�����ƽ�����ʵ�Ա����
       ʵ�ֲ���ÿ�����Ź�����ߵ��˵���ϸ���ϡ�*/

create or replace function funSelectInfo(x int) return int--�����������ԭ�еģ�������ΪfunSelectInfo,����int�����ݣ����ã���Լ��ʹ���вΣ�
as
       e1 emp%rowtype;--�����б���e1�����ڳнӸ��ڹ�˾ƽ�����ʵ���
       e2 emp%rowtype;--�����б���e2�����ڳнӸ��ڲ���ƽ�����ʵ���
       e3 emp%rowtype;--�����б���e3�����ڳнӲ�����߹��ʵ���
       cursor c1 is select * from emp where sal>(select avg(sal) from emp);
       --�����α�c1��ָ����ڹ�˾ƽ�����ʵĵ�һ����¼
       cursor c2 is select * from emp e where sal>(select avg(sal) from emp where dId = e.dId);
       --�����α�c2��ָ����ڲ���ƽ�����ʵĵ�һ����¼
       cursor c3 is select * from emp e where sal=(select max(sal) from emp where dId = e.dId);
       --�����α�c3��ָ���Ź�����ߵĵ�һ����¼
begin
       open c1;--���α�
       dbms_output.put_line('���ʸ��ڹ�˾ƽ�����ʵ�Ա��:');
       dbms_output.put_line(' '||'���'||' '||'����'||'  '||'�Ա�'||' '||'����'||'         '||'����'||'      '||'����');
       fetch c1 into e1;--��ȡ�α�c1��������
       while(c1%found)--�α�c1������ʱ����ѭ��
       loop--ѭ��
             dbms_output.put_line(' '||e1.id||'    '||e1.ename||'  '||e1.sex||'   '||e1.birth||'   '||e1.sal||'   '||e1.did);
             fetch c1 into e1; --�α�����
       end loop;--����ѭ��
       dbms_output.put_line('');
       close c1;--�ر��α�

       open c2;--���α�
       dbms_output.put_line('���ڱ�����ƽ�����ʵ�Ա����');
       dbms_output.put_line(' '||'���'||' '||'����'||'  '||'�Ա�'||' '||'����'||'         '||'����'||'      '||'����');
       fetch c2 into e2;--��ȡ�α�c2��������
       while(c2%found)--�α�c2������ʱ����ѭ��
       loop--ѭ��
             dbms_output.put_line(' '||e2.id||'    '||e2.ename||'  '||e2.sex||'   '||e2.birth||'   '||e2.sal||'   '||e2.did);
             fetch c2 into e2; --�α�����
       end loop;--����ѭ��
       dbms_output.put_line('');
       close c2;--�ر��α�

       open c3;--���α�
       dbms_output.put_line('ÿ�����Ź�����ߵ��ˣ�');
       dbms_output.put_line(' '||'���'||' '||'����'||'  '||'�Ա�'||' '||'����'||'         '||'����'||'      '||'����');
       fetch c3 into e3;--��ȡ�α�c3��������
       while(c3%found)--�α�c3������ʱ����ѭ��
       loop--ѭ��
             dbms_output.put_line(' '||e3.id||'    '||e3.ename||'  '||e3.sex||'   '||e3.birth||'   '||e3.sal||'   '||e3.did);
             fetch c3 into e3; --�α�����
       end loop;--����ѭ��
       dbms_output.put_line('');
       close c3;--�ر��α�
       
       return 1;--���÷���
end;

--�����ĵ���
declare
  x int; --���ñ���x
  y int; --���ñ���y
begin
  y:=funSelectInfo(x);--���ú��������óн�(ΪʲôҪ�ú�����)
end;

/*��3����дһ��������ʵ�ּ���ɾ������ɾ��1�����ź�Ҫ���ò����µ�����Ա����ɾ����*/
--��Ҫ���˻�����,�޷���sysӵ�еĶ��󴴽�������
create user help identified by "123456";--���������û�help
grant connect,resource,DBA to help;--����helpȨ��
grant all on emp to help ;--����help emp���ȫ��Ȩ��
grant all on dept to help ;--����help dept���ȫ��Ȩ��
drop user help cascade;   --ɾ��help
--help�Ա�Ĳ�����Ӱ��sys������

create or replace trigger dropSingleDept--�����������ԭ�еģ���������ΪdropSingleDept
after delete on dept--ɾ���󴥷�
for each row--�м�������
declare 
begin
	delete from emp where emp.dId= :old.id;--ɾ������emp����did��ɾ��ǰdept���е�id��ͬ����Ϣ
end;

delete from dept where id=1;--ɾ������idΪ1����Ϣ

/*��4������������dept���в���3����¼����nameֵ�ֱ�Ϊ10��20��30��
��������������emp���в�����������3�����ŵ�Ա����¼����10����
ÿ�����ŵ�Ա����¼��������3������10����¼��salֵ������ͬ��*/

/*�ʣ���1�����ô洢���̲�ѯԱ���Ĺ�����20����Ա���Ĺ�����ȵ�Ա����
      ��2����ѯԱ���Ĺ��ʴ���20��������Ա���Ĺ��ʵ�Ա����
      ��3����ѯ����Ա�����ʴ��������κ�2��Ա�����ʺ͵�Ա����Ϣ��*/
      
--��������
insert into dept(id,dname) values(seqd.nextval,'10');
insert into dept(id,dname) values(seqd.nextval,'20');
insert into dept(id,dname) values(seqd.nextval,'30');

--��������
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'��ʮ��','��',to_date('1999-5-6','YYYY-MM-DD'),trunc(1000.228,2),4);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'��ʮ��','��',to_date('1987-02-08','YYYY-MM-DD'),trunc(1100.395,2),4);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'��ʮ��','Ů',to_date('1976-12-20','YYYY-MM-DD'),trunc(2300.766,2),4);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'��ʮ��','Ů',to_date('1983-05-10','YYYY-MM-DD'),trunc(3000.721,2),5);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'���ʮ','��',to_date('1996-08-15','YYYY-MM-DD'),trunc(5300.856,2),5);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'�¶�һ','Ů',to_date('1990-06-08','YYYY-MM-DD'),trunc(5600.291,2),5);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'�¶���','Ů',to_date('1990-06-08','YYYY-MM-DD'),trunc(2800.477,2),6);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'�¶���','��',to_date('1999-11-20','YYYY-MM-DD'),trunc(4200.393,2),6);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'������','Ů',to_date('1992-10-15','YYYY-MM-DD'),trunc(5100.861,2),6);
insert into emp(id,ename,sex,birth,sal,dId) values(seqe.nextval,'������','��',to_date('1983-05-10','YYYY-MM-DD'),trunc(4300.779,2),6);

create or replace procedure proSelectInfo(x int)
as
       e4 emp%rowtype;--�����б���e4�н���20�������Ա��
       e5 emp%rowtype;--�����б���e5�нӴ���20�������Ա��
       e6 emp%rowtype;--�����б���e6�н���С����Ա��
       e7 emp%rowtype;--�����б���e7�нӴ�С����Ա��
       e8 emp%rowtype;--�����б���e8�нӴ�����С���ʺ�Ա��
       cursor c4 is select * from emp where sal in (select sal from emp where did = (select id from dept where dname = '20')) and did <> (select id from dept where dname = '20');
       --��ѯԱ���Ĺ�����20����Ա���Ĺ�����ȵ�Ա������ȥ20���ŵ�Ա����
       cursor c5 is select * from emp where sal >(select max(sal) from emp where did = (select id from dept where dname = '20')); 
       --��ѯԱ���Ĺ��ʴ���20��������Ա���Ĺ��ʵ�Ա��
       cursor c6 is select * from emp;--�����α�c�ڲ�ѯemp���������ݵĵ�һ�������Ϸ�

begin
       open c4;--���α�
       fetch c4 into e4;--��ȡ�α�c4��ͨ�����α����ƶ�ȡ�α��е�ֵ���ѱ��е�һ�����ݸ����б���e4��
       dbms_output.put_line('������20����Ա���Ĺ�����ȵ�Ա����');
       dbms_output.put_line(' '||'���'||' '||'����'||'  '||'�Ա�'||' '||'����'||'         '||'����'||'      '||'����');
       while(c4%found)--�����α�c4�����ݽ���ѭ��
       loop--ѭ��
             dbms_output.put_line(' '||e4.id||'    '||e4.ename||'  '||e4.sex||'   '||e4.birth||'   '||e4.sal||'   '||e4.did);--�����Ϣ
             fetch c4 into e4; --�α�����
       end loop;--����ѭ��
       close c4;--�ر��α�
       dbms_output.put_line('');
       
       
       open c5;--���α�
       fetch c5 into e5;--��ȡ�α�c5��ͨ�����α����ƶ�ȡ�α��е�ֵ���ѱ��е�һ�����ݸ����б���e5��
       dbms_output.put_line('���ʴ���20��������Ա���Ĺ��ʵ�Ա����');
       dbms_output.put_line(' '||'���'||' '||'����'||'  '||'�Ա�'||' '||'����'||'         '||'����'||'      '||'����');
       while(c5%found)--�����α�c5�����ݽ���ѭ��
       loop--ѭ��
             dbms_output.put_line(' '||e5.id||'    '||e5.ename||'  '||e5.sex||'   '||e5.birth||'   '||e5.sal||'   '||e5.did);--�����Ϣ
             fetch c5 into e5; --�α�����
       end loop;--����ѭ��
       close c5;--�ر��α�
       dbms_output.put_line('');
       
       
       dbms_output.put_line('Ա�����ʴ��������κ�2��Ա�����ʺ͵�Ա����');
       dbms_output.put_line(' '||'���'||' '||'����'||'  '||'�Ա�'||' '||'����'||'         '||'����'||'      '||'����');
       select * into e6 from emp where sal = (select min(sal) from emp);
       --��ѯemp�������㹤��Ϊ��С��Ա������Ϣ��ֵ��e6
       select * into e7 from emp where sal = (select min(sal) from emp where id <> e6.id);
       --��ѯemp�������㹤��Ϊ�ڶ�С��Ա������Ϣ��ֵ��e7
       
       open c6;--���α�
       fetch c6 into e8;--��ȡ�α�c6��ͨ�����α����ƶ�ȡ�α��е�ֵ���ѱ��е�һ�����ݸ����б���e8��
       while(c6%found)--�����α�c6�����ݽ���ѭ��
            loop--ѭ��
                  if e8.sal > (e7.sal+e6.sal) then--�������������С���ʺ������
                     dbms_output.put_line(' '||e8.id||'    '||e8.ename||'  '||e8.sex||'   '||e8.birth||'   '||e8.sal||'   '||e8.did);--ѭ�������Ϣ
                  end if;--����
                  fetch c6 into e8;--�α�����
            end loop;--����ѭ��
       close c6;--�ر��α�
end;


--����
declare
  x int;--���ñ���x
begin
   proSelectInfo(x);
end;
