package com.tcarisland.tclang.components;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Project {
    ADVANCED_HATCH("AdvancedHatch"),
    THORTYPE_FILTER("ThorTypeFilter");

    final String configPath;

}
