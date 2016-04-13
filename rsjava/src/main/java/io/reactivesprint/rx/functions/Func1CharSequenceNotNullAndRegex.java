package io.reactivesprint.rx.functions;

import rx.functions.Func1;

/**
 * Created by Ahmad Baraka on 4/12/16.
 */
public class Func1CharSequenceNotNullAndRegex<T extends CharSequence> implements Func1<T, Boolean> {

    private final String regex;

    public Func1CharSequenceNotNullAndRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public Boolean call(T t) {
        return t != null && t.toString().matches(regex);
    }
}
