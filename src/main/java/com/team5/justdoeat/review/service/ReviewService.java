package com.team5.justdoeat.review.service;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.team5.justdoeat.order.entity.OrderInfoEntity;
import com.team5.justdoeat.order.repository.OrderInfoRepository;
import com.team5.justdoeat.review.entity.ReviewImgEntity;
import com.team5.justdoeat.review.entity.ReviewInfoEntity;
import com.team5.justdoeat.review.entity.ReviewListEntity;
import com.team5.justdoeat.review.repository.ReviewImgRepository;
import com.team5.justdoeat.review.repository.ReviewInfoRepository;
import com.team5.justdoeat.review.repository.ReviewListRepository;
import com.team5.justdoeat.review.vo.LoginUserVO;
import com.team5.justdoeat.review.vo.ReviewDeleteVO;
import com.team5.justdoeat.review.vo.ReviewInfoVO;
import com.team5.justdoeat.review.vo.ReviewListVO;
import com.team5.justdoeat.review.vo.ReviewUserVO;
import com.team5.justdoeat.store.repository.StoreInfoRepository;
import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.repository.UserInfoRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ReviewService {
    @Autowired
    ReviewInfoRepository rInfoRepo;
    @Autowired
    ReviewImgRepository rImgRepo;
    @Autowired
    UserInfoRepository userRepo;
    @Autowired
    StoreInfoRepository sInfoRepo;
    @Autowired
    ReviewListRepository reviewListRepo;
    // @Autowired
    // OrderInfoRepository orderRepo;

    @Autowired
    OrderInfoRepository orderRepo;

    public Map<String, Object> addReviews( ReviewInfoVO data,  MultipartFile file,
            List<MultipartFile> files) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (data == null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_FOUND);
            resultMap.put("msg", "빈값이거나 로그인정보를 찾을수 없습니다");
            return resultMap;
        }
        OrderInfoEntity orderEntity = orderRepo.findByOiName(data.getOrderSeq());
        System.out.println(orderEntity);
        ReviewInfoEntity reviewInfo = ReviewInfoEntity.builder()
                                        .riSeq(null)
                                        .riRegDt(data.getRegDt())
                                        .riContent(data.getContent())
                                        .rspAllScore(data.getAllScore())
                                        .rspTasteScore(data.getTasteScore())
                                        .rspQuantityScore(data.getQuantityScore())
                                        .rspDeliveryScore(data.getDeliveryScore())
                                        .riSiSeq(orderEntity.getStoreInfo().getSiSeq())
                                        .userInfo(orderEntity.getUserInfoEntity())
                                        .riOerderSeq(orderEntity.getOiName()).build();
        reviewInfo = rInfoRepo.save(reviewInfo);
        resultMap = addReviewImage(file, files, reviewInfo);
        return resultMap;
    }

    //     public Map<String, Object> addReviews( ReviewInfoVO data,  MultipartFile file,
    //         List<MultipartFile> files) {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     if (data == null) {
    //         resultMap.put("status", false);
    //         resultMap.put("code", HttpStatus.NOT_FOUND);
    //         resultMap.put("msg", "빈값이거나 로그인정보를 찾을수 없습니다");
    //         return resultMap;
    //     }
    //     // OrderInfoEntity orderEntity = orderRepo.findByOrName(data.getOrderSeq());
    //     System.out.println(data);
    //     System.out.println("=================================");
    //     ReviewInfoEntity reviewInfo = ReviewInfoEntity.builder()
    //                                     .riSeq(null)
    //                                     .riRegDt(data.getRegDt())
    //                                     .riContent(data.getContent())
    //                                     .rspAllScore(data.getAllScore())
    //                                     .rspTasteScore(data.getTasteScore())
    //                                     .rspQuantityScore(data.getQuantityScore())
    //                                     .rspDeliveryScore(data.getDeliveryScore())
    //                                     .riSiSeq(data.getUiSeq())
    //                                     .userInfo(userRepo.findByUiSeq(data.getSiSeq())).build();
    //     System.out.println(reviewInfo);
    //     reviewInfo = rInfoRepo.save(reviewInfo);
    //     resultMap = addReviewImage(file, files, reviewInfo);
    //     return resultMap;
    // }

    public Map<String, Object> addReviewImage(MultipartFile file, List<MultipartFile> files,
            ReviewInfoEntity reviewInfo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // System.out.println(files);
        if (file == null && files == null) {
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("msg", "댓글만 등록되었습니다");
        } else if (file != null && files == null) {
            addFileImage(file, reviewInfo, 1);
            resultMap.put("status", true);
            resultMap.put("msg", "이미지가 저장되었습니다");
            resultMap.put("code", HttpStatus.OK);
        } else if (file == null && files != null) {
            addFilesImage(files, reviewInfo);
            resultMap.put("status", true);
            resultMap.put("msg", "이미지가 저장되었습니다");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.FORBIDDEN);
            resultMap.put("msg", "잘못된 값이 들어왔습니다.");
            return resultMap;
        }
        return resultMap;

    }

    @Value("${file.image.review}")
    private String fileDir;

    public void addFileImage(MultipartFile file, ReviewInfoEntity reviewInfo, Integer imgOrder) {
        Path folderLocation = Paths.get(fileDir);
        String origName = file.getOriginalFilename();// 원래파일이름
        // String savedPath = fileDir + origName;
        String reviewFileName = createStoreFileName(origName);
        // try {
        //     file.transferTo(new File(fileDir + reviewFileName));

        // } catch (IllegalStateException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // } finally {
            Path targerFile = folderLocation.resolve(reviewFileName);
            try {
                Files.copy(file.getInputStream(), targerFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // reviewFileName = createStoreFileName(origName);
            // System.out.println(reviewFileName);
            ReviewImgEntity imagefile = ReviewImgEntity.builder()
            .rimgFilename(origName)
            .rimgOrder(imgOrder)
            .reviewInfo(reviewInfo)
            .rimgUri("http://192.168.0.156:9988/review/image/"+reviewFileName)
            .rimgSavename(reviewFileName).build();
            rImgRepo.save(imagefile);
        // }
    }

    // UUID를 이용해 파일 이름을 생성한다. 단, 어떤 파일인지 알기 위해 확장자는 남겨둔다.
    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    // 확장자를 꺼낸다.
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


    public void addFilesImage(List<MultipartFile> files, ReviewInfoEntity reviewInfo) {

        for (Integer i = 0; i < files.size(); i++) {
            addFileImage(files.get(i), reviewInfo, i + 1);
        }
    }

    // public Map<String, Object> deleteReview(HttpSession session, Long reviewSeq) {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    //     LoginUserVO user = (LoginUserVO) session.getAttribute("loginUser");

    //     UserInfoEntity userEntity = userRepo.findByUiSeq(user.getUserSeq());
    //     if (rInfoRepo.countByRiSeqAndUserInfo(reviewSeq, userEntity) == 0) {
    //         resultMap.put("status", false);
    //         resultMap.put("code", HttpStatus.FORBIDDEN);
    //         resultMap.put("msg", "자신이 작성한 주문이 아닙니다.");
    //         System.out.println(reviewSeq);
    //         System.out.println(userEntity.toString());
    //         return resultMap;
    //     } else {
    //         rInfoRepo.deleteById(reviewSeq);
    //         resultMap.put("status", true);
    //         resultMap.put("code", HttpStatus.FORBIDDEN);
    //         resultMap.put("msg", "삭제되었습니다.");
    //     }
    //     return resultMap;
    // }

    public Map<String, Object> listReview(HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        return resultMap;
    }

    public String getIiFileNameByUri(String rimgUri) {
        ReviewImgEntity data = rImgRepo.findByRimgSavename(rimgUri);
        
        return data.getRimgSavename();
    }
    //리뷰 리스트 출력
    public  Map<String, Object> getReviewList(Long storeNo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ReviewInfoEntity> list = rInfoRepo.findByRiSiSeq(storeNo);
        if (list.size() == 0){
            resultMap.put("status",false);
            resultMap.put("message","등록된 댓글이없습니다");
            return resultMap;
        }
        resultMap.put("list", list);
        resultMap.put("status",true);
        resultMap.put("message","댓글이 출력되었습니다");
        return resultMap;
    }

    public Map<String, Object> deleteReview(ReviewDeleteVO reviewDeleteVO) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (reviewDeleteVO == null){
            resultMap.put("status",false);
            resultMap.put("message","등록된 댓글이없습니다");
            return resultMap;
        }
    ;
        ReviewInfoEntity entity = rInfoRepo.findByUserInfoAndRiSeq(userRepo.findByUiSeq(reviewDeleteVO.getUserSeq()), reviewDeleteVO.getReviewSeq());
        rInfoRepo.delete(entity);
        resultMap.put("status",true);
        resultMap.put("message","삭제되었습니다");  
        return resultMap;
    }

    // public List<Map<String, Object>> getReviewImgList(Long riSeq){
    //     List<Map<String, Object>> imageList = rImgRepo.getReviewImageList(riSeq);
    //     return imageList;
    // }

}