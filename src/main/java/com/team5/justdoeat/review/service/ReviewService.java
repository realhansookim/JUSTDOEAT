package com.team5.justdoeat.review.service;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
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
import com.team5.justdoeat.review.entity.ReviewScorePointEntity;
import com.team5.justdoeat.review.repository.ReviewImgRepository;
import com.team5.justdoeat.review.repository.ReviewInfoRepository;
import com.team5.justdoeat.review.repository.ReviewScorePointRepository;
import com.team5.justdoeat.review.vo.LoginUserVO;
import com.team5.justdoeat.review.vo.ReviewInfoVO;
import com.team5.justdoeat.store.repository.StoreInfoRepository;
import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.repository.UserInfoRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ReviewService {

    @Autowired
    ReviewScorePointRepository rScoreInfoRepo;
    @Autowired
    ReviewInfoRepository rInfoRepo;
    @Autowired
    ReviewImgRepository rImgRepo;
    @Autowired
    UserInfoRepository userRepo;
    @Autowired
    StoreInfoRepository sInfoRepo;

    // public Map<String, Object> addReviews(HttpSession session, ReviewInfoVO data, MultipartFile file,
    //         List<MultipartFile> files) {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     LoginUserVO user = (LoginUserVO) session.getAttribute("loginUser");
    //     if (data == null && session.getAttribute("loginUser") == null) {
    //         resultMap.put("code", HttpStatus.NOT_FOUND);
    //         resultMap.put("msg", "빈값이거나 로그인정보를 찾을수 없습니다");
    //         return resultMap;
    //     }
    //     ReviewScorePointEntity reviewScore = new ReviewScorePointEntity(data.getAllScore(), data.getTasteScore(),
    //             data.getQuantityScore(), data.getDeliveryScore());
    //     reviewScore = rScoreInfoRepo.save(reviewScore);
    //     ReviewInfoEntity reviewInfo = new ReviewInfoEntity(data.getRegDt(), data.getContent(), user.getStoreOrder(),
    //             sInfoRepo.findBySiSeq(user.getStoreSeq()), reviewScore, userRepo.findByUiSeq(user.getUserSeq()));
    //     reviewInfo = rInfoRepo.save(reviewInfo);
    //     resultMap = addReviewImage(file, files, reviewInfo);
    //     return resultMap;
    // }

    public Map<String, Object> addReviewImage(MultipartFile file, List<MultipartFile> files,
            ReviewInfoEntity reviewInfo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        System.out.println(files);
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
        String origName = file.getOriginalFilename();// 원래파일이름
        String savedPath = fileDir + origName;
        try {
            file.transferTo(new File(savedPath));
            System.out.println("이미지저장");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ReviewImgEntity imagefile = new ReviewImgEntity().builder()
                    .rimgFilename(origName)
                    .rimgOrder(imgOrder)
                    .reviewInfo(reviewInfo).build();
            rImgRepo.save(imagefile);
        }
    }

    public void addFilesImage(List<MultipartFile> files, ReviewInfoEntity reviewInfo) {

        for (Integer i = 0; i < files.size(); i++) {
            addFileImage(files.get(i), reviewInfo, i + 1);
        }
    }

    public Map<String, Object> deleteReview(HttpSession session, Long reviewSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        LoginUserVO user = (LoginUserVO) session.getAttribute("loginUser");

        UserInfoEntity userEntity = userRepo.findByUiSeq(user.getUserSeq());
        if (rInfoRepo.countByRiSeqAndUserInfo(reviewSeq, userEntity) == 0) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.FORBIDDEN);
            resultMap.put("msg", "자신이 작성한 주문이 아닙니다.");
            System.out.println(reviewSeq);
            System.out.println(userEntity.toString());
            return resultMap;
        } else {
            rInfoRepo.deleteById(reviewSeq);
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.FORBIDDEN);
            resultMap.put("msg", "삭제되었습니다.");
        }
        return resultMap;
    }

    public Map<String, Object> listReview(HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        return resultMap;
    }

}