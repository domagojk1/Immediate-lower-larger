package com.myapplication.dkopic.clover;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myapplication.dkopic.clover.constants.StateConstants;
import com.myapplication.dkopic.clover.mvp.presenter.MainPresenter;
import com.myapplication.dkopic.clover.mvp.presenter.impl.MainPresenterImpl;
import com.myapplication.dkopic.clover.mvp.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;
    private RelativeLayout mSolutionLayout;
    private Unbinder unbinder;

    @BindView(R.id.et_input)
    EditText mEditTextInput;

    @BindView(R.id.tv_smaller_num)
    TextView mTextViewSmaller;

    @BindView(R.id.tv_larger_num)
    TextView mTextViewLarger;

    @BindView(R.id.tv_error)
    TextView mTextViewError;

    @BindView(R.id.btn_confirm)
    Button mButtonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this);
        unbinder = ButterKnife.bind(this);
        mSolutionLayout = (RelativeLayout) findViewById(R.id.layout_solution);

        if (savedInstanceState != null) {
            mEditTextInput.setText(savedInstanceState.getString(StateConstants.INPUT));
            mTextViewError.setText(savedInstanceState.getString(StateConstants.ERROR));

            String firstSmaller = savedInstanceState.getString(StateConstants.RESULT_SMALLER);
            String firstLarger = savedInstanceState.getString(StateConstants.RESULT_LARGER);

            if (firstSmaller != "" || firstLarger != "") {
                showResultView();
                mTextViewSmaller.setText(firstSmaller);
                mTextViewLarger.setText(firstLarger);
            }
        }
    }

    @OnClick(R.id.btn_confirm)
    protected void onClickConfirm() {
        if (mButtonConfirm.getText().toString().equals(getString(R.string.cancel))) {
            hideResultView();
            mEditTextInput.setText("");
        }
        else {
            presenter.fetchResults(mEditTextInput.getText().toString());
        }
    }

    @Override
    public void showResultView() {
        mSolutionLayout.setVisibility(View.VISIBLE);
        mButtonConfirm.setText(getString(R.string.cancel));
    }

    @Override
    public void hideResultView() {
        mSolutionLayout.setVisibility(View.INVISIBLE);
        mButtonConfirm.setText(getString(R.string.confirm));
    }

    @Override
    public void showSmaller(String result) {
        mTextViewSmaller.setText(result);
    }

    @Override
    public void showLarger(String result) {
        mTextViewLarger.setText(result);
    }

    @Override
    public void showLargerUnavailable() {
        mTextViewLarger.setText(R.string.larger_unavailable);
    }

    @Override
    public void showSmallerUnavailable() {
        mTextViewSmaller.setText(R.string.smaller_unavailable);
    }

    @Override
    public void showRangeError() {
        mTextViewError.setText(getString(R.string.error_range));
    }

    @Override
    public void showInputError() {
        mTextViewError.setText(getString(R.string.error_not_num));
    }

    @Override
    public void hideError() {
        mTextViewError.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mEditTextInput != null) outState.putString(StateConstants.INPUT, mEditTextInput.getText().toString());
        if (mTextViewLarger != null) outState.putString(StateConstants.RESULT_LARGER, mTextViewLarger.getText().toString());
        if (mTextViewSmaller != null) outState.putString(StateConstants.RESULT_SMALLER, mTextViewSmaller.getText().toString());
        if (mTextViewError != null) outState.putString(StateConstants.ERROR, mTextViewError.getText().toString());
    }
}
