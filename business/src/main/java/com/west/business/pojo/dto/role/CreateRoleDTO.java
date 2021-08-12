package com.west.business.pojo.dto.role;

import com.west.business.pojo.dto.perm.PermDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateRoleDTO extends RoleDTO {

    private List<PermDTO> permList;
}
