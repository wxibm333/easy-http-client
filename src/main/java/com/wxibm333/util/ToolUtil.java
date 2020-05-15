package com.wxibm333.util;

import com.wxibm333.constant.Common;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-15 17:18
 */
public class ToolUtil {

  public static String removeFrontAndRearDiagonal(String param) {
    while (param.startsWith(Common.DIAGONAL)) {
      param = param.substring(1);
    }
    while (param.endsWith(Common.DIAGONAL)) {
      param = param.substring(0, param.length() - 1);
    }
    return param;
  }
}
