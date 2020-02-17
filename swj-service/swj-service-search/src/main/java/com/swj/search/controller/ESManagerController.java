package com.swj.search.controller;

import com.swj.search.service.ESManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ESManagerController {

  @Autowired private ESManagerService esManagerService;

  // 创建索引库结构
  @GetMapping("/create")
  public void create() {
    esManagerService.createMappingAndIndex();
  }

  // 导入全部数据
  @GetMapping("/importAll")
  public void importAll() {
    esManagerService.importAll();
  }
}
