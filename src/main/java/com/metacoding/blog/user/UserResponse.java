package com.metacoding.blog.user;

public record UserResponse(Integer id, String username) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}
