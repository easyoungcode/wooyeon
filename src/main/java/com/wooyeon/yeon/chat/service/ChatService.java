package com.wooyeon.yeon.chat.service;

import com.wooyeon.yeon.chat.domain.Chat;
import com.wooyeon.yeon.chat.dto.ChatDto;
import com.wooyeon.yeon.chat.dto.MatchRoomDto;
import com.wooyeon.yeon.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public List<MatchRoomDto.MatchResponseDto> searchMatchRoomList(MatchRoomDto.SearchRoomRequestDto SearchRoomRequest) {

        // 단어로 매치 테이블에서 검색 리스트 찾는 거 받아오기 -> sql 조건문 사용
        List<MatchRoomDto.MatchResponseDto> matchRoomList = new ArrayList<>();

        for (MatchRoomDto.MatchResponseDto room : matchRoomList) {
            MatchRoomDto.MatchResponseDto.builder()
                    .matchId(1L) // 매치 테이블 아이디
                    .matchUserName("youngdon") // 매치 테이블 자신 말고 타인
                    .matchUserProfileImg(null)
                    .recentMessage(null)
                    .sendTime(null)
                    .build();
        }
        return matchRoomList;
    }

    public List<MatchRoomDto.MatchResponseDto> getMatchRoomList(MatchRoomDto.MatchRequestDto matchRequestDto) {

        // 접속자 아이디로 매치 테이블에서 전체 리스트 찾는 거 받아오기
        List<MatchRoomDto.MatchResponseDto> matchRoomList = new ArrayList<>();

        for (MatchRoomDto.MatchResponseDto room : matchRoomList) {
            MatchRoomDto.MatchResponseDto.builder()
                    .matchId(1L) // 매치 테이블 아이디
                    .matchUserName("youngdon") // 매치 테이블 자신 말고 타인
                    .matchUserProfileImg(null)
                    .recentMessage(null)
                    .sendTime(null)
                    .build();
        }
        return matchRoomList;
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
