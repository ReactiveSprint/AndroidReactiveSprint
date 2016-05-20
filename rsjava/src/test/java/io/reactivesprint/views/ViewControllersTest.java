package io.reactivesprint.views;

import junit.framework.TestCase;

import io.reactivesprint.viewmodels.FetchedArrayViewModel;
import io.reactivesprint.viewmodels.MutableArrayViewModel;
import io.reactivesprint.viewmodels.ViewModel;
import io.reactivesprint.viewmodels.ViewModelException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Ahmad Baraka on 4/8/16.
 */
@SuppressWarnings("unchecked")
public class ViewControllersTest extends TestCase {

    public void testSetTitle() throws Exception {
        IViewController<ViewModel> viewController = mock(IViewController.class);
        ViewControllers.setTitle(viewController).call("Test");
        verify(viewController).setTitle("Test");
    }

    public void testPresentLoading() throws Exception {
        IViewController<ViewModel> viewController = mock(IViewController.class);
        ViewControllers.presentLoading(viewController).call(true);
        verify(viewController).presentLoading(true);
    }

    @SuppressWarnings("ThrowableInstanceNeverThrown")
    public void testPresentError() throws Exception {
        IViewController<ViewModel> viewController = mock(IViewController.class);
        ViewModelException viewModelException = new ViewModelException("TestException");
        ViewControllers.presentError(viewController).call(viewModelException);
        verify(viewController).presentError(viewModelException);

    }

    public void testOnDataSetChanged() throws Exception {
        IArrayViewController<ViewModel, MutableArrayViewModel<ViewModel>> viewController = mock(IArrayViewController.class);
        ViewControllers.onDataSetChanged(viewController).call(0);
        verify(viewController).onDataSetChanged();
    }

    public void testSetLocalizedEmptyMessage() throws Exception {
        IArrayViewController<ViewModel, MutableArrayViewModel<ViewModel>> viewController = mock(IArrayViewController.class);
        ViewControllers.setLocalizedEmptyMessage(viewController).call("Test");
        verify(viewController).setLocalizedEmptyMessage("Test");
    }

    public void testSetLocalizedEmptyMessageVisibility() throws Exception {
        IArrayViewController<ViewModel, MutableArrayViewModel<ViewModel>> viewController = mock(IArrayViewController.class);
        ViewControllers.setLocalizedEmptyMessageVisibility(viewController).call(true);
        verify(viewController).setLocalizedEmptyMessageVisibility(true);
    }

    public void testPresentRefreshing() throws Exception {
        IFetchedArrayViewController<ViewModel, FetchedArrayViewModel<ViewModel, Integer>> viewController = mock(IFetchedArrayViewController.class);
        ViewControllers.presentRefreshing(viewController).call(true);
        verify(viewController).presentRefreshing(true);
    }

    public void testPresentFetchingNextPage() throws Exception {
        IFetchedArrayViewController<ViewModel, FetchedArrayViewModel<ViewModel, Integer>> viewController = mock(IFetchedArrayViewController.class);
        ViewControllers.presentFetchingNextPage(viewController).call(true);
        verify(viewController).presentFetchingNextPage(true);
    }
}