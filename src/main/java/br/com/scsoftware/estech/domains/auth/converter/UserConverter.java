package br.com.scsoftware.estech.domains.auth.converter;

import br.com.scsoftware.estech.domains.auth.business.RoleBO;
import br.com.scsoftware.estech.domains.auth.business.UserBO;
import br.com.scsoftware.estech.domains.auth.business.UserResetPasswordBO;
import br.com.scsoftware.estech.domains.auth.controller.api.v1.response.UserAvatarResponse;
import br.com.scsoftware.estech.domains.auth.controller.api.v1.response.UserResponse;
import br.com.scsoftware.estech.domains.auth.entity.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserConverter {
    public static Optional<UserBO> toBO(Optional<User> user) {
        if (user.isEmpty())
            return Optional.empty();

        final User userEnt = user.get();
        return Optional.of(
                UserBO.builder()
                        .id(userEnt.getId())
                        .tenantId(userEnt.getTenantId())
                        .name(userEnt.getName())
                        .username(userEnt.getUsername())
                        .password(userEnt.getPassword())
                        .changePassword(userEnt.isChangePassword())
                        .accountLocked(userEnt.isAccountLocked())
                        .roles(userEnt.getRoles().stream()
                                .map(userRole -> RoleConverter.toBO(userRole.getRole()))
                                .collect(Collectors.toList()))
                        .build()
        );
    }

    public static UserResponse toResponse(UserBO user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .changePassword(user.isChangePassword())
                .accountLocked(user.isAccountLocked())
                .roles(user.getRoles().stream()
                        .map(RoleBO::getName)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public static UserAvatarResponse toAvatarResponse(final Long id, final String avatar) {
        return UserAvatarResponse.builder()
                .id(id)
                .avatar(avatar)
                .build();
    }

    public static Optional<UserResetPasswordBO> toUserResetPasswordBO(Optional<User> user) {
        if (user.isEmpty())
            return Optional.empty();

        final User userEnt = user.get();
        LocalDateTime passwordResetExpires = null;
        if (userEnt.getPasswordResetExpires() != null) {
            passwordResetExpires = LocalDateTime.ofInstant(userEnt.getPasswordResetExpires().toInstant(), ZoneId.systemDefault());
        }
        return Optional.of(
                UserResetPasswordBO.builder()
                        .id(userEnt.getId())
                        .name(userEnt.getName())
                        .username(userEnt.getUsername())
                        .passwordResetToken(userEnt.getPasswordResetToken())
                        .passwordResetExpires(passwordResetExpires)
                        .build()
        );
    }

}
