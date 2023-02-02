package com.team5.justdoeat.menu.api;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team5.justdoeat.menu.entity.MenuCateConnectEntity;
import com.team5.justdoeat.menu.entity.MenuImageInfoEntity;
import com.team5.justdoeat.menu.entity.MenuInfoEntity;
import com.team5.justdoeat.menu.entity.MenuOptionEntity;
import com.team5.justdoeat.menu.entity.MenuSearchEntity;
import com.team5.justdoeat.menu.repository.MenuCateConnectRepository;
import com.team5.justdoeat.menu.repository.MenuImageInfoRepository;
import com.team5.justdoeat.menu.repository.MenuInfoRepository;
import com.team5.justdoeat.menu.repository.MenuOptionRepository;
import com.team5.justdoeat.menu.repository.MenuSearchRepository;
import com.team5.justdoeat.store.repository.StoreInfoRepository;
import com.team5.justdoeat.menu.service.MenuService;
import com.team5.justdoeat.menu.service.MenuService;
import com.team5.justdoeat.store.repository.StoreInfoRepository;

import org.springframework.core.io.Resource;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/menu")
public class MenuController {
  @Autowired MenuService mService;
  @Autowired MenuInfoRepository mRepo;
  @Autowired StoreInfoRepository sRepo;
  @Autowired MenuOptionRepository optionRepo;
  @Autowired MenuCateConnectRepository cateConnectRepo;
  @Value("${file.image.menu}") String menu_img;
  @Autowired MenuImageInfoRepository imageRepo;

  // @PutMapping("/addmenu")
  // public String addMenuInfo(@RequestBody MenuVO data) {
  //   MenuInfoEntity entity = MenuInfoEntity.builder()
  //   .miName(data.getName())
  //   .miAdditionalEx(data.getAdditional())
  //   .miPrice(data.getPrice())
  //   .miImg(data.getImg())
  //   .storeInfo(
  //     sRepo.findBySiName(data.getStore())
  //   )
  //   .build();
  //   mRepo.save(entity);
  //   return "성공";
  // }

  // @PutMapping("/addOption")
  // public String addOption(@RequestBody MenuOptionVO data) {
  //   MenuOptionEntity entity = MenuOptionEntity.builder()
  //   .moName(data.getName())
  //   .moPrice(data.getPrice())
  //   .menuInfo((mRepo.findByMiName(data.getMenu())))
  //   .build();
  //   if(mRepo.findByMiName(data.getMenu()) == null) {
  //     return "실패";
  //   }
  //   else{
  //     optionRepo.save(entity);
  //     return "성공";
  //   }
  // } // join key 걸었을때 메뉴 이름 입력하여 시퀀스 불러오는 만들기

  @PutMapping("/upload")
  public ResponseEntity<Object> putImage(@RequestPart MultipartFile file) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    Path folderLocation = Paths.get(menu_img);
    String originFileName = file.getOriginalFilename();
    String[] split = originFileName.split("\\.");
    String ext = split[split.length - 1];
    String filename = "";
    for (int i = 0; i < split.length - 1; i++) {
      filename += split[i];
    }
    String saveFilename = "menu" + "_";
    Calendar c = Calendar.getInstance();
    saveFilename += c.getTimeInMillis() + "." + ext;
    Path targerFile = folderLocation.resolve(saveFilename);
    try {
      Files.copy(file.getInputStream(), targerFile, StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      e.printStackTrace();
    }
    MenuImageInfoEntity data = new MenuImageInfoEntity();
    data.setMimgFilename(saveFilename);
    data.setMimgUri(filename);
    mService.addmenuimageInfo(data);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

    @GetMapping("/image/{mimgUri}")
    public ResponseEntity<Resource> getImage (@PathVariable String mimgUri, HttpServletRequest request) throws Exception {
      Path folderLocation = Paths.get(menu_img);
      String filename = mService.getIiFileNameByUri(mimgUri);
      String[] split = filename.split("\\.");
      String ext = split[split.length - 1];
      String exportName = mimgUri + "." + ext;
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


    @Autowired MenuSearchRepository searchRepo;
    @GetMapping("/search")
    public Map<String, Object> getMenuList(@RequestParam @Nullable String keyword) {
      Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
      if(keyword == null) keyword = "";
      List<MenuSearchEntity> storeList = searchRepo.searchStoreAndMenu(keyword);
      resultMap.put("keyword", keyword);
      resultMap.put("totalCount", storeList.size());
      resultMap.put("storeList", storeList);
      return resultMap;
  }

  // @GetMapping("/option")
  //   public Map<String, Object> getCate() {
  //   Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
  //   resultMap.put("list", cateConnectRepo.findAll());
  //   return resultMap;
  // }

  @GetMapping("/list")
  public Map<String, Object> getMenuList(@RequestParam Long storeNo) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap.put("list", mRepo.findByMiSiSeq(storeNo));
    return resultMap;
  }

  @GetMapping("/option")
  public Map<String, Object> getOptionList(@RequestParam Long menuNo) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap.put("list", optionRepo.findByMoMiSeq(menuNo));
    return resultMap;
  }
  
  @DeleteMapping("/delete")
  public Map<String, Object> deleteMember(@RequestParam Long storeNo) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    MenuInfoEntity entity = mRepo.findByMiSeq(storeNo);
    if(entity == null) {
      resultMap.put("status", false);
      resultMap.put("message", "번호를 다시 확인해주세요.");
    }
    else {
      resultMap.put("status", true);
      resultMap.put("message", "삭제되었습니다.");
    }
    return resultMap;
  }

  @PatchMapping("/update")
  public Map<String,Object> updateMember(@RequestParam Long storeNo, @RequestBody MenuInfoEntity data) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    MenuInfoEntity entity = mRepo.findByMiSeq(storeNo);
    if(entity == null) {
      resultMap.put("status", false);
      resultMap.put("message", "번호를 다시 확인해주세요.");
    }
    else {
      entity.setMiName(data.getMiName());
      entity.setMiPrice(data.getMiPrice());
      entity.setMiImg(data.getMiImg());
      mRepo.save(entity);
      resultMap.put("status", true);
      resultMap.put("message", "수정되었습니다.");
    }
    return resultMap;
  }
}
