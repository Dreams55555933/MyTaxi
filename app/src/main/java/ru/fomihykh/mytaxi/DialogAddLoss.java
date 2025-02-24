package ru.fomihykh.mytaxi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogAddLoss extends DialogFragment {

    private EditText lossEditText;
    private RadioButton isGsm;
    private OnDataSaveListener dataSaveListener;

    public void setDataSaveListener(OnDataSaveListener dataSaveListener) {
        this.dataSaveListener = dataSaveListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Инфлейтим макет для диалога
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_loss, null);

        // Инициализируем элементы управления
        lossEditText = view.findViewById(R.id.loss);
        isGsm = view.findViewById(R.id.checkGsm);

        builder.setView(view)
                .setTitle("Добавить расход")
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Сохраняем данные при нажатии на кнопку "Добавить"
                        saveData();
                        if (dataSaveListener != null){
                            dataSaveListener.onDataSaved();
                        }
                    }
                })
                .setNegativeButton("Отмена", null);

        return builder.create();
    }

    private void saveData() {
        // Получаем SharedPreferences
        SharedPreferences pref = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();

        // Получаем значение из EditText
        String lossValue = lossEditText.getText().toString();
        if (!lossValue.isEmpty()) {
            int loss = Integer.parseInt(lossValue);

            // Если выбран RadioButton, сохраняем значение в gsm
            if (isGsm.isChecked()) {
                int gsm = pref.getInt("gsm", 0);
                prefEditor.putInt("gsm", gsm + loss);
            } else {
                // Иначе сохраняем в другое место (например, "other_loss")
                int otherLoss = pref.getInt("other_loss", 0);
                prefEditor.putInt("other_loss", otherLoss + loss);
            }

            // Применяем изменения
            prefEditor.apply();
        }
    }
}