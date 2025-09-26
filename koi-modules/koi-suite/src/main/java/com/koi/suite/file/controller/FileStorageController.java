package com.koi.suite.file.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.annotation.log.AccessLog;
import com.koi.suite.file.domain.dto.req.FileStoragePageReq;
import com.koi.suite.file.domain.dto.resp.FileStoragePageResp;
import com.koi.suite.file.domain.entity.FileStorage;
import com.koi.suite.file.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Validated
@RestController
@RequestMapping("/file-storage")
@RequiredArgsConstructor
@Tag(name = "文件存储", description = "文件存储")
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @GetMapping("/token")
    @Parameter(description = "文件名", name = "originName", in = ParameterIn.QUERY)
    @Operation(summary = "上传Token获取")
    public void getToken(String key, @RequestParam(defaultValue = "true") boolean random) {
        // return Result.ok(storageOperation.token(key, random));
    }

    // @IgnoreAuthorize
    // @Parameters({@Parameter(name = "id", description = "文件ID", in = ParameterIn.PATH),})
    // @GetMapping("/{id}/download")
    // @Operation(summary = "文件下载")
    // public void download(@PathVariable String id, HttpServletResponse response) {
    // final FileEntity file = this.fileService.getById(id);
    // if (file == null) {
    // return;
    // }
    // final DownloadResponse download = storageOperation.download(file.getTargetName());
    // response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    // response.setContentType("application/octet-stream");
    // response.setHeader("Content-Disposition", "attachment; filename=" + file.getOriginName());
    // try (ServletOutputStream outputStream = response.getOutputStream()) {
    // outputStream.write(IoUtil.readBytes(download.getInputStream()));
    // } catch (Exception e) {
    // log.error("文件预览失败", e);
    // }
    // }
    //
    // @IgnoreAuthorize
    // @Parameters({@Parameter(name = "id", description = "文件KEY", in = ParameterIn.PATH),})
    // @GetMapping("/{id}/preview")
    // @Operation(summary = "文件预览")
    // public ResponseEntity<Resource> preview(@PathVariable String id) {
    // final FileEntity file = this.fileService.getById(id);
    // if (file == null) {
    // return ResponseEntity.ok(null);
    // }
    // final DownloadResponse download = storageOperation.download(file.getTargetName());
    // HttpHeaders headers = new HttpHeaders();
    // headers.add(HttpHeaders.CONTENT_TYPE, file.getContentType());
    // return new ResponseEntity<>(new InputStreamResource(download.getInputStream()), headers, HttpStatus.OK);
    // }

    // @PostMapping("/ids_query")
    // @Operation(summary = "通过ID查询文件信息 - [Aaron] - [DONE]")
    // public List<FileEntity> batchQueryByIds(@RequestBody BatchKey<String> param) {
    // if (Objects.isNull(param) || CollUtil.isEmpty(param.getIds())) {
    // return Lists.newArrayList();
    // }
    // return fileService.list(Wraps.<FileEntity>lbQ().in(FileEntity::getId, param.getIds()));
    // }

    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "上传文件")
    @AccessLog(module = "文件存储", description = "上传文件")
    public FileStorage upload(@RequestParam("file") MultipartFile file) {
        return fileStorageService.upload(file);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public IPage<FileStoragePageResp> pageList(FileStoragePageReq req) {
        return fileStorageService.pageList(req);
    }

    @PostMapping("/{id}")
    @Operation(summary = "删除文件", description = "删除文件")
    @AccessLog(module = "文件存储", description = "删除文件")
    public void delete(@PathVariable Long id) {
        fileStorageService.delete(id);
    }

    @PutMapping("/rename/{id}/{originName}")
    @Operation(summary = "文件重命名", description = "文件重命名")
    @AccessLog(module = "文件存储", description = "文件重命名")
    public void rename(@PathVariable Long id, @PathVariable String originName) {
        fileStorageService.rename(id, originName);
    }

    @PostMapping("/upload-image")
    @Operation(summary = "上传图片", description = "上传图片")
    public FileStorage uploadImage(@RequestParam("file") MultipartFile file) {
        return fileStorageService.uploadImage(file);
    }

}
