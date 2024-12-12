package paba.c14220130.projectdbroom

import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import paba.c14220130.projectroom.database.daftarBelanja

class adapterDaftar (private val daftarBelanja: MutableList<daftarBelanja>): RecyclerView.Adapter<adapterDaftar.ListViewHolder>() {
    private lateinit var onItemClickCallback : OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterDaftar.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recycler, parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }

    override fun onBindViewHolder(holder: adapterDaftar.ListViewHolder, position: Int) {
        var daftar = daftarBelanja[position]
        holder._tvTanggal.setText(daftar.tanggal)
        holder._tvItemBarang.setText(daftar.item)
        holder._tvJumlahBarang.setText(daftar.jumlah)
        holder._btnEdit.setOnClickListener{
            val intent = Intent(it.context, TambahDaftar::class.java)
            intent.putExtra("id", daftar.id)
            intent.putExtra("addEdit", 1)
            it.context.startActivity(intent)
        }
        holder._btnHapus.setOnClickListener{
            onItemClickCallback.delData(daftar)
        }
    }

    class ListViewHolder (ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var _tvItemBarang = itemView.findViewById<TextView>(R.id.namaItem)
        var _tvJumlahBarang = itemView.findViewById<TextView>(R.id.tv2)
        var _tvTanggal = itemView.findViewById<TextView>(R.id.tv1)
        var _btnEdit =  itemView.findViewById<Button>(R.id.btnEdit)
        var _btnHapus =  itemView.findViewById<Button>(R.id.btnHapus)
    }

    interface OnItemClickCallback {
        fun delData(dtBelanja: daftarBelanja)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    fun isiData(daftar: List<daftarBelanja>) {
        daftarBelanja.clear()
        daftarBelanja.addAll(daftar)
        notifyDataSetChanged()
    }
}