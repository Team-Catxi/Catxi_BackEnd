# 백엔드 문서 안내

문서는 목적에 따라 세 곳으로 나눕니다. 포트폴리오 문서는 검증된 결과를 요약하고, 성능 문서는 실행 조건과 원자료를 보존하며, 아키텍처 문서는 실제 구성과 운영상 경계를 설명합니다.

| 목적 | 문서 |
| --- | --- |
| 이력서나 기술 블로그에 사용할 수 있는 근거 요약 | [엔지니어링 근거](portfolio/catxi-backend-engineering-evidence.md) |
| 방 입장 경합과 데이터베이스 정합성 | [방 입장 정합성 측정](performance/room-admission.md) |
| 두 앱 서버의 지속 메시징 부하 | [지속 부하 결과](performance/sustained-load.md) |
| Redis Stream 소비자 스레드 모델 비교 | [스레드 모델 비교](performance/thread-model-comparison.md) |
| 관측성 구성요소의 책임과 빈틈 | [관측성 아키텍처 검토](architecture/observability-roles.md) |

## 결과를 해석할 때

- 약 4,500명 가입자와 MAU 수치는 사용자 제공 값입니다. 이 수치만으로 동시 접속자 수나 요청률을 계산하지 않았습니다.
- 성능 수치는 각 문서에 적힌 로컬 Docker 또는 Testcontainers 환경에서 얻었습니다. AWS T3.micro 실측값으로 해석하지 않습니다.
- STOMP 전달, SQL Outbox 처리, Redis Stream 소비는 Firebase 공급자 승인이나 실제 기기 도달과 다른 경계입니다. 테스트 기기 토큰을 사용하지 않은 실행은 FCM 성공률로 표현하지 않습니다.
- 그래프는 해당 실행의 실제 Grafana 패널 캡처입니다. 테스트 조건과 통계 범위가 다른 수치를 하나의 전후 개선률로 합치지 않습니다.
