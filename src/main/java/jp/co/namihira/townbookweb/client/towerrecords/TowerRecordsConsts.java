package jp.co.namihira.townbookweb.client.towerrecords;

import java.util.List;

import jp.co.namihira.townbookweb.util.CommonUtil;

public class TowerRecordsConsts {

    private static final List<String> KEYWORDS_FREE = CommonUtil.list(
            "観覧フリー",
            "観覧はフリー",
            "入場FREE",
            "観覧自由",
            "無料");

    public static List<String> getKeywordsOfFree(){
        return KEYWORDS_FREE;
    }

}
