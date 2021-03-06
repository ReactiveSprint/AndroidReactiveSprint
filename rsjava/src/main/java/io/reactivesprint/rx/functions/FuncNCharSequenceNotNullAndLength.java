package io.reactivesprint.rx.functions;

import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.functions.FuncN;

/**
 * Created by Ahmad Baraka on 4/12/16.
 * <p/>
 * {@link FuncN} which returns true when all input objects are not null
 * and {@link CharSequence#length()} {@code >= minimumLength}
 * <p/>
 * All input objects must be instance of {@link CharSequence}.
 */
public final class FuncNCharSequenceNotNullAndLength implements
        Func2<Boolean, Boolean, Boolean>,
        Func3<Boolean, Boolean, Boolean, Boolean>,
        Func4<Boolean, Boolean, Boolean, Boolean, Boolean>,
        Func5<Boolean, Boolean, Boolean, Boolean, Boolean, Boolean>,
        Func6<Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean>,
        Func7<Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean>,
        Func8<Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean>,
        Func9<Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean>,
        FuncN<Boolean> {

    private final int minimumLength;

    /**
     * Creates instance with {@code minimumLength = 1}
     */
    public FuncNCharSequenceNotNullAndLength() {
        this(1);
    }

    public FuncNCharSequenceNotNullAndLength(int minimumLength) {
        this.minimumLength = minimumLength;
    }

    @Override
    public Boolean call(Boolean aBoolean, Boolean aBoolean2) {
        return call(new Object[]{aBoolean, aBoolean2});
    }

    @Override
    public Boolean call(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3) {
        return call(new Object[]{aBoolean, aBoolean2, aBoolean3});
    }

    @Override
    public Boolean call(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3, Boolean aBoolean4) {
        return call(new Object[]{aBoolean, aBoolean2, aBoolean3, aBoolean4});
    }

    @Override
    public Boolean call(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3, Boolean aBoolean4, Boolean aBoolean5) {
        return call(new Object[]{aBoolean, aBoolean2, aBoolean3, aBoolean4, aBoolean5});
    }

    @Override
    public Boolean call(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3, Boolean aBoolean4, Boolean aBoolean5, Boolean aBoolean6) {
        return call(new Object[]{aBoolean, aBoolean2, aBoolean3, aBoolean4, aBoolean5, aBoolean6});
    }

    @Override
    public Boolean call(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3, Boolean aBoolean4, Boolean aBoolean5, Boolean aBoolean6, Boolean aBoolean7) {
        return call(new Object[]{aBoolean, aBoolean2, aBoolean3, aBoolean4, aBoolean5, aBoolean6, aBoolean7});
    }

    @Override
    public Boolean call(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3, Boolean aBoolean4, Boolean aBoolean5, Boolean aBoolean6, Boolean aBoolean7, Boolean aBoolean8) {
        return call(new Object[]{aBoolean, aBoolean2, aBoolean3, aBoolean4, aBoolean5, aBoolean6, aBoolean7, aBoolean8});
    }

    @Override
    public Boolean call(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3, Boolean aBoolean4, Boolean aBoolean5, Boolean aBoolean6, Boolean aBoolean7, Boolean aBoolean8, Boolean aBoolean9) {
        return call(new Object[]{aBoolean, aBoolean2, aBoolean3, aBoolean4, aBoolean5, aBoolean6, aBoolean7, aBoolean8, aBoolean9});
    }

    @Override
    public Boolean call(Object... args) {
        if (args == null) {
            return false;
        }

        for (Object object : args) {
            if (object == null) {
                return false;
            }

            if (object instanceof CharSequence) {
                if (((CharSequence) object).length() < minimumLength) {
                    return false;
                }
            } else {
                throw new AssertionError(getClass().getName() + " expects objects to be instance of CharSequence.");
            }
        }
        return true;
    }
}
