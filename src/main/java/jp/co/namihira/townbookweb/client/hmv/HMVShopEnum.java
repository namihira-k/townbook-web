package jp.co.namihira.townbookweb.client.hmv;

import java.util.Optional;
import java.util.stream.Stream;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HMVShopEnum {

    SAPPORO("4", "20220", "HMV 札幌ステラプレイス", new String[]{"札幌"}),
    SENDAI("4", "21044", "HMV 仙台 E BeanS", new String[]{"仙台"}),
    TAKASAKI("10", "21913", "HMV イオンモール高崎", new String[] {"高崎"}),
    KAWAGUCHI("11", "22174", "HMV イオンモール川口前川", new String[]{"川口"}),
    OMIYA("11", "21987", "HMV 大宮アルシェ", new String[]{"大宮"}),
    YONO("11", "22025", "HMV イオンモール与野", new String[]{"与野"}),
    KASHIWANOHA("12", "29404", "HMV ららぽーと柏の葉", new String[]{"柏の葉"}),
    IKEBUKURO("13", "22513", "HMV エソラ池袋", new String[]{"池袋"}),
    SHIBUYA("13", "22715", "HMV&BOOKS SHIBUYA", new String[]{"SHIBUYA"}),
    HIBIYA("13", "22951", "HMV&BOOKS HIBIYA COTTAGE", new String[]{"HIBIYA"}),
    SHINJUKU("13", "22741", "HMV record shop 新宿ALTA", new String[]{"新宿"}),
    TOYOSU("13", "22839", "HMV ららぽーと豊洲 ", new String[]{"豊洲"}),
    TACHIKAWA("13", "22799", "HMV 立川", new String[]{"立川"}),
    YOKOHAMA_WP("14", "23368", "HMV 横浜ワールドポーターズ", new String[]{"横浜ワールドポーターズ"}),
    YOKOHAMA_LP("14", "23123", "HMV ららぽーと横浜", new String[]{"ららぽーと横浜"}),
    KAWASAKI("14", "23126", "HMV ラゾーナ川崎", new String[]{"ラゾーナ川崎"}),
    HAMAMATSU("22", "23479", "HMV イオンモール浜松市野", new String[]{"浜松"}),
    SAKEE("23", "24944", "HMV 栄", new String[]{"栄"}),
    KISOGAWA("23", "24902", "HMV イオンモール木曽川", new String[]{"木曽川"}),
    OKAZAKI("23", "24851", "HMV イオンモール岡崎", new String[]{"岡崎"}),
    SHINSAIBASHI("27", "25981", "HMV&BOOKS SHINSAIBASHI", new String[]{"SHINSAIBASHI"}),
    ABENO("27", "26062", "HMV SOPT あべのキューズモール", new String[]{"あべの"}),
    NISHINOMIYA("28", "26488", "HMV 阪急西宮ガーデンズ", new String[]{"西宮"}),
    OKAYAMA("33", "27051", "HMV イオンモール岡山", new String[]{"岡山"}),
    HAKATA("40", "28283", "HMV&BOOKS HAKATA", new String[]{"HAKATA"}),
    FUKUTSU("40", "28305", "HMV イオンモール福津", new String[]{"福津"}),
    OTHER("13", "22828", "HMV", new String[]{"その他"});
    
    private final String prefectureCode;
    private final String stationCode;
    private final String name;
    private final String[] identifier;

    public static HMVShopEnum getShopbyName(final String name) {
        Optional<HMVShopEnum> result = Stream.of(HMVShopEnum.values())
                                             .filter(shop -> CommonUtil.contains(name, shop.getIdentifier()))
                                             .findFirst();
        return result.orElse(HMVShopEnum.OTHER);
    }
    
}
