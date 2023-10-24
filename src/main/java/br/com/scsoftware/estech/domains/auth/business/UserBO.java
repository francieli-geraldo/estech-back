package br.com.scsoftware.estech.domains.auth.business;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
@Builder
public class UserBO {
    private Long id;
    private Long tenantId;
    private String name;
    private String username;
    private String password;
    private boolean changePassword;
    private boolean accountLocked;
    private List<RoleBO> roles;

    public String getFamilyName() {
        String[] listName = getName().split(" ");
        String firstName = listName[0];
        String lastName = listName[listName.length - 1];
        String familyName = firstName;
        if(!(StringUtils.isBlank(lastName) || firstName.equals(lastName))) {
            familyName = familyName.concat(" ").concat(lastName);
        }

        return familyName;
    }
}
