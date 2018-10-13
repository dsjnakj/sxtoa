select * from dept;

select * from position;

insert into position values (1,'总裁','公司总体发展战略规划和管理');
insert into position values (2,'教学经理','负责日常教学管理');
insert into position values (3,'咨询经理','负责咨询部日常管理');
insert into position values (4,'咨询师','完成日常咨询任务');
insert into position values (5,'讲师','完成日常授课任务，优化课程设计');

commit;


insert into employee (empid,deptno,posid,realName,emptype) values ('gaoqi',1,1,'高淇',2);
commit;

select * from employee;


--查询所有员工信息
select emp.*,d.deptname,d.location,p.pname,p.pdesc,mgr.realname
from employee emp
join dept d
on emp.deptno = d.deptno
join position p
on emp.posid = p.posid
join employee mgr
on emp.mgrid = mgr.empid


