# 2023-1-OSSP2-4ofUs-4
2023년, 1학기, 공개SW프로젝트, 02분반, 우리만4명이조, 4조

## <div><img src="https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=Co-kkiri&fontSize=90" /></div>

- - - - - - - - - - - - - - - -


## TEAM MEMBER
|이름|학과|역할|
|----|---|---|
|[강산아](https://github.com/gsandoo)|스포츠문화학|Backend|
|[김세훈](https://github.com/khoon9)|컴퓨터공학|Frontend|
|[나찬진](https://github.com/ckswls56)|컴퓨터공학|Backend|
|[문채영](https://github.com/bbabbi)|컴퓨터공학|팀장, Frontend|


- - - - - - - - - - - - - - - - - - - - - - - - - - - -

## FrameWork
<div>
    <img src="https://img.shields.io/badge/Vue.js-4FC08D?style=flat&logo=Vue.js&logoColor=white"/>
  	<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white" />
    <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white" />
</div>  

## Server
<div>
    <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat&logo=amazonec2&logoColor=white"/>
</div>

## Stack
<div>
    <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white"/>
    <img src="https://img.shields.io/badge/SCSS-1867C0?style=flat&logo=CSS3&logoColor=white"/>
    <img src="https://img.shields.io/badge/JS-7DF1E?style=flat&logo=jss&logoColor=white"/>
    <img src="https://img.shields.io/badge/JPA-6DB33F?style=flat&logo=Spring Boot&logoColor=white" />
</div> 

## OpenSource
<div>
    <img src="https://img.shields.io/badge/STMP-FC7E0F?style=flat&logo=SMTP&logoColor=white"/>
    <img src="https://img.shields.io/badge/IamPort-49BDA5?style=flat&logo=IamPort&logoColor=white"/>
    <img src="https://img.shields.io/badge/SSE-F43E37?style=flat&logo=SSE&logoColor=white"/>
    <img src="https://img.shields.io/badge/WebSocket-010101?style=flat&logo=socketdotio&logoColor=white"/>
</div>


## 1. 프로젝트 주제
<div>
<h4> Co-끼리(대학생 캠퍼스 네트워크 형성을 위한 익명 매칭 서비스)
</div>

## 2. 개요
<p>
        코로나19로 인하여 비대면 기간중에는 학생들간의 원활한 교류가 없어 인간관계가 형성되지 못하였다. 대학 생활을 하면서 인적 네트워크가 형성 되지 않는다면 편의시설 이용, 강의 수강 등  여러가지 불편함과 어려움을 느낄 수 있는 부분이 존재한다. 예를 들어 과제로 팀플을 하는 경우 혹은 사정으로 인하여 결석하는 경우 독강 을 하게 된다면 난감한 경우가 종종 있다. 해당 서비스는 이러한 교류로 인한 불이익을 최소화 하고자 기획하게 되었다.
정리하자면, 이 서비스는 동국대 학우들의 익명성을 보장하며 서로 매칭시켜주는 서비스이다.

</p>


## 3. 프로젝트 구조도

![image](https://github.com/CSID-DGU/2023-1-OSSP2-4ofUs-4/assets/98865571/d9b5fcb4-a582-4026-8b10-3d1e0a7d02b6)


## 3. 스크린 샷

![image](https://github.com/CSID-DGU/2023-1-OSSP2-4ofUs-4/assets/98865571/2d159f13-3367-4672-966b-033666c51681)
![image](https://github.com/CSID-DGU/2023-1-OSSP2-4ofUs-4/assets/98865571/eb39988a-7837-4d56-8a78-5c44343be0e2)
![image](https://github.com/CSID-DGU/2023-1-OSSP2-4ofUs-4/assets/98865571/1cb05a9a-abcb-4293-b739-138de4f9b53c)
![image](https://github.com/CSID-DGU/2023-1-OSSP2-4ofUs-4/assets/98865571/0e0b226b-5607-4cbb-933e-f19451d6d23f)
![image](https://github.com/CSID-DGU/2023-1-OSSP2-4ofUs-4/assets/98865571/d7faa440-4ed0-4643-9196-84159ba47cc8)
![image](https://github.com/CSID-DGU/2023-1-OSSP2-4ofUs-4/assets/98865571/653836ea-cb5f-4bbd-826f-1b9c77dfa780)
![image](https://github.com/CSID-DGU/2023-1-OSSP2-4ofUs-4/assets/98865571/feeb9b58-eb8c-41a2-87e3-c1585806dc7e)


## 4. 사용 라이브러리

``` 
<dependencies>
		<dependency>
			<groupId>com.github.iamport</groupId>
			<artifactId>iamport-rest-client-java</artifactId>
			<version>0.2.22</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.jetbrains.kotlin</groupId>
			<artifactId>kotlin-stdlib</artifactId>
			<version>1.7.10</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/javax.mail/mail -->
		<dependency>
			<groupId>com.sun.mail</groupId>
			<artifactId>javax.mail</artifactId>
			<version>1.6.2</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework/spring-context-support -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
			<version>5.3.9</version>
		</dependency>

		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi</artifactId>
			<version>5.0.0</version>
		</dependency>
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml</artifactId>
			<version>5.0.0</version>
		</dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-messaging</artifactId>
            <version>6.0.7</version>
        </dependency>
    </dependencies>    
    
```


--------------------------------------------------------------------------------------------
  ### github 키워드

|keyword|설명|
|----|---|
|Feat|새로운 기능 추가|
|Fix|버그 수정|
|Design|CSS 등 사용자 UI 디자인 변경|
|!BREAKING CHANGE|커다란 API 변경의 경우|
|!HOTFIX|급하게 치명적인 버그를 고쳐야하는 경우|
|Docs|문서 수정|
|Style|코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우|
|Comment|필요한 주석 추가 및 변경|
|Refactor|코드 리펙토링|
|Test|test code, refactoring test code 추가|
|Chore|build 업무 수정, 패키지 매니저 수정|
|Rename|파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우|
|Remove|파일을 삭제하는 작업만 수행한 경우|
