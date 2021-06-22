package com.trainingorg.demo.Service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SysMenuService {
    Map<String,Object> menu();
}
