package app.dhaslibrary.util;


import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;


/**
 * Created by Dev on 14/12/2559.
 */
public class RxBus {

    private static RxBus instance;
    private Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());
    private int Count = 0;

    private RxBus() {
    }

    public static RxBus getInstance() {
        synchronized (RxBus.class) {
            if (instance == null) {
                instance = new RxBus();
            }
        }
        return instance;
    }

    public void post(int code, String value) {
        Count = 0;
        bus.onNext(new RxMessage(code, value));
    }

    public <T> Subscription doSubscribe(int code, Action1<T> onNext) {
        return bus.ofType(RxMessage.class)
                .filter(o -> o.getCode() == code)
                .map(o -> (T) o)
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<T>() {
                    @Override
                    public void call(T t) {
                        Count = 1;// (Count < 10 ? Count++ : 0);
                    }
                })
                .subscribe(onNext);
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    //    private static RxBus mInstance;
//    private SerializedSubject<Object, Object> mSubject;
//    private HashMap<String, CompositeSubscription> mSubscriptionMap;
//
//    private RxBus() {
//        mSubject = new SerializedSubject<>(PublishSubject.create());
//    }
//
//    public static RxBus getInstance() {
//        if (mInstance == null) {
//            synchronized (RxBus.class) {
//                if (mInstance == null) {
//                    mInstance = new RxBus();
//                }
//            }
//        }
//        return mInstance;
//    }
//
//    /**
//     */
//    public void post(int key, String value) {
//        mSubject.onNext(new RxMessage(key, value));
//    }
//
//    /**
//     * 返回指定类型的Observable实例
//     *
//     * @param type
//     * @param <T>
//     * @return
//     */
//    public <T> Observable<T> toObservable(final Class<T> type) {
//        return mSubject.ofType(type);
//    }
//
//    /**
//     * 是否已有观察者订阅
//     *
//     * @return
//     */
//    public boolean hasObservers() {
//        return mSubject.hasObservers();
//    }
//
//    public Subscription doSubscribe(int key, Action1<RxMessage> next, Action1<Throwable> error) {
//        return mSubject.ofType(RxMessage.class)
//                .filter(o->o.getCode() == key)
////                .map(e->e)
////                .first()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(next, error);
//    }
//
//    public Subscription doSubscribeBackground(int key, Action1<RxMessage> next, Action1<Throwable> error) {
//        return mSubject.ofType(RxMessage.class)
//                .filter(o->o.getCode() == key)
////                .map(e->e)
////                .first()
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(next, error);
//    }
//
//    /**
//     * 保存订阅后的subscription
//     *
//     * @param o
//     * @param subscription
//     */
//    public void addSubscription(Object o, Subscription subscription) {
//        if (mSubscriptionMap == null) {
//            mSubscriptionMap = new HashMap<>();
//        }
//        String key = o.getClass().getName();
//        if (mSubscriptionMap.get(key) != null) {
//            mSubscriptionMap.get(key).add(subscription);
//        } else {
//            CompositeSubscription compositeSubscription = new CompositeSubscription();
//            compositeSubscription.add(subscription);
//            mSubscriptionMap.put(key, compositeSubscription);
//        }
//    }
//
//    /**
//     * 取消订阅
//     *
//     * @param o
//     */
//    public void unSubscribe(Object o) {
//        if (mSubscriptionMap == null) {
//            return;
//        }
//        String key = o.getClass().getName();
//        if (!mSubscriptionMap.containsKey(key)) {
//            return;
//        }
//        if (mSubscriptionMap.get(key) != null) {
//            mSubscriptionMap.get(key).unsubscribe();
//        }
//        mSubscriptionMap.remove(key);
//    }


}



