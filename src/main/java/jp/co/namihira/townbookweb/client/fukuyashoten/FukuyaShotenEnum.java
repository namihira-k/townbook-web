package jp.co.namihira.townbookweb.client.fukuyashoten;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FukuyaShotenEnum {
	
	SHINJUKU("13", "22741", "福家書店 新宿サブナード店"),
	TOYOSHU("13", "22839", "豊洲PIT");
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
    
    public static FukuyaShotenEnum getShopbyName(final String name) {
    	Optional<FukuyaShotenEnum> result = Stream.of(FukuyaShotenEnum.values())
    			                                  .filter(shop -> shop.getName().contains(name))
    			                                  .findFirst();
    	return result.get();
    }

}
