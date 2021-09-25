package br.com.scsoftware.afinese.domains.auth.controller.api.v1.response;

import lombok.Builder;
import lombok.Getter;

/**
 * @author samuel-cruz
 */
@Builder
@Getter
public class UserAvatarResponse {

    private Long id;
    private String avatar;
}