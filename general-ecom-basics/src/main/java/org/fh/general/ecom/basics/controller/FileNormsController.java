package org.fh.general.ecom.basics.controller;

import org.apache.commons.lang.StringUtils;
import org.fh.general.ecom.basics.service.FileNormsService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.files.*;
import org.fh.general.ecom.common.img.ImageUtil;
import org.fh.general.ecom.common.po.basics.files.OutputFileNormsPO;
import org.fh.general.ecom.common.upload.FastDFSConfig;
import org.fh.general.ecom.common.upload.UploadService;
import org.fh.general.ecom.common.upload.UploadServiceImpl;
import org.fh.general.ecom.common.vo.basics.files.OutputFileNormsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hlp
 * @since 2018-09-28
 */
@RestController
public class FileNormsController {


    @Autowired
    private FileNormsService  fileNormsService;
    UploadService uploadService = new UploadServiceImpl();

    @RequestMapping("/upload")
    public BaseVO upload(@RequestParam("file") List<MultipartFile> multipartFiles, @RequestParam("branch") String branch,
                         @RequestParam("fileFlag") String fileFlag  ) {
        BaseVO  baseVO=new BaseVO();
        if (multipartFiles==null || multipartFiles.isEmpty()) {
            baseVO.setBusAlert("上传失败，请选择文件");
            return baseVO;
        }
        String filePath = "";            //返回路径
        UploadService uploadService = new UploadServiceImpl();
        if (null != multipartFiles) {
            //取得request中的所有文件名
            List<String> paths = new ArrayList<String>();
            for(MultipartFile file: multipartFiles){
                //取得上传文件
                String fileName = file.getOriginalFilename();
                String extUpp = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
                Long fileSize = (file.getSize() / 1024);
                ByteArrayInputStream in = null; // 将b作为输入流；
                BufferedImage image = null;
                byte[] saveMinPhoto = null;
                try {
                    if (file != null) {
                        in = new ByteArrayInputStream(file.getBytes());
                        image = ImageIO.read(in);
                        if (StringUtils.isNotEmpty(fileFlag)) {
                            InputFileNormsDTO dto=new InputFileNormsDTO();
                            dto.setBranch(branch);
                            dto.setFileFlag(fileFlag);
                            OutputFileNormsDTO fileNormsDTO = this.fileNormsService.checkFileFlag(dto);
                            if(fileNormsDTO!=null){
                                if (checkFileNorms(baseVO, file, image, fileNormsDTO)) return baseVO;
                            }
                            }
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //上传
                if (file != null && image != null) {
                    try {
                        saveMinPhoto = file.getBytes();
                        if (extUpp.matches("^[(JPG)|(PNG)|(BMP)|(JPEG)]+$")) {
                            if (fileSize > 100) {
                                saveMinPhoto = ImageUtil.reduceImg(image);
                                filePath = uploadService.uploadFile(saveMinPhoto, fileName);
                            } else {
                                filePath = uploadService.uploadFile(saveMinPhoto, fileName);
                            }
                        } else {
                            filePath = uploadService.uploadFile(saveMinPhoto, fileName);
                        }
                        paths.add(filePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    if(file != null){
                        try {
                            saveMinPhoto = file.getBytes();
                            filePath = uploadService.uploadFile(saveMinPhoto, fileName);
                            paths.add(filePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            OutputFileUploadDTO response = new OutputFileUploadDTO();
            FastDFSConfig config = new FastDFSConfig();
            response.setHttpUrl(config.getFastdfsUrl());
            response.setList(paths);
            baseVO.setData(response);
            baseVO.success();
        }
        return baseVO;
    }

    private boolean checkFileNorms(BaseVO baseVO, MultipartFile file, BufferedImage image, OutputFileNormsDTO fileNormsDTO) {
        int width = 0;
        int height = 0;
        if(!fileNormsDTO.getFileFlag().equals("video")){
            width = image.getWidth();
            height = image.getHeight();
        }
        if ((file.getSize() / 1024) > fileNormsDTO.getFileSize()) {
            if(fileNormsDTO.getFileFlag().equals("video")){
                baseVO.setBusAlert("视频不能大于" + fileNormsDTO.getFileSize()/1024 + "MB!");
                return true;
            }else{
                baseVO.setBusAlert("图片不能大于" + fileNormsDTO.getFileSize() + "KB!");
                return true;
            }
        }
        if (width > fileNormsDTO.getFileWidth()) {
            baseVO.setBusAlert("图片宽度不能大于" + fileNormsDTO.getFileWidth() + "px!");
            return true;
        }
        if (height > fileNormsDTO.getFileHeight()) {
            baseVO.setBusAlert("图片高度不能大于" + fileNormsDTO.getFileHeight() + "px!");
            return true;
        }
        return false;
    }


    @RequestMapping("FN00001")
    public PagingVO findPage(InputFileNormsListDTO dto){
        PagingVO pageVO = new PagingVO();
        OutputFileNormsListDTO response =this.fileNormsService.findPage(dto);
        List<OutputFileNormsVO> listvo= new ArrayList<OutputFileNormsVO>();
        List<OutputFileNormsPO>  list = response.getList();
        if(list!=null && list.size()>0) {
            list.forEach((OutputFileNormsPO temp) -> {
                OutputFileNormsVO vo = new OutputFileNormsVO();
                BeanUtils.copyProperties(temp, vo);
                listvo.add(vo);
            });
            pageVO.success(listvo, response.getPageInfo());
        }else{
            pageVO.noData();
        }
        return pageVO;
    }


    @RequestMapping("FN00002")
    public BaseVO addFileNorms(InputFileNormsAddDTO dto){
        BaseVO baseVo = new BaseVO();
        String msg  =this.fileNormsService.addEntity(dto);
        if(StringUtils.isNotEmpty(msg)){
            baseVo.setBusAlert(msg);
            return  baseVo;
        }
        baseVo.success();
        return baseVo;
    }


    @RequestMapping("FN00003")
    public BaseVO findDetail(InputFileNormsDetaiDTO dto){
        BaseVO baseVo = new BaseVO();
        OutputFileNormsDetailDTO  outputFileNormsDetailDTO  =this.fileNormsService.findEntity(dto);
        if(outputFileNormsDetailDTO==null){
            baseVo.noData();
            return  baseVo;
        }
        baseVo.setData(outputFileNormsDetailDTO);
        baseVo.success();
        return baseVo;
    }

    @RequestMapping("FN00004")
    public BaseVO updateDetail(InputFileNormsUpdateDTO dto){
        BaseVO baseVo = new BaseVO();
        String msg  =this.fileNormsService.updateDetail(dto);
        if(StringUtils.isNotEmpty(msg)){
            baseVo.setBusAlert(msg);
            return  baseVo;
        }
        baseVo.success();
        return baseVo;
    }



    @RequestMapping("FN00005")
    public BaseVO delDictionary(InputFilesNormsDelDTO dto) {
        BaseVO baseVo = new BaseVO();
        String msg = this.fileNormsService.delFileNorms(dto);
        if(StringUtils.isNotEmpty(msg)){
            baseVo.setBusAlert(msg);
            return baseVo;
        }
        baseVo.success();
        return baseVo;
    }
}
