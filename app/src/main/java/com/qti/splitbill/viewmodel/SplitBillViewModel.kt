import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.qti.splitbill.table.AppDatabase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.qti.splitbill.entity.SplitBill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplitBillViewModel(application: Application) : AndroidViewModel(application) {

    private val splitBillDao = AppDatabase.getInstance(application).splitBillDao()

    // LiveData untuk menampung data SplitBill
    private val _splitBillList = MutableLiveData<List<SplitBill>>()
    val splitBillList: LiveData<List<SplitBill>> get() = _splitBillList

    private val _splitBillDetail = MutableLiveData<SplitBill?>()
    val splitBillDetail: LiveData<SplitBill?> get() = _splitBillDetail

    // Get list of SplitBill
    fun fetchAllSplitBills() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = splitBillDao.getAllSplitBills()
            _splitBillList.postValue(list) // Mengupdate LiveData di thread utama
        }
    }

    // Get SplitBill by ID
    fun fetchSplitBillById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val detail = splitBillDao.getSplitBillById(id)
            _splitBillDetail.postValue(detail) // Mengupdate LiveData di thread utama
        }
    }

    // Update SplitBill
    fun updateSplitBill(splitBill: SplitBill) {
        viewModelScope.launch(Dispatchers.IO) {
            splitBillDao.updateSplitBill(splitBill)
            fetchAllSplitBills() // Refresh list setelah update
        }
    }

    // Delete SplitBill
    fun deleteSplitBill(splitBill: SplitBill) {
        viewModelScope.launch(Dispatchers.IO) {
            splitBillDao.deleteSplitBill(splitBill)
            fetchAllSplitBills() // Refresh list setelah delete
        }
    }
}

