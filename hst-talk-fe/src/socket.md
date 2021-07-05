## 프로토콜 기본 구조

1
2
3
4
5
{
"messageType": "프로토콜 타입",
"roomId": "Room Id, null 일 수 도 있음",
"payload": "각 프로토콜의 본문"
}

## 프로토콜 목록

### 1. 사용자 정보 초기화

##### Request

{ "messageType": "INIT_USER_INFO", "roomId": null, "payload": "사용자 닉네임" }

##### Response

{ "messageType": "INIT_USER_INFO", "roomId": null, "payload": "f0c5b85b-d63f-181d-b2de-f4cef16d2d8e"}

### 2. 방 생성

##### Request

{ "messageType": "CREATE_ROOM" }

##### Response

{"messageType": "CREATE_ROOM", "roomId": "pKWhFEpZ", "payload": null }

### 3. 방 입장

##### Request

{ "messageType": "ENTER_ROOM", "roomId": "pKWhFEpZ" }

##### Response

- 입장자만
  {"messageType": "ENTER_ROOM", "roomId": "pKWhFEpZ", "payload": {"id":"d2dadbd8-88ec-ed39-41fb-0f46793c76dd","nickname":"Test2"}}

- 입장자 + 기존 방인원 모두
  {"messageType": "CHAT", "roomId": "fygBeP1y", "payload" :{"senderNickname":"SYSTEM","message":"사용자 닉네임님이 입장하셨습니다^^","sendAt":"2021-07-02T02:34:17.424"}}

### 4. 채팅

##### Request

{ "messageType": "CHAT", "roomId": "pKWhFEpZ", "payload": "안녕하세요^^" }

##### Response

{"messageType":"CHAT","roomId":"fygBeP1y","payload":{"senderNickname":"SYSTEM","message":"사용자 닉네임님이 입장하셨습니다^^","sendAt":"2021-07-02T02:34:17.424"}}
