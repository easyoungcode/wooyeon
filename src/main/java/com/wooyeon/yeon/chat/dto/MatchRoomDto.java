package com.wooyeon.yeon.chat.dto;

import com.wooyeon.yeon.likeManage.domain.UserLike;
import com.wooyeon.yeon.user.domain.User;
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
        private Long userId;
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
        private UserLike matchUserName;
        private String matchUserProfileImg;
        private String recentMessage;
        private Timestamp sendTime;
    }
}
