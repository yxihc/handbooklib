package com.taopao.oldbase.base.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.taopao.oldbase.R;
import com.taopao.oldbase.base.bean.Menstruation;
import com.taopao.oldbase.base.bean.PregnantJournal;
import com.taopao.oldbase.base.bean.QuestionItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lkn on 2017/5/15.
 */

public class JSONUtil {
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    //读取raw内宝宝信息json文件
    public static JSONArray getQuestionnaire1(Context context, int index) {
        JSONArray arrQuesionnaire = null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.hzfbquestionnaire);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            String json = new String(buffer, "utf-8");
            JSONArray arr = new JSONArray(json);
            JSONObject data = arr.getJSONObject(0);
            JSONArray jsonArray = data.getJSONArray("QArray");
            arrQuesionnaire = jsonArray.optJSONArray(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrQuesionnaire;
    }
    public static List<QuestionItem> getQuestionList(Context context, int index) throws JSONException, IOException {
        JSONArray questions = getQuestionnaire1(context, index);
        List<QuestionItem> questionList = new ArrayList<QuestionItem>();
        for (int i = 0; i < questions.length(); i++) {
            questionList.add(getQuestion(questions.getJSONObject(i)));
        }
        return questionList;
    }
    public static QuestionItem getQuestion(JSONObject jsonQuestion) throws JSONException {
        QuestionItem question = null;

        String title = jsonQuestion.getString("question");
        String correctAnswer = jsonQuestion.getString("answer");
        JSONArray options = jsonQuestion.getJSONArray("options");
        String[] opts = new String[options.length()];
        for (int i = 0; i < options.length(); i++) {
            opts[i] = options.getString(i);
        }

        question = new QuestionItem(correctAnswer, opts, title);
        return question;
    }


    public static List<PregnantJournal> getPregnantJournalList(JSONObject jsonResponse) throws JSONException {
        List<PregnantJournal> journalList = new ArrayList<PregnantJournal>();

        JSONArray journalArray = jsonResponse.getJSONArray("PregnantJournals");

        for (int i = 0; i < journalArray.length(); i++) {
            journalList.add(getPregnantJournal(journalArray.getJSONObject(i)));
        }

        return journalList;
    }

    public static Map<String, String> getHistoryScoreList(JSONObject response) throws JSONException {
        Map<String, String> historyScoreCollection = new HashMap<String, String>();
        JSONArray historyScores = response.getJSONArray("PregnantCourses");
        for (int i = 0; i < historyScores.length(); i++) {
            JSONObject historyScoreItem = historyScores.getJSONObject(i);
            historyScoreCollection.put(historyScoreItem.getInt("kechengid") + "", historyScoreItem.getString("grade"));
        }
        return historyScoreCollection;
    }

    public static PregnantJournal getPregnantJournal(JSONObject data) throws JSONException {
        PregnantJournal pregnantJournal = null;

        String id = data.getInt("id") + "";
        String title = data.getString("title");
        String content = data.getString("content");
        String journalType = data.getInt("journaltype") + "";
        String createTime = data.getString("createtime");
        String updateTime = data.getString("updatetime");
        String photo = data.getString("photo");
        String idno = data.getString("idcard");
        String url = data.getString("url");

        pregnantJournal = new PregnantJournal(id, photo, title, content, createTime, updateTime, journalType, idno, url);
        return pregnantJournal;
    }

    public static List<Menstruation> getMenstruationList(com.alibaba.fastjson.JSONObject response) {
        return JSON.parseArray(response.getJSONArray("menstruations").toString(), Menstruation.class);
    }

    public static String parseIatResult(String json) {
        StringBuffer ret = new StringBuffer();
        try {
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);

            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length(); i++) {
                // 转写结果词，默认使用第一个结果
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                JSONObject obj = items.getJSONObject(0);
                ret.append(obj.getString("w"));
//				如果需要多候选结果，解析数组其他字段
//				for(int j = 0; j < items.length(); j++)
//				{
//					JSONObject obj = items.getJSONObject(j);
//					ret.append(obj.getString("w"));
//				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret.toString();
    }


}
