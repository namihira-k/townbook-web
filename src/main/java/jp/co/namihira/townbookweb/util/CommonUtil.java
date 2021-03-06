package jp.co.namihira.townbookweb.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class CommonUtil {

	public static boolean contains(final String target, final String[] keywords) {
		return Stream.of(keywords).anyMatch(keyword -> target.contains(keyword));
	}
	
	@SafeVarargs
	public static <E> List<E> list(E... args) {
		return Arrays.asList(args);
	}
	
	public static <E> List<E> toList(Iterable<E> args) {
		final List<E> results = new ArrayList<>();
		args.forEach(results::add);
		return results;
	}
	
	public static <E> List<E> toList(E[] args) {
		return Arrays.asList(args);
	}
	
	
    public static <E> boolean isEmpty(Collection<E> c) {
        return c == null || c.size() == 0;
    }

    public static <E> boolean isNotEmpty(Collection<E> c) {
        return !isEmpty(c);
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
