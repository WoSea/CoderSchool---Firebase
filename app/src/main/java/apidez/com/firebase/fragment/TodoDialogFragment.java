package apidez.com.firebase.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import apidez.com.firebase.R;
import apidez.com.firebase.custom.DueDatePicker;
import apidez.com.firebase.custom.PriorityPicker;
import apidez.com.firebase.model.Todo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nongdenchet on 2/11/16.
 */
public class TodoDialogFragment extends DialogFragment implements DueDatePicker.ListenerPickDate {
    public static final String TAG = TodoDialogFragment.class.getSimpleName();
    private static final String TODO = "todo";
    private boolean isRestore = false;
    private Todo mTodo;
    private CallbackSuccess mCallbackSuccess;

    @BindView(R.id.discard)
    TextView discardButton;

    @BindView(R.id.title_edit_text)
    EditText titleEditText;

    @BindView(R.id.priority_picker)
    PriorityPicker priorityPicker;

    @BindView(R.id.due_date_picker)
    DueDatePicker dueDatePicker;

    public interface CallbackSuccess {
        void onCreateSuccess(Todo todo);
        void onUpdateSuccess(Todo todo);
    }

    public void setCallbackSuccess(CallbackSuccess callbackSuccess) {
        this.mCallbackSuccess = callbackSuccess;
    }

    public static TodoDialogFragment newInstance() {
        Bundle args = new Bundle();
        TodoDialogFragment fragment = new TodoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static TodoDialogFragment newInstance(Todo todo) {
        Bundle args = new Bundle();
        args.putSerializable(TODO, todo);
        TodoDialogFragment fragment = new TodoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_todo, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dueDatePicker.setListenerPickDate(this);
        discardButton.setOnClickListener(v -> dismiss());
        restoreTodo();
    }

    private void restoreTodo() {
        mTodo = (Todo) getArguments().getSerializable(TODO);
        if (mTodo != null) {
            isRestore = true;
            restoreView();
        }
    }

    private void restoreView() {
        titleEditText.setText(mTodo.getTitle());
        priorityPicker.setPriority(mTodo.getPriority());
        dueDatePicker.setDueDate(mTodo.getDueDate());
    }

    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void pickDate(DueDatePicker.CallbackPickDate callbackPickDate) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                (view, year, monthOfYear, dayOfMonth) -> callbackPickDate.onDatePicked(year, monthOfYear, dayOfMonth),
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
    }

    @OnClick(R.id.save)
    public void onSaveButtonClick() {
        if (isRestore) {
            update();
        } else {
            create();
        }
    }

    private Todo gatherData() {
        // TODO: implement this
        return null;
    }

    private void create() {
        // TODO: implement this
    }

    private void update() {
        // TODO: implement this
    }
}
