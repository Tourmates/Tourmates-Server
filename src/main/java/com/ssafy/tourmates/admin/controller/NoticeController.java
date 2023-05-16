package com.ssafy.tourmates.admin.controller;

import com.ssafy.tourmates.admin.controller.dto.admin.LoginAdmin;
import com.ssafy.tourmates.admin.controller.dto.notice.form.AddNoticeForm;
import com.ssafy.tourmates.admin.controller.dto.notice.form.EditNoticeForm;
import com.ssafy.tourmates.admin.controller.dto.notice.response.AdminNoticeResponse;
import com.ssafy.tourmates.admin.notice.service.NoticeQueryService;
import com.ssafy.tourmates.admin.notice.service.NoticeService;
import com.ssafy.tourmates.admin.notice.service.dto.AddNoticeDto;
import com.ssafy.tourmates.admin.notice.service.dto.EditNoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/intranet/notices")
public class NoticeController {

    // TODO: 2023/05/16 세부 수정
    //관리자 권한: 복사
    private final NoticeService noticeService;
    private final NoticeQueryService noticeQueryService;

    @GetMapping
    public String noticeList(
            @ModelAttribute("addForm") AddNoticeForm addForm,
            @ModelAttribute("editForm") EditNoticeForm editForm,
            Model model) {
        List<AdminNoticeResponse> notices = noticeQueryService.searchAdminNotices();
        model.addAttribute("notices", notices);
        return "noticeList";
    }

    @PostMapping("/register")
    public String registerNotice(
            @ModelAttribute AddNoticeForm form,
            @SessionAttribute(name = "loginAdmin") LoginAdmin loginAdmin
    ) {
        AddNoticeDto dto = AddNoticeDto.builder()
                .pin(form.getPin() == null ? "0" : "1")
                .title(form.getTitle())
                .content(form.getContent())
                .build();

        Long noticeId = noticeService.registerNotice(loginAdmin.getLoginId(), dto);
        log.debug("noticeId={}", noticeId);
        return "redirect:/intranet/notices";
    }

    @PostMapping("/edit")
    public String editNotice(
            @ModelAttribute EditNoticeForm form
    ) {
        EditNoticeDto dto = EditNoticeDto.builder()
                .pin(form.getPin().equals("true") ? "1" : "0")
                .title(form.getTitle())
                .content(form.getContent())
                .build();
        Long noticeId = noticeService.editNotice(form.getNoticeId(), dto);
        log.debug("noticeId={}", noticeId);
        return "redirect:/intranet/notices";
    }

    @PostMapping("/remove")
    public String removeNotice(@RequestBody List<Long> noticeIds) {
        Long count = noticeQueryService.bulkDeActive(noticeIds);
        log.debug("count={}", count);
        return "redirect:/intranet/notices";
    }

    @GetMapping("/excel/download")
    public void excelDownload(HttpServletResponse response) throws IOException {
        log.debug("excelDownload");

        List<AdminNoticeResponse> notices = noticeQueryService.searchAdminNotices();

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        //Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell = row.createCell(1);
        cell.setCellValue("제목");
        cell = row.createCell(2);
        cell.setCellValue("작성자");
        cell = row.createCell(3);
        cell.setCellValue("작성일");
        cell = row.createCell(4);
        cell.setCellValue("삭제여부");

        //Body
        for (AdminNoticeResponse notice : notices) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(notice.getNoticeId());
            cell = row.createCell(1);
            cell.setCellValue(notice.getTitle());
            cell = row.createCell(2);
            cell.setCellValue(notice.getWriter());
            cell = row.createCell(3);
            cell.setCellValue(notice.getCreatedDate());
            cell = row.createCell(4);
            cell.setCellValue(notice.getActive());
        }

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String fileName = "notices" + date + ".xlsx";

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        //Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
}
