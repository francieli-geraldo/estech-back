package br.com.scsoftware.afinese.domains.auth.service;

import br.com.scsoftware.afinese.domains.auth.business.UserBO;
import br.com.scsoftware.afinese.domains.auth.business.UserResetPasswordBO;
import br.com.scsoftware.afinese.domains.auth.entity.User;
import br.com.scsoftware.afinese.domains.basicrecords.service.BaseService;

import java.util.Optional;

/**
 * @author samuel-cruz
 */
public interface UserService extends BaseService<User> {

    Optional<UserBO> getRecordByUserName(String username);

    UserResetPasswordBO getUserForResetPassword(Long userId);

    UserBO listUser(String username);

    String getAvatar(Long id);

    boolean hasHole(String roleName);

    String forgotPassword(Long id, String username);

    void updatePassword(Long id, String newPassword);
}
