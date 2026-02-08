package com.api.${moduleName}.controller;

<#list apiList as api>
import com.api.${moduleName}.data.dto.${controllerName}.${api.name?cap_first}Dto;
</#list>
<#list apiList as api>
import com.api.${moduleName}.service.${controllerName}.${api.name?cap_first};
</#list>
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * ${moduleName?cap_first}${controllerAnnotation}相关接口
 *
 * @author ${author}
 */
@RestController
@RequestMapping("/${moduleName}/${path}")
public class ${controllerName?cap_first}Controller {

<#list apiList as api>
     @Resource(name = "${moduleName?cap_first}${api.name?cap_first}${controllerName?cap_first}ServiceImpl")
     private ${api.name?cap_first} ${api.name}${controllerName?cap_first}Service;
</#list>

<#list apiList as api>
     // ${api.annotation}
     @PostMapping("/${api.name}")
     public Result<?> ${api.name}(@RequestBody @Valid ${api.name?cap_first}Dto dto) {
         ${api.name}${controllerName?cap_first}Service.check(dto);
         return ${api.name}${controllerName?cap_first}Service.service(dto);
     }
</#list>
}
