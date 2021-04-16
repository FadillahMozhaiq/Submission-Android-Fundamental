package id.fadillah.userconsumerapp.ui.fragment.timepicker

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment: DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private var mListener: DialogTimeListener? = null
    private var mCancelListener: DialogTimeCancelListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as DialogTimeListener
    }

    override fun onDetach() {
        super.onDetach()
        if (mListener != null) {
            mListener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val formatHour24 = true
        return TimePickerDialog(activity, this, hour, minute, formatHour24)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        mListener?.onDialogTimeSet(hourOfDay, minute)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        mCancelListener?.onDialogCancel(true)
    }

    interface DialogTimeListener {
        fun onDialogTimeSet(hourOfDay: Int, minute: Int)
    }

    interface DialogTimeCancelListener {
        fun onDialogCancel(canceled: Boolean)
    }
}