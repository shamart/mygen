package com.github.converter;

import com.github.model.DomainFileModel;
import com.github.spec.DomainSpec;
import org.apache.commons.lang3.StringUtils;

public class HandlerConverter extends AbstractConverter {
    @Override
    public void convert() {
        businessException();
        errorEnum();
        globalExceptionHandler();
        resultBody();
    }
    private void resultBody() {
        String fromFile = "src/main/java/com/example/demo/handler/ResultBody.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/handler/ResultBody.java", projectSpec);
    }

    private void globalExceptionHandler() {
        String fromFile = "src/main/java/com/example/demo/handler/GlobalExceptionHandler.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/handler/GlobalExceptionHandler.java", projectSpec);
    }
    private void errorEnum() {
        String fromFile = "src/main/java/com/example/demo/handler/ErrorEnum.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/handler/ErrorEnum.java", projectSpec);
    }

    private void businessException() {
        String fromFile = "src/main/java/com/example/demo/handler/BusinessException.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/handler/BusinessException.java", projectSpec);
    }
}
