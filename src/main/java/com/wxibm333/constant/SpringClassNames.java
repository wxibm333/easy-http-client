package com.wxibm333.constant;

import com.google.common.collect.Sets;
import java.util.Set;

/**
 * spring相关类名
 *
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-15 11:58
 */
public interface SpringClassNames {

  String REQUEST = "org.springframework.web.bind.annotation.RequestMapping";
  String REQUEST_GET = "org.springframework.web.bind.annotation.GetMapping";
  String REQUEST_PUT = "org.springframework.web.bind.annotation.PutMapping";
  String REQUEST_POST = "org.springframework.web.bind.annotation.PostMapping";
  String REQUEST_PATCH = "org.springframework.web.bind.annotation.PatchMapping";
  String REQUEST_DELETE = "org.springframework.web.bind.annotation.DeleteMapping";
  Set<String> REQUEST_COLLECTION = Sets
      .newHashSet(REQUEST, REQUEST_GET, REQUEST_PUT, REQUEST_POST, REQUEST_PATCH, REQUEST_DELETE);

  String CONTROLLER = "org.springframework.stereotype.Controller";
  String REST_CONTROLLER = "org.springframework.web.bind.annotation.RestController";
  Set<String> CONTROLLER_COLLECTION = Sets.newHashSet(CONTROLLER, REST_CONTROLLER);

}
