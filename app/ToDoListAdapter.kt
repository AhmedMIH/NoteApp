import android.provider.ContactsContract.Directory.PACKAGE_NAME
import com.example.kvapp.ui.magazine.adapter.FeaturedHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kvapp.R

class ToDoListAdapter(val noteModel:List<noteModel>) : RecyclerView.Adapter<ToDoListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_list_row, parent, false)
        return ToDoListHolder(view)
    }
    override fun getItemCount(): Int {
        return text.size
    }

    override fun onBindViewHolder(holder: ToDoListHolder, position: Int) {

    }
}

class ToDoListHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind() {}
}

}
