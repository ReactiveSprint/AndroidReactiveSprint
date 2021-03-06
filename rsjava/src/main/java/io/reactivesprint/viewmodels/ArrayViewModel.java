package io.reactivesprint.viewmodels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import io.reactivesprint.rx.IMutableProperty;
import io.reactivesprint.rx.IProperty;
import io.reactivesprint.rx.MutableProperty;
import io.reactivesprint.rx.Property;
import io.reactivesprint.rx.functions.Func1Comparable;
import io.reactivesprint.rx.functions.Func1Comparator;

import static io.reactivesprint.Preconditions.checkNotNull;

/**
 * Created by Ahmad Baraka on 4/25/16.
 * {@link IArrayViewModel} implementation that has a constant {@code List<ViewModel>}
 */
public class ArrayViewModel<E extends IViewModel> extends ViewModel implements IArrayViewModel<E> {
    //region Fields

    private final Object lock = new Object();

    private final IMutableProperty<Integer> count = new MutableProperty<>(0);

    private final IProperty<Boolean> empty;

    private List<E> viewModels;

    private final IMutableProperty<CharSequence> localizedEmptyMessage = new MutableProperty<>(null);

    //endregion

    //region Constructors

    protected ArrayViewModel() {
        empty = new Property<>(count.getValue() <= 0, count.getObservable().skip(1)
                .distinctUntilChanged().map(new Func1Comparable<>(Func1Comparator.LESS_THAN_OR_EQUAL, 0)));
    }

    public ArrayViewModel(Collection<E> viewModels) {
        this();
        checkNotNull(viewModels, "viewModels");
        setViewModels(viewModels);
    }

    //endregion

    //region Iterable

    @Override
    public final Iterator<E> iterator() {
        synchronized (lock) {
            return viewModels.iterator();
        }
    }

    //endregion

    //region IArrayViewModel

    @Override
    public final IProperty<Integer> count() {
        return count;
    }

    @Override
    public final IProperty<Boolean> empty() {
        return empty;
    }

    @Override
    public IMutableProperty<CharSequence> localizedEmptyMessage() {
        return localizedEmptyMessage;
    }

    protected void setViewModels(Collection<E> viewModels) {
        if (viewModels == null) {
            viewModels = Collections.emptyList();
        }
        synchronized (lock) {
            this.viewModels = new ArrayList<>(viewModels);
            count.setValue(this.viewModels.size());
        }
    }

    @Override
    public final List<E> getViewModels() {
        synchronized (lock) {
            return viewModels;
        }
    }

    @Override
    public final int indexOf(E element) {
        checkNotNull(element, "element");
        synchronized (lock) {
            return viewModels.indexOf(element);
        }
    }

    @Override
    public final E getViewModel(int index) {
        synchronized (lock) {
            return viewModels.get(index);
        }
    }

    //endregion

    //region Object

    @Override
    public String toString() {
        return "ArrayViewModel{" +
                "count=" + count.getValue() +
                ", localizedEmptyMessage=" + localizedEmptyMessage.getValue() +
                "} " + super.toString();
    }

    //endregion
}
