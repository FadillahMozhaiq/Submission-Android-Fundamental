package id.fadillah.fundamentalsubmission.ui.activity.detail

import androidx.lifecycle.ViewModel
import id.fadillah.fundamentalsubmission.data.model.UserEntity

class DetailViewModel: ViewModel() {
    private var _dataDetail = UserEntity()

    fun setData(data: UserEntity) {
        _dataDetail = data
    }

    fun loadData(): UserEntity = _dataDetail
}