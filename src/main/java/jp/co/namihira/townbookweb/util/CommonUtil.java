package jp.co.namihira.townbookweb.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommonUtil {

	public static <E> List<E> toList(Iterable<E> args) {
		final List<E> results = new ArrayList<>();
		args.forEach(results::add);
		return results;
	}
	
    public static <E> boolean isEmpty(Collection<E> c) {
        return c == null || c.size() == 0;
    }
    
    public static boolean isEmpty(final String str) {
        return str == null || str.isEmpty();
    }
    
    public static boolean isNotEmpty(final String str) {
    	return !isEmpty(str);
    }
    
    public static <T> List<T> list() {
        return new ArrayList<T>();
    }

}
