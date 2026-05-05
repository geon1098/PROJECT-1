# 🍷 주류 정보 사이트 (UnpreProjectFirst)

> 와인 / 위스키 / 맥주 정보를 모아서 보고, 카테고리별 커뮤니티에서
> 사용자끼리 후기를 공유할 수 있는 웹사이트입니다.

학교 팀 프로젝트로 만든 첫 번째 풀스택 웹 프로젝트예요.
Spring MVC + MyBatis + JSP 로 직접 구현하면서 백엔드 흐름을
처음부터 끝까지 손으로 따라가본 게 가장 큰 수확이었어요.

<br>

## 📌 프로젝트 개요

- **개발 기간** : 약 4개월 (팀 프로젝트)
- **참여 인원** : 백엔드 2명 / 프론트 2명 / 디자인 1명
- **본인 역할** : 백엔드 (회원, 게시판, 검색, 주류 정보 도메인)
- **결과물 형태** : Tomcat 위에 올리는 WAR 패키지 웹 애플리케이션

<br>

## 🛠 사용한 기술 스택 (pom.xml 기반 실측)

| 구분 | 기술 / 버전 |
| --- | --- |
| Language | Java |
| Framework | Spring Framework 5.2.5 (Spring MVC) |
| Persistence | MyBatis 3.5.4 + mybatis-spring 2.0.4 |
| DB | MySQL 8.0.19 (mysql-connector-java) |
| Connection Pool | Apache Commons DBCP2 2.7.0 |
| View | JSP + JSTL 1.2 |
| 빌드 / 패키징 | Maven, WAR |
| 로깅 | SLF4J 1.6.6 + log4j 1.2.15 + log4jdbc-log4j2 |
| 테스트 | JUnit 4.7 |
| 프론트 (참고) | HTML5 / CSS3 / JavaScript / Bootstrap |

> ※ Spring **Boot** 가 아니라 Spring **Framework** 5 입니다.
> 외부 Tomcat 컨테이너에 배포해서 띄우는 전통적인 WAR 구조예요.

<br>

## 🗂 프로젝트 구조

```
src/main/java/unpre/project/first/
├── MainController.java           // 메인 페이지 진입
├── UserController.java           // 회원가입 / 로그인 / 로그아웃 / 마이페이지
├── UserService(Impl).java
├── UserDAO.java
├── adwrite/                      // 관리자 게시판 (adboard)
│   ├── AdWriteController.java
│   ├── AdWriteService(Impl).java
│   └── AdWriteDao.java
├── item/                         // 주류 정보 상세
│   ├── ItemController.java
│   ├── ItemService(Impl).java
│   └── ItemDao.java
├── search/                       // 검색
│   ├── SearchController.java
│   ├── SearchService(Impl).java
│   └── SearchDAO.java
├── write/                        // 일반 게시판 (board)
│   ├── WriteController.java
│   ├── WriteService(Impl).java
│   └── WriteDao.java
└── Bar/
    └── BarController.java        // Bar 정적 페이지

src/main/resources/sqlmap/
├── user_db_SQL.xml               // user 테이블 쿼리
├── board_db_SQL.xml              // board 테이블 쿼리
├── adwrite_SQL.xml               // adboard 테이블 쿼리
└── item_db_SQL.xml               // item 테이블 쿼리

src/main/webapp/WEB-INF/views/main/
├── main.jsp, search.jsp
├── SignUp/, Login/, MyPage/
├── Community/                    // 게시판 JSP (list, detail, write, update)
├── Item/                         // 주류 상세 JSP (wine/whisky/beer/introduce)
├── AdminBoard/                   // 관리자 게시판 JSP
└── Bar/                          // bar.jsp, bar2.jsp
```

<br>

## 🔑 REST API 설계

### 1. 회원 (UserController)

| 기능 | 메서드 / URL | 설명 |
| --- | --- | --- |
| 회원가입 화면 | `GET /signup` | `signup.jsp` 반환 |
| 회원가입 처리 | `POST /signup.do` | `user_insert` (user_id, pwd, user_name, user_tel, email, nickname) |
| 로그인 화면 | `GET /login` | `login.jsp` 반환 |
| 로그인 처리 | `POST /userlogin.do` | `login_check` 쿼리 → 일치 시 `HttpSession` 에 `signIn` 으로 사용자 정보 저장 |
| 로그인 실패 | `GET /login_fail` | `login_fail.jsp` 반환 |
| 로그아웃 | `GET /logout.do` | `session.invalidate()` |
| 마이페이지 | `GET /mypage` | `mypage.jsp` |
| 정보 수정 화면 | `GET /mypagechange` | `mypagechange.jsp` |
| 정보 수정 처리 | `POST /mypageupdate.do` | `user_update` + `update_myboard_nickname` 두 쿼리 실행 후 세션 갱신 |
| 내 게시글 목록 | `GET /mypageboard` | 내가 쓴 글만 `where user_id = #{user_id}` 로 조회 |

> 🔎 인증 방식은 **HttpSession 기반** 이에요. JWT나 Spring Security는 도입하지 않았습니다.

<br>

### 2. 일반 게시판 (WriteController)

| 기능 | 메서드 / URL | 설명 |
| --- | --- | --- |
| 작성 화면 | `GET /write` | |
| 작성 처리 | `POST /write.do` | `board_insert`. 성공 시 `HttpSession` 의 `myboarddata` 갱신하고 상세로 redirect |
| 상세 조회 | `GET /detail?bNum=` | `select_detail` |
| 수정 화면 | `GET /update?bNum=` | |
| 수정 처리 | `POST /update` | `update_board` |
| 삭제 처리 | `POST /delete` | `delete_board`. 성공 시 목록, 실패 시 상세로 redirect |
| 전체 목록 | `GET /list` | `select_list` (검색 포함) |
| 와인 목록 | `GET /list_wine` | 동일한 `select_list` 호출 (뷰만 다름) |
| 맥주 목록 | `GET /list_beer` | 동일 |
| 위스키 목록 | `GET /list_whisky` | 동일 |

검색 쿼리는 MyBatis `<if>` 로 키워드 유무에 따라 동적으로 붙여요.

```xml
<select id="select_list" parameterType="hashMap" resultType="hashMap">
   select nickname, b_num, b_title, category, cdate, b_content
   from board b
   where 1=1
   <if test="keyword != null and keyword != ''">
      and (category like CONCAT('%',#{keyword},'%')
        or b_title  like CONCAT('%',#{keyword},'%')
        or b_content like CONCAT('%',#{keyword},'%')
        or user_id  like CONCAT('%',#{keyword},'%')
        or nickname like CONCAT('%',#{keyword},'%'))
   </if>
   order by b_num desc
</select>
```

<br>

### 3. 관리자 게시판 (AdWriteController)

`adboard` 테이블을 따로 두고, 일반 게시판과 분리된 별도 CRUD 를 가집니다.

| 기능 | 메서드 / URL | 쿼리 |
| --- | --- | --- |
| 작성 화면 / 처리 | `GET·POST /adwrite` | `adwrite_insert` |
| 상세 | `GET /Admin_detail?adbNum=` | `adselect_detail` |
| 수정 화면 / 처리 | `GET·POST /adupdate` | `adupdate` |
| 삭제 | `POST /addelete` | `addelete` |
| 목록 | `GET /adlist` | `adselect_list` (제목 / 내용 / 카테고리 LIKE 검색) |

<br>

### 4. 검색 (SearchController)

| 기능 | URL | 설명 |
| --- | --- | --- |
| 통합 검색 | `GET /search?keyword=` | `searchList` + `nicknameDistinct` 두 쿼리 결과를 같이 모델에 담아 `search.jsp` 렌더링 |

<br>

### 5. 주류 정보 (ItemController)

`item` 테이블에 주류 마스터 데이터(이름 / 도수 / 원산지 / 종류 / 출시년도 / 소개 / 제조지 / 이미지 등)를 저장하고 상세 페이지로 보여줍니다.

| 기능 | URL | 설명 |
| --- | --- | --- |
| 상세 페이지 | `GET /introduce?itemNum=` | `select_introduce` 로 단건 조회 |
| 와인 목록 | `GET /winelist` | `wineList.jsp` (정적 목록 페이지) |
| 위스키 목록 | `GET /whiskylist` | `whiskyList.jsp` |
| 맥주 목록 | `GET /beerlist` | `beerList.jsp` |

> ⚠️ ItemController 의 등록(POST) 코드는 주석 처리되어 있어서, **신규 등록은 DB에 직접 INSERT** 하는 방식이에요. 운영 화면에서 추가하는 기능은 없습니다.

<br>

### 6. Bar 페이지 (BarController)

`/bar`, `/bar2` 두 개의 정적 JSP 페이지로, 유명 바 / SNS 링크를 모아둔 화면입니다. (DB 연동 없음)

<br>

## 💾 DB 테이블

코드 / SQL 매퍼에서 확인되는 테이블은 다음 4개입니다.

| 테이블 | 주요 컬럼 |
| --- | --- |
| `user` | user_id (PK), pwd, user_name, user_tel, email, nickname |
| `board` | b_num (PK, AUTO_INCREMENT), b_title, b_content, category, cdate, user_id, nickname |
| `adboard` | adb_num (PK, AUTO_INCREMENT), adb_title, adb_content, ad_category, adb_cdate, user_id |
| `item` | item_num (PK), item_name, b_category, s_category, proof, origin, race, p_year, i_introduce, m_place, img |

<br>

## 💡 기술적 의사결정

> 처음 학교 수업에서 Spring 배울 때 "왜 굳이 이걸 써?" 라는 의문이 많았는데,
> 직접 프로젝트에 적용해보면서 그제야 이유를 알게 됐어요.
> 지금 시점에서 다시 보면 더 좋은 선택지도 있지만, 그때 왜 이렇게 정했는지 정리해봤습니다.

| 사용 기술 | 선택한 이유 |
| --- | --- |
| **Spring MVC** | 학교 커리큘럼에서 다룬 표준 프레임워크였고, `@Controller / @Service / @Autowired` 같이 책임이 분리된 구조를 직접 코드로 익히고 싶어서 선택했습니다. |
| **MyBatis** | JPA를 아직 다뤄본 적이 없었고, SQL을 직접 다룰 줄 알아야 한다고 생각했어요. 키워드가 있을 때만 검색 조건을 붙이는 동적 쿼리(`<if>`)를 XML 한 곳에서 관리할 수 있는 점이 편했습니다. |
| **MySQL** | 가장 자료가 많고 무료라서 학습용으로 가장 익숙했고, 팀원 모두가 SQL을 처음 다뤄보는 입장이었기 때문에 합의가 가장 빨랐습니다. |
| **JSP + JSTL** | 서버에서 렌더링까지 끝내는 전통적인 흐름을 직접 경험해보고 싶었어요. `request / session` 객체에 담긴 값이 화면까지 어떻게 전달되는지 흐름이 한눈에 보였습니다. |
| **HttpSession 기반 인증** | 첫 프로젝트라서 토큰 인증 구조까지는 부담스러웠어요. 우선 `session.setAttribute("signIn", userInfo)` 로 간단히 로그인 유지를 구현하고, 추후 JWT 학습 후에 마이그레이션하기로 팀에서 합의했습니다. |
| **Commons DBCP2** | 매 요청마다 커넥션을 새로 만들면 비용이 크다는 걸 수업에서 배워서, 커넥션 풀을 적용했습니다. Spring 설정 파일에서 `BasicDataSource` 한 줄로 끝나서 학습용으로 적당했어요. |
| **log4j + log4jdbc** | 단순 SLF4J 로그뿐 아니라 실제로 DB에 어떤 SQL 이 어떤 파라미터로 나가는지 보고 싶어서 `log4jdbc` 를 같이 붙였습니다. 콘솔에서 쿼리를 직접 보면서 디버깅한 경험이 가장 많이 도움이 됐어요. |
| **Maven (WAR)** | 외부 Tomcat 에 배포하는 학교 실습 환경에 맞추기 위해 WAR 패키징을 선택했습니다. |

<br>

## 🚀 실행 방법

### 사전 준비물

| 항목 | 권장 버전 | 비고 |
| --- | --- | --- |
| JDK | 8 ~ 11 | `pom.xml` 의 `java-version` 은 11, `maven-compiler-plugin` source/target은 1.6 으로 잡혀있음 |
| Maven | 3.6 이상 | |
| MySQL | 8.0 | 로컬 또는 도커 |
| Tomcat | 8.5 ~ 9 | WAR 배포용 외부 컨테이너 |

### 1. 클론

```bash
git clone https://github.com/geon1098/PROJECT-1.git
cd PROJECT-1
```

### 2. DB 준비

`src/main/webapp/WEB-INF/spring/root-context.xml` 의 DB 설정은 다음과 같이 하드코딩되어 있습니다.

```
url      : jdbc:mysql://localhost:3306/project_db
username : root
password : 1234
```

로컬 MySQL 에 `project_db` 스키마를 만들어주세요.

```sql
CREATE DATABASE project_db DEFAULT CHARACTER SET utf8mb4;
USE project_db;
```

그리고 위 SQL 매퍼(`board_db_SQL.xml`, `user_db_SQL.xml`, `adwrite_SQL.xml`, `item_db_SQL.xml`) 에서
사용하는 컬럼에 맞게 테이블 4개(`user`, `board`, `adboard`, `item`)를 생성합니다.

> ⚠️ 비밀번호가 평문으로 저장되고, DB 비번이 XML 에 그대로 들어가 있어요.
> 학습용 프로젝트라 그대로 뒀지만, 운영 환경이라면 BCrypt + 환경변수로 빼야 합니다.

### 3. 빌드

```bash
mvn clean package
```

성공하면 `target/UnpreProjectFirst-1.0.0-BUILD-SNAPSHOT.war` 가 생성됩니다.

### 4. Tomcat 에 배포

생성된 WAR 파일을 `${TOMCAT_HOME}/webapps/` 로 복사하고 톰캣을 재시작합니다.
(Eclipse / IntelliJ 의 톰캣 플러그인을 써도 됩니다.)

### 5. 접속

```
http://localhost:8080/UnpreProjectFirst/main
```

(Context Path 를 `/` 로 바꿨다면 `http://localhost:8080/main`)

<br>

## 🔥 트러블슈팅

> 실제 프로젝트 진행 중에 코드/SQL 매퍼에 흔적이 남은 문제들 중심으로
> 정리했어요. 첫 팀 프로젝트라 기본기에서 막힌 게 많았는데,
> 그게 오히려 가장 오래 기억에 남아요.

---

### 1. 닉네임 변경했는데 이전 게시글에는 옛날 닉네임이 그대로 남는 문제

**상황**

마이페이지에서 닉네임을 바꿨는데, 게시판 목록에는 바꾸기 전 닉네임이
그대로 떠있었어요. 새로 글을 쓰면 새 닉네임으로 들어가는데, 옛날 글들은
변하지 않으니까 한 사람이 두 닉네임으로 글 쓴 것처럼 보이더라고요.

**원인**

`board` 테이블에 글을 쓸 때 작성자 `nickname` 을 컬럼으로 같이 박아넣고 있었어요.
즉 회원 정보(닉네임)가 게시글 row 에 비정규화되어 들어가 있는 상태였죠.
`user` 테이블만 update 하니까 이미 들어간 게시글은 옛 닉네임 그대로일 수밖에요.

**해결**

`UserServiceImpl#edit` 에서 사용자 정보 update 한 번, 그 사람이 쓴
게시글의 닉네임 update 한 번, 총 두 개의 쿼리를 같이 실행하도록 했어요.

```java
// UserServiceImpl.java
public boolean edit(Map<String, Object> map) {
    int userUpdateCount    = this.userdao.userupdate(map);
    int boardNicknameCount = this.userdao.updateBoardNickname(map);
    if ((userUpdateCount >= 1) && (boardNicknameCount >= 1)) {
        return true;
    }
    return false;
}
```

```xml
<!-- user_db_SQL.xml -->
<update id="user_update" parameterType="hashMap">
   update user set pwd = #{pwd}, user_tel = #{user_tel},
                   email = #{email}, nickname = #{nickname}
   where user_id = #{user_id}
</update>

<!-- board_db_SQL.xml -->
<update id="update_myboard_nickname" parameterType="hashMap">
   update board set nickname = #{nickname}
   where user_id = #{user_id};
</update>
```

**배운 점**

이게 사실 정규화의 문제였어요. `board` 에 `user_id` 만 두고 화면에 띄울 때
`user` 와 join 했으면 이런 동기화 자체가 필요 없었을 텐데, 처음 설계할 때
"join 한 번 더 하면 느려질 거 같은데?" 라는 어림짐작으로 닉네임을 같이 박았던 거죠.
정규화가 왜 중요한지 몸으로 느낀 케이스였어요.

---

### 2. 검색창 비워두고 검색하면 결과가 0건으로 나오던 문제

**상황**

처음에 검색 쿼리를 이렇게 짰어요.

```sql
where category like CONCAT('%', #{keyword}, '%')
```

근데 검색창을 비워둔 채 그냥 목록을 누르면 게시글이 한 개도 안 떴어요.
키워드가 빈 문자열일 때 `like '%%'` 가 모든 행에 매칭될 줄 알았는데,
실제로는 `keyword=null` 이 들어와서 `like '%null%'` 비교가 되니까
아무것도 안 나왔던 거예요.

**원인**

폼에서 키워드를 입력하지 않으면 파라미터가 아예 안 넘어와서 null 이 되는데,
그걸 LIKE 에 그대로 넣었던 게 문제였어요.

**해결**

MyBatis 의 `<if>` 동적 SQL 로 키워드가 있을 때만 검색 조건이 붙도록 분기.

```xml
<select id="select_list" parameterType="hashMap" resultType="hashMap">
   select nickname, b_num, b_title, category, cdate, b_content
   from board b
   where 1=1
   <if test="keyword != null and keyword != ''">
      and (category like CONCAT('%',#{keyword},'%')
        or b_title  like CONCAT('%',#{keyword},'%')
        or b_content like CONCAT('%',#{keyword},'%')
        or user_id  like CONCAT('%',#{keyword},'%')
        or nickname like CONCAT('%',#{keyword},'%'))
   </if>
   order by b_num desc
</select>
```

`where 1=1` 트릭은 처음 봤을 땐 좀 어색했는데, 동적 조건을 `and` 로
이어붙일 때 매번 첫 조건 여부 분기를 안 해도 되니까 진짜 편하더라고요.

---

### 3. 와인/위스키/맥주 카테고리별로 컨트롤러 메서드를 4개나 두는 게 맞나?

**상황**

처음에 카테고리별 게시판을 만들 때 `list_wine`, `list_beer`, `list_whisky`
URL 마다 컨트롤러 메서드를 따로 두고, 각각 `service.listWine()`,
`service.listBeer()`, `service.listWhisky()` 같은 메서드를 만들려고 했어요.
근데 결국 SQL 쿼리는 똑같고 카테고리만 다른 거였어서, 메서드를 4개나
만드는 게 너무 중복 같았어요.

**해결 (현재 코드 상태)**

컨트롤러는 URL 별로 메서드를 두되, **서비스 호출은 공통 `list(map)` 하나로 통일** 했어요.
View 이름만 카테고리별 JSP 로 다르게 매핑.

```java
// WriteController.java
@RequestMapping(value = "/list_wine")
public ModelAndView listwine(@RequestParam Map<String, Object> map) {
    List<Map<String, Object>> listwine = this.writeService.list(map);  // ← 공통
    ModelAndView mav = new ModelAndView();
    mav.addObject("data", listwine);
    if (map.containsKey("keyword")) {
        mav.addObject("keyword", map.get("keyword"));
    }
    mav.setViewName("main/Community/list_wine");                       // ← 뷰만 다름
    return mav;
}
```

**아쉬운 점 (지금 다시 보면)**

지금 보면 카테고리도 결국 `keyword` 에 넣어 LIKE 검색으로 처리하고 있어서,
URL 자체는 4개로 갈라놨지만 서버 입장에서는 사실상 차별화가 없어요.
다음에 다시 한다면 `/board/list?category=wine` 같이 단일 엔드포인트로
받고, 쿼리에서 `category = #{category}` 로 정확 매칭하는 방향이 더 깔끔할 것 같아요.
"코드 구조에 정답은 없지만, 중복은 항상 의심해보자" 라는 걸 배운 케이스였어요.

---

### 4. 한글 제목 / 내용이 `???` 로 들어가던 인코딩 문제

**상황**

게시글에 한글로 글을 쓰면 DB 에 `???` 로 들어갔어요. 영어는 멀쩡하고요.

**원인 / 해결**

총 세 군데에서 인코딩이 맞춰져야 한다는 걸 그제야 알았어요.

1. **JDBC URL** : `useUnicode=true&characterEncoding=UTF-8`
2. **DB / 테이블** : `DEFAULT CHARACTER SET utf8mb4`
3. **요청 인코딩** : `web.xml` 에 `CharacterEncodingFilter` 등록

```xml
<!-- root-context.xml -->
<property name="url"
  value="jdbc:mysql://localhost:3306/project_db?serverTimezone=Asia/Seoul&amp;useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8" />
```

**배운 점**

"인코딩은 맨 끝에 한 줄만 잘못돼도 다 깨진다" 가 어떤 느낌인지 몸으로 알게 됐어요.
그리고 클라이언트 ↔ WAS ↔ DB 사이에서 UTF-8 이 끊기지 않도록 모든 단계를
점검하는 습관을 그때부터 들이게 됐어요.

<br>

## 📝 한계 / 회고

**솔직하게, 지금 다시 보면 부족한 점이 많아요.**

- **비밀번호 평문 저장** — `pwd` 컬럼에 그대로 들어가요. BCrypt 같은 해시 적용이 필요합니다.
- **DB 비밀번호 하드코딩** — `root-context.xml` 에 그대로 박혀있어요. 환경변수 분리가 필요.
- **세션 인증** — 서버 인스턴스가 늘어나면 세션 공유 이슈가 생겨요.
- **N+1 / 페이징 부재** — 게시판이 단순 `select * order by` 라서 글이 많아지면 느려져요. `LIMIT / OFFSET` + 카테고리 정확 매칭 인덱스가 필요.
- **트랜잭션 처리 부족** — 정보 수정에서 `user` 와 `board` 두 update 가 하나의 트랜잭션이 아니에요. 한쪽만 실패하면 데이터가 어긋날 수 있습니다.
- **중복 컨트롤러 메서드** — 카테고리별 URL 4개가 사실상 같은 일을 해요.

이 프로젝트가 끝난 뒤에 위 한계들을 해결해보고 싶어서
**Spring Boot + JPA + Spring Security + JWT** 를 별도로 학습하고 있어요.
다음 프로젝트에서는 이 사이트를 베이스로 위 항목들을 하나씩 개선해나가는 게 목표입니다.

