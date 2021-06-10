package io.github.kings1990.plugin.fastrequest.parse;

import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class AbstractParamParse {

    abstract LinkedHashMap<String, Object> parseParam(FastRequestConfiguration config, List<ParamNameType> paramNameTypeList);
}
