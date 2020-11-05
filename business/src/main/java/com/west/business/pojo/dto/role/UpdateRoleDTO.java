package com.west.business.pojo.dto.role;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateRoleDTO extends RoleDTO {

    @NotBlank
    private String roleId;

    private List<String> newPerms;
}
