package com.wooyeon.yeon.chat.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MatchRoomDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class MatchRequestDto {
        private Long UserId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class MatchResponseDto {
        private Long matchId;
        private String matchUserName;
        private String matchUserProfileImg;
        private String recentMessage;
        private Timestamp sendTime;
    }
}
