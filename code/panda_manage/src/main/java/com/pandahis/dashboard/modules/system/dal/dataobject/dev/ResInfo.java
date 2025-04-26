package com.pandahis.dashboard.modules.system.dal.dataobject.dev;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInfo implements Serializable {
    private String  fileName;
    private String oriName;
}
