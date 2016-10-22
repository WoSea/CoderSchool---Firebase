package apidez.com.firebase.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.text.format.DateFormat;
import android.view.View;

import apidez.com.firebase.model.Priority;
import apidez.com.firebase.model.Todo;

/**
 * Created by nongdenchet on 2/8/16.
 */
public class TodoViewModel extends BaseObservable {
    private final String NO_DUE_DATE = "No due date";

    private ObservableBoolean mEnableState = new ObservableBoolean(true);
    private ObservableInt mActionVisibility = new ObservableInt(View.GONE);
    private ObservableInt mDividerVisibility = new ObservableInt(View.VISIBLE);
    private ObservableInt mDisableVisibility = new ObservableInt(View.INVISIBLE);
    private Todo mTodo;

    public TodoViewModel(Todo todo) {
        this.mTodo = todo;
    }

    public Todo getTodo() {
        return mTodo;
    }

    public ObservableInt getActionVisibility() {
        return mActionVisibility;
    }

    public ObservableInt getDisableVisibility() {
        return mDisableVisibility;
    }

    public ObservableBoolean getEnableState() {
        return mEnableState;
    }

    public ObservableInt getDividerVisibility() {
        return mDividerVisibility;
    }

    public void setTodo(Todo todo) {
        this.mTodo = todo;
    }

    public void resetState() {
        mEnableState.set(true);
        mActionVisibility.set(View.GONE);
        mDividerVisibility.set(View.VISIBLE);
        mDisableVisibility.set(View.INVISIBLE);
    }

    private boolean isDisableLayerVisible() {
        return mDisableVisibility.get() == View.VISIBLE;
    }

    private boolean isActionVisible() {
        return mActionVisibility.get() == View.VISIBLE;
    }

    private boolean isDividerVisible() {
        return mDividerVisibility.get() == View.VISIBLE;
    }

    public void switchEnableWhenNotChoose() {
        mDisableVisibility.set(isDisableLayerVisible() ? View.INVISIBLE : View.VISIBLE);
        mEnableState.set(!mEnableState.get());
    }

    public void switchActionVisibility() {
        mActionVisibility.set(isActionVisible() ? View.GONE : View.VISIBLE);
        mDividerVisibility.set(isDividerVisible() ? View.INVISIBLE : View.VISIBLE);
    }

    public boolean isCompleted() {
        return mTodo.isCompleted();
    }

    public boolean actionShowing() {
        return mActionVisibility.get() == View.VISIBLE;
    }

    public float getOpacity() {
        return mTodo.isCompleted() ? 0.5f : 1.0f;
    }

    public String getTitle() {
        return mTodo.getTitle();
    }

    public String getDueDate() {
        return mTodo.getDueDate() == null ? NO_DUE_DATE : DateFormat.format("dd/MM/yyyy", mTodo.getDueDate()).toString();
    }

    public Priority getPriority() {
        return mTodo.getPriority();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TodoViewModel && ((TodoViewModel) obj).getTodo().equals(mTodo);
    }
}