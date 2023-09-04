package com.wooyeon.yeon.chat.service;

import com.wooyeon.yeon.chat.domain.Chat;
import com.wooyeon.yeon.chat.dto.ChatDto;
import com.wooyeon.yeon.chat.dto.MatchRoomDto;
import com.wooyeon.yeon.chat.repository.ChatRepository;
import com.wooyeon.yeon.profileChoice.domain.UserMatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public MatchRoomDto.MatchResponseDto getMatchRoomList(MatchRoomDto.MatchRequestDto matchRequestDto) {

        // 매치 테이블에서 유저 아이디로 리스트 조회하는 거 희수가 만들어야함
        
        return MatchRoomDto.MatchResponseDto.builder()
                .matchId(1L)
                .matchUserName("kim") // 유저 아이디로 유저 테이블에서 조회
                .matchUserProfileImg("url") // 유저 아이디로 유저 테이블에서 조회
                .recentMessage("hello") // null 값 가능 채팅 테이블에서 조회
                .sendTime(new Timestamp(1l)) // null 값 가능 채팅 테이블에서 조회
                .build();
    }


    // 클라이언트에서 전달받은 chatDto(메시지 정보) DB에 저장
    public void insertChat(ChatDto chatDto) {
//        Long matchId = chatDto.getMatchId();
//        Match match = new Match(); matchId로 Match 객체 조회 해야 함.
        String sender = chatDto.getSender();
        String message = chatDto.getMessage();

        chatRepository.save(Chat.builder()
                .message(message)
                .sender(sender)
//                .match(match)
                .build());
    }
}
