package com.team5.justdoeat.review.api;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team5.justdoeat.review.service.ReviewService;
import com.team5.justdoeat.review.vo.ReviewInfoVO;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/review")
@RestController
public class ReviewController {
  
  @Autowired ReviewService reviewService;

  // @Autowired UserInfoRepository userRepo;
  // @Autowired StoreInfoRepository storeInfoRepo;

  // @Autowired 
  //@Nullable MultipartFile file, @Nullable List<MultipartFile> multipartFiles

//   @PostMapping("/add")
//   public Map<String,Object> addReview(HttpSession session ,@RequestPart ReviewInfoVO reviewInfoVO,
//    @RequestPart @Nullable MultipartFile file,@RequestPart  @Nullable List<MultipartFile> multipartFiles){
//     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();


//     // ReviewInfoVO reviewInfoVO = new ReviewInfoVO(LocalDate.now(), "댓글내용테스트", 5.0, 5.0, 5.0, 5.0);
    
    
//     resultMap = reviewService.addReviews(session, reviewInfoVO, file, multipartFiles);
//     // return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
//     return resultMap;
//   }

  @GetMapping("/store/review/list")
  public Map<String,Object> getlistReview(HttpSession session){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap = reviewService.listReview(session);
    
    return resultMap;
  }

  @DeleteMapping("/remove")
  public Map<String,Object> removeReview(HttpSession session, @RequestParam Long reviewSeq){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    resultMap = reviewService.deleteReview(session,reviewSeq);
    return resultMap;
  }
//    @PostMapping("/upload")
// public ResponseEntity<Object> putImageUpload(
//            @RequestPart @Nullable MultipartFile file, @RequestPart @Nullable List<MultipartFile> multipartFiles, HttpSession session
// ) throws IOException {
//   Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
//   LoginUserVO loginUser = (LoginUserVO)session.getAttribute("loginUser");
//       resultMap = reviewService.addReviewImage(file,multipartFiles, null);
//       return new ResponseEntity<>(resultMap,(HttpStatus)resultMap.get("code"));
//    }

}
