package diary.weather.controller;

import diary.weather.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import diary.weather.domain.Diary;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "다이어리 컨트롤러", description = "Diary Controller")
@RestController
public class DiaryController {
    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }


    @Operation(summary = "다이어리 생성", description = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody String text) {
        diaryService.createDiary(date, text);
    }


    // 단일 날짜 조회
    @Operation(summary = "단일 날짜 조회", description = "선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        if (date.isAfter(LocalDate.ofYearDay(2024, 1))) {
//            throw new InvalidDate();
//        }
        return diaryService.readDiary(date);
    }

    // 범위 날짜 조회
    @Operation(summary = "범위 날짜 조회", description = "선택한 기간의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@Parameter(name = "조회할 기간의 첫 번째 날", example = "yyyy-MM-dd") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@Parameter(name = "조회할 기간의 마지막 날", example = "2023-04-01") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    // 일기 수정하기
    @Operation(summary = "일기 수정하기", description = "일기를 수정합니다.")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "일기 삭제", description = "일기를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
