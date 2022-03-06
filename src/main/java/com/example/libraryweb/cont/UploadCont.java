package com.example.libraryweb.cont;

import com.example.libraryweb.svc.BoardSVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("library")
public class UploadCont {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    BoardSVC bSvc;

    @PostMapping("upload")
    @ResponseBody
    public Map<String, Object> uploadajax(@RequestParam("files") MultipartFile[] mfiles,
                                          @RequestParam("section") String section,
                                          @RequestParam("title") String title,
                                          @RequestParam("content") String content,
                                          HttpServletRequest request
    ) {
        int pid = bSvc.boardWrite(section, title, content);
        ServletContext context = request.getServletContext();
        String savePath = context.getRealPath("/WEB-INF/upload");
        //savePath = "/Users/lucas/eclipse-workspace/libraryWeb/src/main/resources/static/upload";
        Random rd = new Random();
        Map<String, Object> map = new HashMap<>();
        try {
            String saveName = null;
            List<String> saveFileList = new ArrayList<>();
            List<Integer> uploadList = new ArrayList<>();
            for (int i = 0; i < mfiles.length; i++) {
                String rdName = Integer.toString(rd.nextInt(9999)) + "_"
                        + mfiles[i].getOriginalFilename();
                saveName = savePath + "/"
                        + rdName;
                //파일 저장하는 로직
                mfiles[i].transferTo(new File(saveName));
                //System.out.println("***경로***"+context.getResource("WEB-INF/upload"));

          /*파일 클레스에서 상위폴더를 구하는 메서드 이용하여 상대주소처럼 운용
          파일경로를 / 로 split 해서 포문 돌려서 4단계위를 구하고 다시 합친후
          resource/static/upload 를 합쳐서 반응형 업로드 패스를 만든다*/
                File file = new File(saveName);
                String[] token = saveName.split("/");
                String reactPath = "";
                for (int r = 0; r < token.length - 4; r++) {
                    reactPath += "/" + token[r];
                }
                String filePath = reactPath + "/resources/static/upload/" + rdName;
                String uploadUrl = "/upload/" + rdName;
                File newFile = new File(filePath);
                FileInputStream input = new FileInputStream(file);
                FileOutputStream output = new FileOutputStream(newFile);
                byte[] buf = new byte[1024];
                int readData;
                while ((readData = input.read(buf)) > 0) {
                    output.write(buf, 0, readData);
                }
                input.close();
                output.close();
                saveFileList.add(mfiles[i].getOriginalFilename());
                // 유저내임하고 경로를 db 에 저장한다.
                uploadList.add((Integer) bSvc.upload(pid, uploadUrl));
            }
            String msg = String.format("파일(%d)개가 업로드 되었습니다.", mfiles.length);
            map.put("msg", msg);
            // 일단 단일 파일 루트 나중에 복수를
            map.put("file", saveFileList);
            map.put("uploadId", uploadList);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "파일 저장 실패:");
            return map;
        }
    }

    @GetMapping("download/{filename}")//다운로드 요청 패스배리어블이 다루기 편하다
    public ResponseEntity<Resource> download(
            HttpServletRequest request,
            @PathVariable String filename) {
        Resource resource = resourceLoader.getResource("WEB-INF/upload/" + filename);
        //파일 경로 리소스로더로 리소를 구한다. 파일이 저장되는 절대경로를 알수 있다.
        System.out.println("파일명:" + resource.getFilename());
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            // 절대경로를 구해서 브라우저에서 서버로 전달되는 데이터타입 (알아야지 유저가 형식을 알수 있음)
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
            //타입이 없을때! (이것이 없으면 브라우저에서 열려버림)
            //서버디스크에서 이용자의 디스크로 스트림함
            //즉 브라우저에서 열지 않고 다운로드 창을 만들기 위해
        }
        return ResponseEntity.ok()
                //스트림을 이용하여 브라우저에게 전달하는 로직_스프링에서 지원
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        //http 프로토콜 형식 다운로드창에 파일이름 표시와 브라우저에서 열리지 않고 다운로드 창을 띄움
        //ok() 바디를 만들어준다. ~@Responsebody
        // 변경사항은 파일이름 바꾸는 부분
    }
}

