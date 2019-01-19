package jp.co.namihira.townbookweb.client.fukuyashoten;

import java.util.Optional;
import java.util.stream.Stream;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FukuyaShotenEnum {
	
	SHINJUKU("13", "22741", "福家書店 新宿サブナード店", new String[]{"新宿"}),
	TOYOSHU("13", "22839", "豊洲PIT", new String[]{"豊洲"}),
	OTHER("13", "22828", "福家書店", new String[]{"その他"});
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
    private final String[] identifier;
    
    public static FukuyaShotenEnum getShopbyName(final String name) {
    	Optional<FukuyaShotenEnum> result = Stream.of(FukuyaShotenEnum.values())
    			                                  .filter(shop -> CommonUtil.contains(name, shop.getIdentifier()))
    			                                  .findFirst();
    	return result.orElse(FukuyaShotenEnum.OTHER);
    }

}
