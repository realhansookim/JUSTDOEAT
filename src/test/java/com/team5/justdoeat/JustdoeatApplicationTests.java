package com.team5.justdoeat;

import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.service.UserInfoService;

@SpringBootTest
class JustdoeatApplicationTests {
	
	// @Autowired UserInfoService sService;
	// @Test
	// public ResponseEntity<Object> addUSer(@RequestParam UserInfoEntity entity){
	// 	Map<String,Object>map = sService.addUserEntity(entity);
	// 	return new ResponseEntity<>(map,(HttpStatus)map.get("code"));
	@Test
	public void ramdomString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
    String generatedString = random.ints(leftLimit, rightLimit + 1)
                                   .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                   .limit(targetStringLength)
                                   .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                   .toString();
    System.out.println(generatedString);
  }
}


