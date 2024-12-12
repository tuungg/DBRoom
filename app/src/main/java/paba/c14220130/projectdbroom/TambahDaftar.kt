package paba.c14220130.projectdbroom

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import paba.c14220130.projectroom.database.daftarBelanja
import paba.c14220130.projectroom.database.daftarBelanjaDB
import paba.c14220130.projectroom.helper.DateHelper.getCurrentDate

class TambahDaftar : AppCompatActivity() {
    private lateinit var etItem: EditText
    private lateinit var etJumlah: EditText
    private lateinit var btnTambah: Button
    private lateinit var btnUpdate: Button
    var DB = daftarBelanjaDB.getDatabase(this)
    var tanggal = getCurrentDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_daftar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etItem = findViewById(R.id.etItem)
        etJumlah = findViewById(R.id.etJumlah)
        btnTambah = findViewById(R.id.btnTambah)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnTambah.setOnClickListener{
            CoroutineScope(Dispatchers.IO).async {
                DB.fundaftarBelanjaDAO().insert(
                    daftarBelanja(
                        tanggal = tanggal,
                        item = etItem.text.toString(),
                        jumlah = etJumlah.text.toString()
                    )
                )
                finish()
            }
        }
    }
}