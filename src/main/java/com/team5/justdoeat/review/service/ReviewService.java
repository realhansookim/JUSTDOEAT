package com.team5.justdoeat.review.service;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.team5.justdoeat.review.entity.ReviewImgEntity;
import com.team5.justdoeat.review.entity.ReviewInfoEntity;
import com.team5.justdoeat.review.repository.ReviewImgRepository;
import com.team5.justdoeat.review.repository.ReviewInfoRepository;
import com.team5.justdoeat.review.vo.LoginUserVO;
import com.team5.justdoeat.review.vo.ReviewInfoVO;
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
    ReviewImgRepository imgRepo;
    // @Autowired
    // OrderInfoRepository orderRepo;

    public Map<String, Object> addReviews( ReviewInfoVO data,  MultipartFile file,
            List<MultipartFile> files) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (data == null && userRepo.findByUiSeq(data.getUiSeq()) == null) {
            resultMap.put("code", HttpStatus.NOT_FOUND);
            resultMap.put("msg", "빈값이거나 로그인정보를 찾을수 없습니다");
            return resultMap;
        }
        System.out.println(data);
        ReviewInfoEntity reviewInfo = ReviewInfoEntity.builder()
                                        .riSeq(null)
                                        .riRegDt(data.getRegDt())
                                        .riContent(data.getContent())
                                        .riSiSeq(data.getStoreSeq())
                                        .rspAllScore(data.getAllScore())
                                        .rspTasteScore(data.getTasteScore())
                                        .rspQuantityScore(data.getQuantityScore())
                                        .rspDeliveryScore(data.getDeliveryScore())
                                        .riUiSeq(data.getUiSeq()).build();
        reviewInfo = rInfoRepo.save(reviewInfo);
        resultMap = addReviewImage(file, files, reviewInfo);
        return resultMap;
    }

    public Map<String, Object> addReviewImage(MultipartFile file, List<MultipartFile> files,
            ReviewInfoEntity reviewInfo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // System.out.println(files);
        if (file == null && files == null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.FORBIDDEN);
            resultMap.put("msg", "이미지가 추가되지않았습니다");
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
        ReviewImgEntity data = imgRepo.findByRimgUri(rimgUri);

        return data.getRimgFilename();
    }

}