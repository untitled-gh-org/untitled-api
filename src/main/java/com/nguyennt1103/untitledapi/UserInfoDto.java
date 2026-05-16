package com.nguyennt1103.untitledapi;

import java.util.List;

public record UserInfoDto(String userId, String username, String email, List<String> roles, Long exp) {
    public static final UserInfoDto ANONYMOUS = new UserInfoDto("", "", "", List.of(), null);
}
