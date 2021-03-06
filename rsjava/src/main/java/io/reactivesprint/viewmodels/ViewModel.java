package io.reactivesprint.viewmodels;

import io.reactivesprint.rx.ICommand;
import io.reactivesprint.rx.IMutableProperty;
import io.reactivesprint.rx.IProperty;
import io.reactivesprint.rx.MutableProperty;
import io.reactivesprint.rx.Property;
import io.reactivesprint.rx.functions.Func1BooleanNot;
import io.reactivesprint.rx.functions.FuncNBooleanOr;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

import static io.reactivesprint.Preconditions.checkNotNull;

/**
 * Created by Ahmad Baraka on 3/29/16.
 * Abstract implementation of a {@code ViewModel} used in MVVM.
 */
public class ViewModel implements IViewModel {
    //region Fields

    private final IMutableProperty<Boolean> active = new MutableProperty<>(false);
    private final IMutableProperty<CharSequence> title = new MutableProperty<>(null);

    private final Subject<Observable<Boolean>, Observable<Boolean>> loadingSubject = PublishSubject.create();
    private final IProperty<Boolean> loading;

    private final IProperty<Boolean> enabled;

    private final Subject<Observable<IViewModelException>, Observable<IViewModelException>> errorsSubject = ReplaySubject.create(1);
    private final Observable<IViewModelException> errors = Observable.merge(errorsSubject);

    //endregion

    //region Constructors

    public ViewModel() {
        Observable<Boolean> loadingObservable = Observable.switchOnNext(loadingSubject.scan(Observable.just(false), new Func2<Observable<Boolean>, Observable<Boolean>, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(Observable<Boolean> observable, Observable<Boolean> observable2) {
                return Observable.combineLatest(observable, observable2.startWith(false), FuncNBooleanOr.getInstance());
            }
        }));

        loading = new Property<>(false, loadingObservable);

        enabled = new Property<>(false, loading.getObservable().map(Func1BooleanNot.getInstance()));
    }

    public ViewModel(String title) {
        this();
        this.title.setValue(title);
    }

    //endregion

    //region Properties

    @Override
    public final IMutableProperty<Boolean> active() {
        return active;
    }

    @Override
    public IMutableProperty<CharSequence> title() {
        return title;
    }

    @Override
    public final IProperty<Boolean> loading() {
        return loading;
    }

    @Override
    public final Observable<IViewModelException> errors() {
        return errors;
    }

    @Override
    public final IProperty<Boolean> enabled() {
        return enabled;
    }

    //endregion

    //region Binding

    @Override
    public final void bindLoading(Observable<Boolean> loadingObservable) {
        checkNotNull(loadingObservable, "loadingObservable");
        loadingSubject.onNext(loadingObservable
                .concatWith(Observable.just(false))
                .onErrorResumeNext(Observable.just(false))
        );
    }

    @Override
    public final void bindErrors(Observable<IViewModelException> errorObservable) {
        checkNotNull(errorObservable, "errorObservable");
        errorsSubject.onNext(errorObservable.onErrorResumeNext(Observable.<IViewModelException>empty()));
    }

    @Override
    public final <I, R> void bindCommand(ICommand<I, R> command) {
        checkNotNull(command, "command");
        bindLoading(command.isExecuting().getObservable());

        bindErrors(command.getErrors().flatMap(new Func1<Throwable, Observable<IViewModelException>>() {
            @Override
            public Observable<IViewModelException> call(Throwable throwable) {
                if (throwable instanceof IViewModelException) {
                    return Observable.just((IViewModelException) throwable);
                }
                return Observable.empty();
            }
        }));
    }

    //endregion

    //region Object

    @Override
    public String toString() {
        return "ViewModel{" +
                "active=" + active.getValue() +
                ", title=" + title.getValue() +
                ", loading=" + loading.getValue() +
                ", enabled=" + enabled.getValue() +
                '}';
    }

    //endregion
}
