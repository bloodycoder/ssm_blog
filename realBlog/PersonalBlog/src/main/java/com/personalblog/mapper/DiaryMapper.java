package com.personalblog.mapper;

import com.personalblog.model.Diary;

import java.util.List;

public interface DiaryMapper {

    void writediary(Diary diary);

    List<Diary> selectAllDiary();

    void deleteDiaryById(int diaryid);

}
