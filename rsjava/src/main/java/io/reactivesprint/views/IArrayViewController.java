package io.reactivesprint.views;

import io.reactivesprint.viewmodels.IArrayViewModel;
import io.reactivesprint.viewmodels.IViewModel;

/**
 * Created by Ahmad Baraka on 4/6/16.
 * Represents a View Controller that wraps {@link IArrayViewModel}
 */
public interface IArrayViewController<VM extends IViewModel, AVM extends IArrayViewModel> extends IViewController<VM> {
    /**
     * @return a ViewModel used as {@link IArrayViewModel}
     * This could return a different value than {@link #getViewModel()}
     */
    AVM getArrayViewModel();

    /**
     * Binds {@link IArrayViewModel#count()} to {@link #onDataSetChanged()}
     */
    void bindCount(AVM arrayViewModel);

    /**
     * Should be implemented to reload views.
     */
    void onDataSetChanged();

    /**
     * Binds {@link IArrayViewModel#localizedEmptyMessage()} to {@link #setLocalizedEmptyMessage(CharSequence)}
     */
    void bindLocalizedEmptyMessage(AVM arrayViewModel);

    /**
     * Sets message used when array is empty.
     */
    void setLocalizedEmptyMessage(CharSequence localizedEmptyMessage);

    /**
     * Bind {@link IArrayViewModel#localizedEmptyMessage()} to {@link #setLocalizedEmptyMessageVisibility(boolean)}
     */
    void bindLocalizedEmptyMessageVisibility(AVM arrayViewModel);

    /**
     * Sets visibility to empty message view.
     */
    void setLocalizedEmptyMessageVisibility(boolean visibility);
}
