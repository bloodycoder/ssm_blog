package com.personalblog.service;

import com.personalblog.model.Diary;

import java.util.List;

public interface DiaryService {

    void writediary(Diary diary);

    List<Diary> selectAllDiary();

    void deleteDiaryById(int diaryid);

}
