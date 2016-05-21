package io.reactivesprint.views;

import io.reactivesprint.viewmodels.IFetchedArrayViewModel;
import io.reactivesprint.viewmodels.IViewModel;

/**
 * Created by Ahmad Baraka on 5/21/16.
 */
public interface IFetchedArrayViewBinder
        <VM extends IViewModel, AVM extends IFetchedArrayViewModel, V extends IFetchedArrayView<VM, AVM>>
        extends IArrayViewBinder<VM, AVM, V> {
    /**
     * Binds {@link IFetchedArrayViewModel#refreshing()} to {@link IFetchedArrayView#presentRefreshing(boolean)}
     */
    void bindRefreshing(AVM arrayViewModel);

    /**
     * Binds {@link IFetchedArrayViewModel#fetchingNextPage()} to {@link IFetchedArrayView#presentFetchingNextPage(boolean)}
     */
    void bindFetchingNextPage(AVM arrayViewModel);
}
