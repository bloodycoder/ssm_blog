package com.personalblog.service;

import com.personalblog.mapper.DiaryMapper;
import com.personalblog.model.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryMapper diarymapper;

    public void writediary(Diary diary) {
        diarymapper.writediary(diary);

    }

    public List<Diary> selectAllDiary() {
        List<Diary> diarys = this.diarymapper.selectAllDiary();
        return diarys;
    }

    public void deleteDiaryById(int diaryid) {
        diarymapper.deleteDiaryById(diaryid);

    }

}
