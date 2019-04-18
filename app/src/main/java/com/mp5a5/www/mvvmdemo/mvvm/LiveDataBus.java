package com.mp5a5.www.mvvmdemo.mvvm;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ：mp5a5 on 2019/4/9 17：36
 * @describe
 * @email：wwb199055@126.com
 */
public class LiveDataBus {
  private static volatile LiveDataBus instance;

  private final ConcurrentHashMap<Object, LiveBusData<Object>> mLiveBus;

  private final List<Object> mList;

  private LiveDataBus() {
    mLiveBus = new ConcurrentHashMap<>();
    mList = new CopyOnWriteArrayList<>();
  }

  public static LiveDataBus getDefault() {
    if (instance == null) {
      synchronized (LiveDataBus.class) {
        if (instance == null) {
          instance = new LiveDataBus();
        }
      }
    }
    return instance;
  }


  public <T> MutableLiveData<T> subscribe(Object eventKey) {
    return subscribe(eventKey, "");
  }

  public <T> MutableLiveData<T> subscribe(Object eventKey, String tag) {
    return (MutableLiveData<T>) subscribe(eventKey, tag, Object.class);
  }

  public <T> MutableLiveData<T> subscribe(Object eventKey, Class<T> tClass) {
    return subscribe(eventKey, null, tClass);
  }

  public <T> MutableLiveData<T> subscribe(Object eventKey, String tag, Class<T> tClass) {
    String key = mergeEventKey(eventKey, tag);
    if (!mLiveBus.containsKey(key)) {
      mLiveBus.put(key, new LiveBusData<>(true));
    } else {
      LiveBusData liveBusData = mLiveBus.get(key);
      liveBusData.isFirstSubscribe = false;
    }

    return (MutableLiveData<T>) mLiveBus.get(key);
  }

  public <T> MutableLiveData<T> postEvent(Object eventKey, T value) {
    return postEvent(eventKey, null, value);
  }

  public <T> MutableLiveData<T> postEvent(Object eventKey, String tag, T value) {
    MutableLiveData<T> mutableLiveData = subscribe(mergeEventKey(eventKey, tag));
    mutableLiveData.postValue(value);
    return mutableLiveData;
  }


  public static class LiveBusData<T> extends MutableLiveData<T> {

    private boolean isFirstSubscribe;

    LiveBusData(boolean isFirstSubscribe) {
      this.isFirstSubscribe = isFirstSubscribe;
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
      super.observe(owner, new ObserverWrapper<>(observer,isFirstSubscribe));
    }
  }

  private static class ObserverWrapper<T> implements Observer<T> {

    private Observer<T> observer;

    private boolean isChanged;

    private ObserverWrapper(Observer<T> observer, boolean isFirstSubscribe) {
      this.observer = observer;
      isChanged = isFirstSubscribe;
    }

    @Override
    public void onChanged(@Nullable T t) {
      if (isChanged) {
        if (observer != null) {
          observer.onChanged(t);
        }
      } else {
        isChanged = true;
      }
    }

  }


  private String mergeEventKey(Object eventKey, String tag) {
    String mEventKey;
    if (!TextUtils.isEmpty(tag)) {
      mEventKey = eventKey + tag;
    } else {
      mEventKey = (String) eventKey;
    }
    mList.add(mEventKey);
    return mEventKey;
  }


  public void clear() {
    if (!mList.isEmpty()) {
      for (Object key : mList) {
        clear(key, null);
      }
    }
  }

  public void clear(Object eventKey, String tag) {
    if (mLiveBus.size() > 0) {
      String mEventKey = mergeEventKey(eventKey, tag);
      mLiveBus.remove(mEventKey);
    }

  }
}
