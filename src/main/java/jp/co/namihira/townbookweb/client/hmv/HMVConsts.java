package jp.co.namihira.townbookweb.client.hmv;

import java.util.List;

import jp.co.namihira.townbookweb.util.CommonUtil;

public class HMVConsts {

    private static final List<String> KEYWORDS_FREE = CommonUtil.list(
            "観覧フリー",
            "観覧はフリー",
            "フリー観覧",
            "観覧は無料",
            "イベントはフリー");

    public static List<String> getKeywordsOfFree(){
        return KEYWORDS_FREE;
    }

}
