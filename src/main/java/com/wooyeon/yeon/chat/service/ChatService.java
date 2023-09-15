package com.wooyeon.yeon.chat.service;

import com.wooyeon.yeon.chat.domain.Chat;
import com.wooyeon.yeon.chat.dto.ChatDto;
import com.wooyeon.yeon.chat.dto.MatchRoomDto;
import com.wooyeon.yeon.chat.repository.ChatRepository;
import com.wooyeon.yeon.profileChoice.domain.UserMatch;
import com.wooyeon.yeon.profileChoice.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final MatchRepository matchRepository;

    public List<MatchRoomDto.MatchResponseDto> searchMatchRoomList(MatchRoomDto.SearchRoomRequestDto SearchRoomRequest) {

        // 검색 기능 보류
        List<MatchRoomDto.MatchResponseDto> matchRoomList = new ArrayList<>();
        List<MatchRoomDto.MatchResponseDto> roomList = new ArrayList<>();

        for (MatchRoomDto.MatchResponseDto room : matchRoomList) {
            MatchRoomDto.MatchResponseDto.builder()
                    .matchId(1L) // 매치 테이블 아이디
//                    .matchUserName() // 매치 테이블 자신 말고 타인
                    .matchUserProfileImg(null)
                    .recentMessage(null)
                    .sendTime(null)
                    .build();
        }
        return matchRoomList;
    }

    public List<MatchRoomDto.MatchResponseDto> getMatchRoomList(MatchRoomDto.MatchRequestDto request) {

        List<UserMatch> matchRoomList = matchRepository.findAllByUserLike1(request.getUserId());
        List<MatchRoomDto.MatchResponseDto> roomList = new ArrayList<>();

        for (UserMatch room : matchRoomList) {
            MatchRoomDto.MatchResponseDto response = MatchRoomDto.MatchResponseDto.builder()
                    .matchId(room.getMatchId()) // 매치 테이블 아이디
                    .matchUserName(room.getUserLike2()) // 매치 테이블 자신 말고 타인
                    .matchUserProfileImg(null)
                    .recentMessage(null)
                    .sendTime(null)
                    .build();

            roomList.add(response);
        }
        return roomList;
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
