package com.team5.justdoeat.review.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team5.justdoeat.review.repository.ReviewInfoRepository;
import com.team5.justdoeat.review.service.ReviewService;
import com.team5.justdoeat.review.vo.ReviewDeleteVO;
import com.team5.justdoeat.review.vo.ReviewInfoVO;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/review")
@RestController
public class ReviewController {
  
  @Autowired ReviewService reviewService;

  // @Autowired UserInfoRepository userRepo;
  // @Autowired StoreInfoRepository storeInfoRepo;

  // @Autowired 
  //@Nullable MultipartFile file, @Nullable List<MultipartFile> multipartFiles

  @PostMapping("/add")
  public Map<String,Object> addReview(@RequestPart ReviewInfoVO reviewInfoVO,
   @RequestPart @Nullable MultipartFile file,@RequestPart  @Nullable List<MultipartFile> multipartFiles){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    System.out.println(reviewInfoVO);
    resultMap = reviewService.addReviews( reviewInfoVO, file, multipartFiles);
    return resultMap;
  }

  @GetMapping("/store/review/list")
  public Map<String,Object> getlistReview(HttpSession session){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap = reviewService.listReview(session);
    
    return resultMap;
  }
  @Value("${file.image.review}")
  private String review_img;

  @GetMapping("/image/{rimgUri}")
  public ResponseEntity<Resource> getImage(@PathVariable String rimgUri, HttpServletRequest request)
          throws Exception {
      Path folderLocation = Paths.get(review_img);
      String filename = reviewService.getIiFileNameByUri(rimgUri);
      String[] split = filename.split("\\.");
      String ext = split[split.length - 1];
      String exportName = rimgUri + "." + ext;
      Path targetFile = folderLocation.resolve(filename);
      Resource r = null;
      try {
          r = new UrlResource(targetFile.toUri());
      } catch (Exception e) {
          e.printStackTrace();
      }
      String contentType = null;
      try {
          contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
          if (contentType == null) {
              contentType = "application/octet-stream";
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return ResponseEntity.ok()
              .contentType(MediaType.parseMediaType(contentType))
              .header(HttpHeaders.CONTENT_DISPOSITION,
                      "attachment; filename*=\"" + URLEncoder.encode(exportName, "UTF-8") + "\"")
              .body(r);
  }

  // @DeleteMapping("/remove")
  // public Map<String,Object> removeReview(HttpSession session, @RequestParam Long reviewSeq){
  //   Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

  //   resultMap = reviewService.deleteReview(session,reviewSeq);
  //   return resultMap;
  // }
//    @PostMapping("/upload")
// public ResponseEntity<Object> putImageUpload(
//            @RequestPart @Nullable MultipartFile file, @RequestPart @Nullable List<MultipartFile> multipartFiles, HttpSession session
// ) throws IOException {
//   Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
//   LoginUserVO loginUser = (LoginUserVO)session.getAttribute("loginUser");
//       resultMap = reviewService.addReviewImage(file,multipartFiles, null);
//       return new ResponseEntity<>(resultMap,(HttpStatus)resultMap.get("code"));
//    }

@GetMapping("/list")
    public ResponseEntity<Object> getReviewList(@RequestParam Long storeNo) {
    return new ResponseEntity<>(reviewService.getReviewList(storeNo), HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteReview(@RequestBody ReviewDeleteVO reviewDeleteVO){
    return new ResponseEntity<>(reviewService.deleteReview(reviewDeleteVO), HttpStatus.OK);

    }
  }
