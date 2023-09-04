package com.wooyeon.yeon.chat.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class MatchRoomDto {

    @Data
    public static class MatchRequestDto {
        @NotNull
        private Long UserId;
    }

    @Data
    public static class SearchRoomRequestDto {
        @NotNull
        private String searchWord;
    }

    @Data
    @Builder
    public static class MatchResponseDto {
        @NotNull
        private Long matchId;
        @NotNull
        private String matchUserName;
        private String matchUserProfileImg;
        private String recentMessage;
        private Timestamp sendTime;
    }
}
