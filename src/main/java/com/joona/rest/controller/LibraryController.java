package com.joona.rest.controller;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {
    @RequestMapping(value = "/library/app/health",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> healthCheck(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "Green");
        return new ResponseEntity<String>(jsonObject.toJSONString(), HttpStatus.OK);
    }
}
