# SQLD 공부 겸 실습 간략 정리
# HIRedrain
# 작성 : 2025.08.20
# 수정
# 2025.8.22. table 이름 단수 n

# 사전 설정

# 유저 생성
create user 'hiredrain'@'%' identified with mysql_native_password by 'hiredrain';
flush privileges;

# DB 스키마 생성 및 유저에 권한 부여
create database study;
grant all privileges on study.* to 'hiredrain'@'%';
flush privileges;

commit;

# 사용 데이터베이스 지정
use study;

# 기존 테이블 데이터 삭제
drop table if exists emp;
drop table if exists salgrade;
drop table if exists dept;

# table 생성
# 부서 관련 테이블
create table if not exists dept (
  deptno int(11) not null,
  dname varchar(14) default null,
  loc varchar(13) default null,
  primary key (deptno)
);

# 직원 관련 테이블
create table if not exists emp (
  empno int(11) not null,
  ename varchar(10) default null,
  job varchar(9) default null,
  mgr int(11) default null,
  hiredate datetime default null,
  sal double default null,
  comm double default null,
  deptno int(11) default null,
  primary key (empno),
  key pk_emp (deptno)
);

alter table emp add constraint pk_emp foreign key (deptno) references dept (deptno) on delete set null on update cascade;
  
# 월급 등급 관련 테이블
create table if not exists salgrade (
  grade double default null,
  losal double default null,
  hisal double default null
);


# Dummy data

insert into dept (deptno, dname, loc) values (10, 'accounting', 'new york');
insert into dept (deptno, dname, loc) values (20, 'research', 'dallas');
insert into dept (deptno, dname, loc) values (30, 'sales', 'chicago');
insert into dept (deptno, dname, loc) values (40, 'operations', 'boston');

insert into emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) values 
(7369, 'smith', 'clerk', 7902, '1980-12-17 00:00:00', 800, null, 20),
(7499, 'allen', 'salesman', 7698, '1981-02-20 00:00:00', 1600, 300, 30),
(7521, 'ward', 'salesman', 7698, '1981-02-22 00:00:00', 1250, 500, 30),
(7566, 'jones', 'manager', 7839, '1981-04-02 00:00:00', 2975, null, 20),
(7654, 'martin', 'salesman', 7698, '1981-09-28 00:00:00', 1250, 1400, 30),
(7698, 'blake', 'manager', 7839, '1981-05-01 00:00:00', 2850, null, 30),
(7782, 'clark', 'manager', 7839, '1981-06-09 00:00:00', 2450, null, 10),
(7788, 'scott', 'analyst', 7566, '1987-04-19 00:00:00', 3000, null, 20),
(7839, 'king', 'president', null, '1981-11-17 00:00:00', 5000, null, 10),
(7844, 'turner', 'salesman', 7698, '1981-09-08 00:00:00', 1500, 0, 30),
(7876, 'adams', 'clerk', 7788, '1987-05-23 00:00:00', 1100, null, 20),
(7900, 'james', 'clerk', 7698, '1981-12-03 00:00:00', 950, null, 30);

insert into salgrade (grade, losal, hisal) values
(1, 700, 1200),
(2, 1201, 1400),
(3, 1401, 2000),
(4, 2001, 3000),
(5, 3001, 9999);

commit;


# 실습

# 데이터 정의어 (DDL : Data Definition Language) : create, drop, alter, rename, truncate - 테이블 // alter 칼럼 구조 변경할 수도 있음 잘 봐야 함
# 데이터 조작어 (DML :  Data Manipulation Language) : select, insert, delete from, update set - 데이터
# 데이터 제어어 (DCL : Data Control Language) : grant, revoke - 권한
# 트랜잭션 제어어 (TCL : Transaction Control Language) : commit, rollback, savepoint - 트랜잭선 (원자성, 일관성, 고립성, 영속성)
# role : 권한과 유저 사이 존재 - 예) 개발자 role 생성 => 부여
## create role developer grant create session, create table to developer grant developer to my_user;

# 실행 순서
## from (+ Join) => where => group by => having => select => order by => limit 
## order by 는 가장 마지막에 처리, 오름차순 (asc : 기본값), 내림차순 (desc)
## limit : 결과 중 몇 개 행 (row, tuple, records) 출력


# 문자 함수
select dname as 원본, lower(dname) as 소문자, upper(dname) as 대문자 from dept; # 문자 ; 대문자, 소문자 변환
select dname as 원본, length(dname) as 길이 from dept; # 문자 길이 
select dname as 원본, substr(dname, 2, 3) as subsr_ from dept; # substr(칼럼, 시작 문자 위치 - 1부터 시작, 그로부터 출력할 문자 개수)
select dname as 원본, instr(dname, 'A') as instr_ from dept; # instr(칼럼, 찾을 문자) => 해당 문자가 가장 처음에 있는 위치 반환 // 없으면 0
select dname as 원본, lpad(dname, 10, 'L') as ipad_, rpad(dname, 10, 'R') from dept; # lpad(칼럼, 총 몇 자리 문자로 만들 건지, 문자가 짧을 때 왼쪽 빈 공간에 채울 문자), rpad(칼럼, 총 몇 자리 문자로 만들 건지, 문자가 짧을 때 오른쪽 빈 공간에 채울 문자)
select dname as 원본, trim(dname) as trim_, ltrim(dname) as ltrim_, rtrim(dname) as rtrim_ from dept; # trim(칼럼) ; 앞뒤 공백 제거, ltrim(칼럼) ; 왼쪽 공백 제거, rtrim() ; 오른쪽 공백 제거
select dname as 원본, replace(dname, 'O', '$') as replace_ from dept; # replace(칼럼, 바꾸길 원하는 문자, 어떤 문자로 바꿀 건지) ; 바꾸길 원하는 문자 전부를 변경함 - 예) OPERATIONS => $PERATI$NS


# 숫자 함수
select round(112.3456, 1) as r1, round(112.3456, 2) as r2, round(112.3456, -1) as r3 from dual; # daul ; 임시 테이블, round(숫자 or 칼럼, 소수점 아래 몇 번째 자리까지 남겨 둘 건지) ; 반올림 // + 값 ; 소수점 아래 (오른쪽), - 값 ; 소수점 기준 왼쪽
select truncate(112.3456, 1) as t1, truncate(112.3456, 2) as t2, truncate(112.3456, -1) as t3 from dual; # daul ; 임시 테이블, truncate(숫자 or 칼럼, 소수점 아래 몇 번째 자리까지 남겨 둘 건지) ; 버리는 애 // + 값 ; 소수점 아래 (오른쪽), - 값 ; 소수점 기준 왼쪽
select mod(26, 3) as m1, mod(10, 9) as m2, mod(4, 2) as m3 from dual; # mod(숫자 or 칼럼, 나눌 값) ; 나누고 남은 나머지 값 반환 (a % b)
select ceil(26.3) as c1, ceil(10.9) as c2, ceil(-2.9) as c3, floor(26.3) as f1, floor(10.9) as f2, floor(-2.9) as f3 from dual; # ceil(숫자 or 칼럼) ; 주어진 값보다 큰 정수 중 가장 작은 정수, floor(숫자 or 칼럼) ; 주어진 값보다 작은 정수 중 가장 큰 정수 - 가우스 느낌 [x]
select power(5, 2) as p1, power(3, 3) as p2, power(11, 3) as p3 from dual; # power(숫자 or 칼럼, 몇 제곱)  


# 날짜 함수
select sysdate() as sysdate_ from dual; # systimestamp() 이건 오라클인 듯
# select add_month(sysdate(), 6) as add_month_, next_day(sysdate(), '일요일') as next_day, last_day(sysdate()) as last_day from dual; # 오라클인 듯
# select to_char(sysdate + 1/24/60/60, 'yyyy/mm/dd hh24:mi:ss') from dual; # 오라클인 듯 ; 1/24/60/60 ; 1초 <=> 1일 / 24시간 => 1시간 /60분 => 1분 / 60초 => 1초 // 1초를 더하고 난 후 저 형식으로 출력하라는 뜻


# group by
## having 이용해서 조건 추가 가능
## 집계 함수는 함께 다님
select deptno, avg(sal) as 평균_월급, sum(sal) as 전체_월급 from emp group by deptno; # deptno 기준으로 묶겠다
select deptno, avg(sal) as 평균_월급, sum(sal) as 전체_월급 from emp where deptno in (10, 30) group by deptno; # where : group by 연산 이전 데이터 필터링 처리 // from => where => group by => select 실행
select deptno, avg(sal) as 평균_월급, sum(sal) as 전체_월급 from emp group by deptno having avg(sal) >= 5000; # having : group by 연산 이후 조건 처리
select deptno, count(*) as cnt from emp group by deptno; # deptno 로 묶는데, 그에 해당하는 각 튜플 몇 개 있나?
select deptno, min(sal) as 최소_월급, max(sal) as 최대_월급 from emp group by deptno; # deptno 로 묶는데, 그에 묶인 튜플 중 최소, 최대 월급은?


# 분기문
# nvl : null 인 값 제거 함수
## nvl(칼럼 or 값, null 일 때 출력 값) : null 대신 다른 값 출력 가능
# nvl2 : 파라미터 nvl보다 하나 더 추가됨
## nvl2(칼럼 or 값, null 이 아닐 때 출력 값, null 일 때 출력 값)
# select empno, ename, nvl(comm, '해당 없음') as nvl_, nvl2(comm, '지급', '미지급') as nvl2_ from emp; # 오라클

# decode - 별도의 예시로 사용
## decode(칼럼, 조건 1, 결과 1, 조건 2, 결과 2, ..., default)
# select id, name, decode(bonus_type, 'AB', '1등급', 'AC', '2등급', '미대상자') as decode_ from salary; 

# case when
## case 칼럼 when ~ then ~ else (default) ~ end
# select id, name, case bonus_type when 'AB' then '1등급' when 'AC' then '2등급' else '미대상자' end as decode_ from salary; \
## case when 칼럼 = 조건 then ~ else (default) ~ end
# select id, name, case when bonus_type = 'AB' then '1등급' when bonus_type = 'AC' then '2등급' else '미대상자' end as decode_ from salary; 


# rownum : 오라클 - 출력 rows 설정 기능
## mssql : top() 대체
# select * from emp where rownum < 3
# select top(2) * from emp;
select * from emp limit 2; # mysql 은 이렇게 쓰면 됨

# rowid : 오라클에서 row 생성 시 부여되는 row 고유 id (데이터 객체 번호, 상대적 파일 번호, 블록 번호, 행 번호) 조합
# select rowid, * from emp;

# with 임시 테이블 이름 as (select ~ ) select ~
## 서브 쿼리 : 이거 먼저 실행하고 메인 쿼리 실행됨
with high_salary as (select ename, sal from emp where sal > 2000) select * from high_salary;
with high_salary as (select ename, sal from emp where sal > 2000), low_salary as (select ename, sal from emp where sal < 1000) select * from high_salary, low_salary; # 곱집합 (cartesian product) 됨 : high_salary의 모든 행과 low_salary의 모든 행을 모든 조합으로 연결
with high_salary as (select ename, sal from emp where sal > 2000), low_salary as (select ename, sal from emp where sal < 1000) select * from high_salary union all select * from low_salary; # union all : 수직으로 합친다 (중복 제거 x) => 단순히 행 이어 붙임 // union : 중복 제거 o => 시간 걸릴 수 있음



# join : 데이터 증가할 수 있음 => 중복 데이터 있을 때 신중하게 사용해야 함

# cross join : 곱집합 // 조건 따로 설정 x
# select * from major cross join professor;

# inner join : 가장 보편적 join 방법
## inner 생략 가능 
## 두 테이블 간 매칭되는 모든 데이터 조합 중 특정 칼럼끼리 같은 값을 가진 것만 출력
# select * from major m inner join professor p on m.major_id = p.bl_major_id # major_id 가 같은 것만 쭈루룩 // 데이터 늘어날 일 거의 없는

# natural join : 두 테이블 간 동일한 이름을 갖는 모든 칼럼에 대해 자동으로 equi join 실행
## where 절에서 join 사용 // on 절 도는 using 절 사용 x
# select * from major m natural join professor p;

# outer join : 기준이 되는 한쪽은 매칭되는 값이 없어도 출력됨
## 동일한 값 x => null 출력
# 예시 student => (student_id, name, major_id) : (1, 철수, 101), (2, 영희, 102), (3, 민수, null)
# 예시 major => (major_id, major_name) : (101, 컴공), (103, 정통)
# left : from 뒤에 오는 테이블 기준 - 해당 테이블의 모든 행 무조건 포함
# right : join 뒤에 오는 테이블 기준 - 해당 테이블의 모든 행 무조건 포함
# select s.name, m.major_name from student s left outer join major m on s.major_id = m.major_id; # student : 왼쪽, major 오른쪽 => 왼쪽 기준 join => major 테이블과 연결되는 값 있으면 붙이고, 아니면 major 쪽 칼럼 null (즉, m.major_name 의 값이 null 처리)
## 결과 : (name, major_name) : (철수, 컴공), (영희, null : 102에 대한 과목 정보가 major 에 없음 => null), (민수, null : 민수는 major_id 값이 null 이었음) // 모든 학생 포함됨
## 연결된다 : on s.major_id = m.major_id; 여기서 s.major_id 와 m.major_id 의 값이 같은 행끼리 연결 => 철수와 컴공이 연결됨, s.major 에 해당하는 값이 m.major_id 에 없으면 연결 x => null
# select s.name, m.major_name from student s right outer join major m on s.major_id = m.major_id; # major 기준 join => student 테이블과 연결되는 값 있으면 붙이고, 아니면 student 쪽 칼럼 null (즉, s.name 의 값이 null)
## 결과 : (name, major_name) : (철수, 컴공), (null, 정통) // 모든 전공 포함됨
# select s.name, m.major_name from student s full outer join major m on s.major_id = m.major_id; # 전부 연결
## 결과 : (name, major_name) : (철수, 컴공), (영희, null), (민수, null), (null, 정통)
## 양쪽 모든 행이 포함되어야 함

# on : 동일하지 않은 이름을 가진 칼럼끼리 join 조건에 사용 가능하도록 처리
## where 와 혼용 사용 가능
# select * from major m inner join professor p on m.major_id = p.bl_major_id; # 두 칼럼 이름 다를 때 명시적으로 사용

# using - 예시 그냥 다른 걸로 함
## 두 테이블에 존재하는 공통된 컬럼명 사용 && 데이터 유형 일치
# select * from major m inner join professor p using(major_id); # 둘 다 major_id 있어야 using 쓸 수 있음 // 두 테이블 다 major_id 라는 칼럼이 있어야 하고, inner join 이라서 두 테이블에서 major_id 가 동일한 튜플만 추출해서 join 처리
 

# 서브 쿼리 (sub query)
# select : 스칼라 서브 쿼리
## 성능 매우 불리
# select name as 학생_이름, (select major_name from major m where m.major_id = s.major_id) as 학과명 from student s;

# from : 인라인뷰, 동적 뷰
## sql 핵심 
# select s.name as 학생_이름, m.major_name as 학과명 from student s, (select major_name, major_id from major) m where s.major_id = m.major_id;
# select * from (select major_id, count(*) cnt from student group by major_id);

# where : 중첩 서브 쿼리, 서브 쿼리
# exists, not exists : 서브 쿼리의 결과가 존재 => 메인 쿼리의 결과가 출력됨
## on 사용 불가
# select * from student s where exists (select 1 from major m where m.major_id = s.major_id); # 1 대신 문자나 다른 숫자 넣어도 됨 : exists 는 존재하는지 아닌지만 판단함 => m.major_id = s.major_id 존재 => true, 존재 x => false
## 결과 : (name, major_id) : (철수, 101) // true 나온 튜플만 출력함
# select * from student s where not exists (select 1 from major m where m.major_id = s.major_id); # m.major_id = s.major_id 존재 x => true, 존재 o => false
## 결과 : (name, major_id) : (영희, 102), (철수, null) 

# 등가 조인 : a.id = b.id // 정확히 일치하는 값 연결
# 비등가 조인 : on a.value between b.min and b.max // 범위나 조건 기반 연결

# 비등가 조인 : 조인할 때 무조건 등가 조인? x
## 특정 범위나 같지 않다는 조건으로도 조인 가능
# 예시 student : (name, score) : (철수, 85), (영희, 72), (민수, 90)
# 예시 major : (major_name, min_score, max_score) : (컴공, 80, 100), (정통, 70, 79)
# select s.name, s.score, m.major_name from student s join major m on s.score between m.min_score and m.max_score;
## 결과 : (name, score, major_name) : (철수, 85, 컴공), (영희, 72, 정통), (민수, 90, 컴공)
## 조건 : on s.score between m.min_score and m.max_score => 이거 만족하면 연결됨 => 철수, 민수는 80 <= score <= 100 이므로 컴공, 영희는 70 <= score <= 79 이므로 정통
## 참고 : between a and b => [a, b] 닫힌 구간


# join : 두 개의 테이블을 하나로 만들 때 좌우로 붙이는 개념
# 집합 연산자 : row 단위로 데이터를 위아래로 붙이는 개념
# 두 개 이상의 select 결과를 하나로 만들어 줌
# select 의 칼럼 수 동일, 데이터 타입 호환되어야 함



# 계층형 sql : 한 테이블 안에 계층적인 데이터 구조를 가진 테이블에서 쉽게 데이터를 출력하기 위한 sql 문법
# select 컬럼명 from 테이블 where 조건절 start with 시작 조건 connect by [nocycle] prior 관계 방향 order [siblings] by 정렬 조건

