<h1>우연히 마주친 우리의 인연, 소개팅 앱 Wooyeon</h1>


- 담당 브랜치 : feature/join(전체), feature/login(USER_ROLES 등 일부 구현)


# 기술스택
### 백엔드
- Java
- SpringBoot (gradle, 2.7.x)
- JPA
- MySQL
- Github
- GCP(Google Cloud Platform)
- GCS(Google Cloud Storage)
- AWS RDS
- AWS lightsail (S3기반의 AWS lightsail bucket도 사용)
- Docker
- Jenkins

### 프론트엔드
- Dart
- Flutter

---
# 담당 역할 및 기능

- **회원가입 기획 및 설계**
    - 회원가입 절차와 필요한 테이블 설계, 프론트엔드와 소통에 필요한 API 명세서까지 모두 직접 기획하고 작성하였습니다. 이 과정에서 산출물로 기능/비기능 요구사항 명세서, API 명세서, ERD, 회의록을 산출하였습니다.
    - 회원가입 절차에서 사용할 오픈 API를 직접 선택하고, 공식 레퍼런스와 인터넷 글을 참고하며 사용하였습니다. (예 : 휴대폰 SMS 인증 시, 네이버클라우드의 SMS API 사용)
    - 회원가입의 절차는 아래와 같이 이루어집니다.
        - 사용자가 이메일 입력
        - 인증 이메일의 인증하기 버튼을 눌러 이메일 인증 (Gmail SMTP 사용)
        - 서버에서 사용자의 이메일 인증 여부를 확인하고 SSE를 이용하여 프론트엔드에게 인증 여부 전송
            - 미인증 시, 프론트엔드에서 재전송 요청
            - 인증완료 시, 비밀번호 입력창으로 이동
        - 비밀번호 입력 (암호화하여 전송)
        - 암호화된 비밀번호를 복호화한 후, passwordEncoder를 사용하여 암호화 후 USER 테이블에 저장
        - 로그인
        - 로그인 후 사용자의 프로필 등록 여부에 따라 화면 전환
            - 등록된 경우 메인화면으로 이동
            - 미등록시 프로필 등록 진행 후 메인화면으로 이동
- **회원가입 절차에 사용할 테이블 설계**
    - 테이블 생성 시, 모두 클래스에서 @Entity 어노테이션을 붙여 생성 했습니다. JPA 방식을 통해 연관관계를 설정하는 과정이 조금 어려웠으나, 인터넷에서 정보를 찾고, 김영한 강사님의 JPA 책을 보며 공부하였습니다.
    - 회원가입 절차에 사용된 테이블은 아래와 같습니다.
        - 이메일 인증(emailAuth)
        - 휴대폰 인증(phoneAuth)
        - 회원(User)
        - 프로필(Profile)
        - 프로필 이미지(Profile Image)
        - 사용자 권한(User Roles)
    - 테이블들은 아래와 같은 연관관계를 가지고 있습니다.
        - USER - Profile (1:1, 주테이블 Profile, user보다 profile을 접근하는 일이 많아 주테이블을 profile로 정했습니다. profile 테이블에서 user의 PK를 받아옵니다)
        - Profile - Profile Image (1:N)
        - USER - USER_ROLES (1:1)
- **API 명세서 작성**
    - 프론트엔드와 소통에 필요한 API 명세서를 작성하였습니다. 이 과정에서 Restful API에 대해 공부하였으며, 공부한 것을 토대로 Restful한 API 명세서를 작성하려 노력하였습니다.
- **이메일 인증 (Gmail SMTP)**
    - 사용자의 이메일 인증 요청 시, 프론트엔드에서 사용자의 이메일을 /auth/email(POST)로 전송합니다.
    - 백엔드에서 해당 사용자의 이메일의 존재 여부와 상태값을 판별합니다.
        - 이메일 인증 DB(emailAuth)에 존재하는지, 존재한다면 가입된 회원인지, 만약 미가입 회원인지 판별합니다.
        - 만약 미가입회원이라면 상태값이 인증된 상태인지, 미인증된 상태인지 판별합니다.
    - 만약 미가입된 회원이며, 이메일인증 DB에 해당 이메일이 존재하지 않을 경우 Gmail SMTP를 사용하여 사용자에게 인증코드를 포함한 이메일을 보냅니다.
        - Thymleaf를 사용해 이메일에 로컬 이미지 첨부 [↗️](https://easyoungcode.tistory.com/entry/Spring-Spring%EC%97%90%EC%84%9C-SMTP%EB%A1%9C-html-templateimage-%ED%8F%AC%ED%95%A8-%ED%8F%AC%ED%95%A8%ED%95%9C-%EC%9D%B8%EC%A6%9D-%EB%A9%94%EC%9D%BC-%EB%B3%B4%EB%82%B4%EA%B8%B0JAVA)
    - emailAuth 테이블엔 사용자의 이메일과 개인 별로 고유하게 부여되는 인증코드(UUID), 만료시간, 인증여부를 저장합니다.
    - 사용자가 이메일의 인증하기 버튼을 누르면 이메일인증 API를 통해 서버로 인증여부가 전송됩니다. (도메인주소/사용자를 식별할 수 있는 정보)
    - 백엔드에서 이메일 인증을 확인한 후, 프론트엔드에게 SSE를 통해 인증여부를 전송합니다.
- **NAVER SMS API를 사용한 휴대폰 인증 [↗️](https://easyoungcode.tistory.com/entry/Spring-%EB%84%A4%EC%9D%B4%EB%B2%84-%ED%81%B4%EB%9D%BC%EC%9A%B0%EB%93%9C-SMS-API-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)**
    - 사용자가 휴대폰 번호를 입력한 후 인증번호 발송 버튼을 클릭합니다.
    - 숫자로 구성된 6자리 난수 인증코드를 SMS API를 이용해 사용자에게 전송합니다.
    - 사용자가 문자 메시지로 송신된 인증코드를 입력합니다. (프론트엔드에서 문자로 송신된 코드를 자동으로 가져와 입력되도록 했습니다)
- **비밀번호 암호화**
    - 회원가입 절차 중 프론트엔드와 비밀번호를 주고 받을 때 RSA와 AES 암호화를 같이 사용하는 하이브리드 암호화를 사용하였습니다.
    - RSA(비대칭키 암호화 알고리즘)의 경우, 공개키와 개인키로 이루어져 있어 보안성으로는 AES(대칭키 암호화 알고리즘)보다 뛰어나지만, AES에 비해 암/복호화 시간이 많이 걸린다는 단점이 있습니다. 이 점을 극복하기 위하여 두 암호화 알고리즘을 같이 사용하는 하이브리도 암호화 알고리즘을 사용하였습니다.
        - RSA, AES를 이용한 암호화(개념) : **[↗️](https://easyoungcode.tistory.com/entry/%ED%95%98%EC%9D%B4%EB%B8%8C%EB%A6%AC%EB%93%9C-%EC%95%94%ED%98%B8%ED%99%94-RSA-AES-1-%EA%B0%9C%EB%85%90-%EC%84%A4%EB%AA%85)**
        - RSA, AES를 이용한 암호화(구현) : **[↗️](https://easyoungcode.tistory.com/entry/%ED%95%98%EC%9D%B4%EB%B8%8C%EB%A6%AC%EB%93%9C-%EC%95%94%ED%98%B8%ED%99%94-RSA-AES-2-%EA%B5%AC%ED%98%84)**
    - 비밀번호 암호화 과정은 다음과 같습니다.
        1. 백엔드에서 RSA key pair 생성
        2. 프론트엔드로 RSA public key(공개키) 전송
        3. 프론트엔드에서 사용자에게 비밀번호를 받아서 AES128/CBC 방식으로 암호화 (encryptedPassword)
        4. AES key와 iv(CBC 방식에 필요)를 합쳐 백엔드에게 받은 RSA public key로 암호화 (encryptedKey) -> 여기서 aes key를 세션키라고도 부른다.
        5. 백엔드에게 암호화된 비밀번호와 암호화된 key 전달
        6. 백엔드는 암호화된 키(encryptedKey)를 RSA secret key(개인키)로 복호화
        7. 복호화된 평문(key)에서 AES key 추출
        8. 암호화된 비밀번호를 AES key와 iv를 통해 복호화
        복호화된 평문(password)에 salt 추가 후, SHA256으로 암호화 (salt는 rainbow 테이블 방지하기 위해 사용)
- **프로필 등록**
    - 회원가입 후, 로그인 시 사용자의 프로필 등록 여부를 프론트엔드에게 반환해줬습니다.
    - 만약 프로필이 등록되어 있지 않다면 프로필 등록을 진행합니다.
    - API 명세서에 따른 프로필 등록 API를 통해 프론트엔드에게 사용자의 프로필 정보와 사진을 받습니다.
    - JPA repository를 이용하여 DB에 저장합니다.
- **GCS(Google Cloud Storage)를 이용한 프로필 사진 등록 [↗️](https://easyoungcode.tistory.com/entry/API로-MultipartFile이미지-파일과-JSON-받아서-업로드-및-포스트맨으로-테스트-해보기-feat-Spring-flutter)**
- **AWS lightsail bucket(S3 기반)을 이용한 프로필 사진 등록**
- **AWS lightsail 방화벽 설정 및 도메인 연결**
    - 방화벽 설정 **[↗️](https://easyoungcode.tistory.com/entry/AWS-aws-lightsail-%EA%B3%A0%EC%A0%95-IP-%EC%83%9D%EC%84%B1-%ED%9B%84-%EB%B0%A9%ED%99%94%EB%B2%BD-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)** : 팀원들이 서버에 접속할 수 있도록 방화벽을 설정하였습니다.
    - 도메인 연결 **[↗️](https://easyoungcode.tistory.com/entry/AWS-무료-도메인-생성-후-aws-lightsail에-연결해주기)** : ip가 아닌 도메인으로 접속할 수 있도록 도메인을 발급받아 aws lightsail과 연결해주었습니다.
- **서버배포 - AWS lightsail과 Docker를 이용한 배포 [↗️](https://easyoungcode.tistory.com/entry/SpringBootDockerAWS-lightsail로-애플리케이션-서버에-배포하기feat-m1?category=1227065)**
- **CI/CD (1) - Jenkins pipeline**
    - Jenkins의 pipeline script를 작성하였습니다.
- **CI/CD (2) - Docker 설정**
- **CI/CD (3) - git webhook과 연결**
