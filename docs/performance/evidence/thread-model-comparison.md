# 스레드 모델 실행 원자료

이 기록은 2026-10-08에 실행한 Grafana 캡처의 run ID와 시간 범위를 보존합니다. 모든 캡처는 dashboard runtime version 10이며, 일반 패널은 1,708×592 픽셀, Redis backlog 패널은 3,432×592 픽셀입니다.

| 순서 | 모드 | Run ID | Grafana 조회 구간 UTC | 부하 구간 |
| --- | --- | --- | --- | ---: |
| 첫 비교 1 | 플랫폼 | `run-20261008-tuned-platform-1cpu-reader4-msg25-5m-v2` | 20:43:46–20:50:01 | 300초 |
| 첫 비교 2 | 가상 | `run-20261008-control-virtual-1cpu-reader4-msg25-5m` | 20:55:13–21:01:24 | 300초 |
| 역순 반복 1 | 가상 | `run-20261008-repeat2-virtual-1cpu-reader4-msg25-5m` | 21:07:48–21:13:59 | 300초 |
| 역순 반복 2 | 플랫폼 | `run-20261008-repeat2-platform-1cpu-reader4-msg25-5m` | 21:18:55–21:25:08 | 300초 |
| 운영 후보 프로파일 | 플랫폼 reader 1 | `run-20261008-profile-platform-020cpu-reader1-5rps-5m` | 21:37:08.820–21:44:40.114 | 300초 |
| 운영 후보 프로파일 | 가상 reader 4 | `run-20261008-profile-virtual-020cpu-reader4-5rps-5m` | 22:05:26.886–22:12:55.851 | 300초 |

시간은 2026-10-07 UTC입니다. capture manifest의 로컬 브라우저 실행 경로와 `localhost` Grafana URL은 제외했습니다. 결과 집계와 해석은 [스레드 모델 비교](../thread-model-comparison.md)에 있습니다.

운영 후보 캡처는 Grafana dashboard runtime version 11에서 다시 렌더링했습니다. 성공률, 지연, CPU throttling 패널은 3,842×592 픽셀이고 Redis lag/PEL 패널은 7,700×592 픽셀입니다. 재렌더링은 동일 run ID의 기존 Prometheus 구간을 사용했으며 새 부하 실행을 뜻하지 않습니다.

첫 가상 실행과 두 운영 후보 실행의 manifest는 threshold 통과로 기록하지만, 연결된 k6 summary JSON은 전달 성공률 1과 실패 0, dropped iteration 0을 담으면서 해당 threshold들을 false로 표시합니다. runner 코드는 종료 코드 0만으로 manifest를 통과 처리합니다. 이 불일치의 원인을 확정하지 않았으므로 포트폴리오 문서는 pass/fail 대신 관측값과 판정 한계를 함께 인용합니다. 2시간 지속 부하 summary에도 false threshold가 있으나 대응 manifest는 보존 자료에서 찾지 못했습니다.
