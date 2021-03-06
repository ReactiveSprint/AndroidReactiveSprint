package io.reactivesprint.viewmodels;

import java.util.Collection;

/**
 * Created by Ahmad Baraka on 4/1/16.
 * {@link ArrayViewModel} implementation that allows {@link #setViewModels(Collection)}
 */
public class MutableArrayViewModel<E extends IViewModel> extends ArrayViewModel<E> {
    public MutableArrayViewModel() {
    }

    public MutableArrayViewModel(Collection<E> viewModels) {
        super(viewModels);
    }

    @Override
    public void setViewModels(Collection<E> viewModels) {
        super.setViewModels(viewModels);
    }
}
