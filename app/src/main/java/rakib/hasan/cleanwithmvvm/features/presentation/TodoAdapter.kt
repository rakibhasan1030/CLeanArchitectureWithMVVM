package rakib.hasan.cleanwithmvvm.features.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rakib.hasan.cleanwithmvvm.databinding.ItemTodoBinding
import rakib.hasan.cleanwithmvvm.features.domain.entity.TodoEntity

class TodoAdapter (private val todos: MutableList<TodoEntity>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    interface Listener {
        fun onTap(todo: TodoEntity)
    }

    private var listener: Listener? = null


    fun setOnTapListener(l: Listener) {
        listener = l
    }


    fun updateList(list: List<TodoEntity>) {
        todos.clear()
        todos.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: ItemTodoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(todo: TodoEntity) {
            itemBinding.titleTextView.text = todo.title
            itemBinding.isCompleteTextView.text = if (todo.isComplete) "COMPLETED" else "NOT COMPLETED"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(todos[position])


    override fun getItemCount() = todos.size

}