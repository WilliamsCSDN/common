package org.williams.project.component.mvc.result;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * @author LiuJieBang
 * @date 2023-08-10-10:49
 * @Description
 */
@Data
@AllArgsConstructor
public class HttpError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String message;
}
