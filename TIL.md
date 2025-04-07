■ 25.03.26(수) Today I Learned (TIL)

1. Cloud SQL 연결 시 “docker-compose” 자동 실행 문제
    - Spring Boot 3.4+에선 프로젝트 루트/상위 폴더에 있는 docker-compose.yml을 자동 감지해 Docker Compose를 구동하려고 시도한다.
    - 이를 피하려면 spring-boot-docker-compose 의존성을 제거하거나, spring.docker.compose.enabled=false 설정 등을 통해 비활성화할 수 있다.
2. Cloud SQL 외부 접속 시 “공개 IP + Authorized networks”
    - 로컬 PC가 NAT로 나가는 “공인 IP”를 Cloud SQL의 Authorized networks에 등록해야 함.
    - ipconfig 등에 나오는 192.168.x.x, 172.25.x.x 같은 사설 IP로는 등록 불가. 실제 NAT된 공인 IP(/32)를 등록해야 한다.
3. “The Application Default Credentials are not available” 오류
    - 구글 “Application Default Credentials(ADC)”를 찾지 못해 발생.
    - 로컬에서 Cloud SQL 소켓 팩토리 사용 시, GOOGLE_APPLICATION_CREDENTIALS 환경 변수로 서비스 계정 키(.json) 경로를 지정해야 함.
4. DB Connector dependencies 충돌
    - `*runtimeOnly*("com.mysql:mysql-connector-j")`
    - `*runtimeOnly*("com.h2database:h2")`
    - 두개의 DB Connector 이 설정되어 db 연결 시 table을 찾지 못하는 문제 발생, db connection url에 jdbc:mysql://로 명시해도 h2에 연결 시도하는 문제가 있었음
    - `*testRuntimeOnly*("com.h2database:h2")`로 변경하여 문제 해결


■ 25.04.02(화) Today I Learned (TIL)

1. DDD 레이어드 아키텍처
    - Domain, Application, Infrastructure, Interfaces로 구분해 각 계층 책임을 명확히 함.
    - Domain엔 순수한 모델(User 등)과 포트(Repository 인터페이스)만 두고, Infrastructure에 실제 구현체(JpaRepository 등)를 둬 결합도를 낮춤.
2. JPA 엔티티 분리
    - 도메인 모델(User)에서 JPA 어노테이션을 걷어내고, Infrastructure 레이어에 별도 Entity(UserEntity)를 두면 도메인 순수성이 높아짐.
    - Mapper(혹은 확장 함수)를 통해 Domain ↔ Entity를 상호 변환.
3. Controller와 DTO 구분
    - Controller는 REST 엔드포인트 로직만 담당하고, DTO는 요청·응답 데이터 전달 전용으로 분리.
    - interfaces.auth.controller / interfaces.auth.dto 식으로 패키지를 명확히 구분해 가독성과 유지보수성 향상.
4. Jpa Entity, Domain Entity 분리 타이밍?
    - 대규모 프로젝트나 스토리지 교체 가능성이 있을 때, 도메인 모델 독립성이 중요하다면 분리하는 편이 유리
    - 소규모 프로젝트에선 굳이 분리하지 않고 단순화하는 것도 가능.
5. Kotlin은 신인가
    - 코틀린을 안쓰고 Java를 사용한다면 보일러 플레이트가 상당할 듯 싶다.
    - 특히 `UserMapper.kt`처럼 명시적인 메퍼가 가능한게 장점인 듯 하다.

→ 이렇게 계층을 깔끔히 나누고 의존을 최소화하면, 확장성·유연성이 높은 아키텍처를 구축할 수 있다.


■ 25.04.06(월) Today I Learned (TIL)

1. 도메인 정의
   - Material(소재), AdGroup, Campaign 등 광고 도메인에 해당하는 모델들을 Domain 계층으로 정의.
   - Java로 작성되어 있던 클래스를 Kotlin의 data class로 변환하면서, Lombok(@Getter, @Setter 등)을 제거했고, 생성자 로직을 코틀린 문법에 맞춰 개선.
   - Link, Period, Target, TextNdescription, UserLock 등 다양한 도메인 모델에 대한 코틀린 데이터 클래스 생성.
   - JPA Entity는 infrastructure 레이어 내에 별도의 Entity를 만들어 도메인 모델과 분리할 예정.