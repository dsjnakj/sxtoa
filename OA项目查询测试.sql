select * from dept;

select * from position;

insert into position values (1,'�ܲ�','��˾���巢չս�Թ滮�͹���');
insert into position values (2,'��ѧ����','�����ճ���ѧ����');
insert into position values (3,'��ѯ����','������ѯ���ճ�����');
insert into position values (4,'��ѯʦ','����ճ���ѯ����');
insert into position values (5,'��ʦ','����ճ��ڿ������Ż��γ����');

commit;


insert into employee (empid,deptno,posid,realName,emptype) values ('gaoqi',1,1,'���',2);
commit;

select * from employee;


--��ѯ����Ա����Ϣ
select emp.*,d.deptname,d.location,p.pname,p.pdesc,mgr.realname
from employee emp
join dept d
on emp.deptno = d.deptno
join position p
on emp.posid = p.posid
join employee mgr
on emp.mgrid = mgr.empid


